package com.luis.kingboot.boot;

import java.util.ArrayList;
import java.util.List;

import sun.management.counter.perf.PerfLongArrayCounter;

import com.luis.kingboot.connection.OnlineInputOutput;
import com.luis.kingboot.main.GameState;
import com.luis.kingboot.main.Main;
import com.luis.strategy.constants.GameParams;
import com.luis.strategy.data.GameBuilder;
import com.luis.strategy.datapackage.scene.PreSceneListData;
import com.luis.strategy.datapackage.scene.SceneData;
import com.luis.strategy.datapackage.scene.SceneListData;
import com.luis.strategy.map.ActionIA;
import com.luis.strategy.map.Army;
import com.luis.strategy.map.Army.IADecision;
import com.luis.strategy.map.GameScene;
import com.luis.strategy.map.Kingdom;
import com.luis.strategy.map.Player;
import com.luis.strategy.map.Terrain;
import com.luis.strategy.map.Troop;

public class Boot extends Thread{
	
	private Object mutex;
	private String name;
	
	
	private static final int MAX_PREESCENARI = 3;
	
	public Boot(Object mutex, String name){
		this.mutex = mutex;
		this.name = name;
	}
	 
	@Override
	public void run(){
		super.run();
		while(true){
			try{
				createScenary();
				play();
				
				Thread.sleep(1000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void createScenary(){
		synchronized (mutex) {
			
			PreSceneListData preSceneListData =  OnlineInputOutput.getInstance().reviceAllPreSceneListData(OnlineInputOutput.URL_GET_ALL_PRE_SCENE_LIST);
			
			if(preSceneListData.getPreSceneDataList().size() < MAX_PREESCENARI){
				int map = Main.getRandom(0, 2);
				String host = name;
				String sceneName = "Scene by " + name;
				
				OnlineInputOutput.getInstance().sendPreScene("createPreSceneServlet", ""+map, host, sceneName);
				
				System.out.println("Boot " + name + " has created a new scene");
			}
		}
	}
	
	private void play(){
		//recivo todas las partidas en las que participo
		SceneListData sceneListData = OnlineInputOutput.getInstance().reviceSceneListData(name);
		for(SceneData sceneData : sceneListData.getSceneDataList()){
			//Si el siguiente jugador soy yo:
			if(sceneData.getNextPlayer().equals(name)){
				int playerIndex = sceneData.getPlayerIndex();
				
				//Cargo la partida
				GameState gameState = new GameState();
				if(sceneData.getState() == 0){
					SceneData sd = OnlineInputOutput.getInstance().reviceSceneData(OnlineInputOutput.URL_GET_START_SCENE, ""+sceneData.getId());
					gameState.init(GameState.GAME_MODE_ONLINE, sd);
					gameState.setGameScene(GameBuilder.getInstance().buildStartGameScene(gameState));
				}else{
					SceneData sd = OnlineInputOutput.getInstance().reviceSceneData(OnlineInputOutput.URL_GET_SCENE, ""+sceneData.getId());
					gameState.init(GameState.GAME_MODE_ONLINE, sd);
					gameState.setGameScene(GameBuilder.getInstance().buildGameScene(gameState));
					
					
					
					playTurn(gameState.getGameScene());
					
					
					
				}
				do{
					playerIndex = playerIndex+1%gameState.getGameScene().getPlayerList().size();
				}
				while(gameState.getGameScene().getPlayerList().get(playerIndex).getCapitalkingdom() == null);
				
				if(playerIndex==0){
					gameState.getGameScene().setTurnCount(gameState.getGameScene().getTurnCount()+1);
				}
				gameState.getGameScene().setPlayerIndex(playerIndex);
				
				
				SceneData sd = GameBuilder.getInstance().buildSceneData(gameState, 1);
				OnlineInputOutput.getInstance().sendDataPackage(OnlineInputOutput.URL_UPDATE_SCENE, sd);
				
				OnlineInputOutput.getInstance().sendNotifiation(
						OnlineInputOutput.URL_CREATE_NOTIFICATION, 
						"" + gameState.getSceneData().getId(), 
						name, 
						" TURN: " + gameState.getGameScene().getTurnCount());
				System.out.println(name + " has responded game");
			}
		}
		
		
	}

	private void playTurn(GameScene gameScene) {
		
		Player player = gameScene.getPlayerList().get(gameScene.getPlayerIndex());
		
		//Activo la IA
		ActionIA ia = new ActionIA();
		ia.setPlayer(player);
		player.setActionIA(ia);
		for(Army a: player.getArmyList()){
			a.createIADecision();
		}
		
		//Caluculo de la economia
		int tax = player.getTaxes();
		//Calculo de salarios
		int salary = player.getCost(false);
		player.setGold(player.getGold()+tax-salary);
		
		
		for(Army army : player.getArmyList()){
			player.getActionIA().buildDecision(gameScene.getPlayerList(), army);
			
			if(army.getIaDecision().getDecision() != ActionIA.DECISION_NONE){
				
				resolveMovement(gameScene.getPlayerList(), player, army);
				
			}
			
		}
	}
	

	private void combat(List<Player> playerList, Player player, Terrain terrain, Army armyAtack, Army armyDefense){
		
		int diceDifficult = calculateDifficult(terrain, armyAtack, armyDefense);
		
		int result = 0;
		int[] diceValue = new int[]{
				Main.getRandom(1, GameParams.ROLL_SYSTEM), 
				Main.getRandom(1, GameParams.ROLL_SYSTEM), 
				Main.getRandom(1, GameParams.ROLL_SYSTEM)};
		
		for(int i = 0;i < 3; i++){
			if(diceValue[i] == GameParams.ROLL_SYSTEM || diceValue[i] >= diceDifficult){
				result++;
			}
		}
		resolveCombat(playerList, player, armyAtack, result);
	}
	
	private void resolveCombat(List<Player> playerList, Player player, Army selectedArmy, int result){
		Player defeatPlayer = null;
		Army defeatArmy = null;
		Army enemy = getEnemyAtKingdom(playerList, player);
		
		/*
		 * El ejercito ganador genera la mitad de su valor en daño al ejercito perdedor
		 * El ejercito perdedor genera la la cuarta parte de su daño al ejercito ganador
		 */
		boolean changeCapital = false;
		boolean deletePlayer = false;
		
		String textH = "";
		String textB = "";
		
		boolean attackerWins=false;
		boolean attackerLost=false;
		boolean attackerHasDestroyed=false;
		boolean arrackerHasBeendestroyed=false;
		
		textH="RESULT";
		switch(result){
			case 0: 
				textH="BIG DEFEAT";
				break;
			case 1: 
				textH="DEFEAT";
				break;
			case 2: 
				textH="VICTORY";
				break;
			case 3: 
				textH="BIG VICTORY";
				break;
		}
		
		//Hay ejercito enemigo
		if(enemy != null){
			//Resolucion del combate
			if(result > 1)
				defeatArmy = getEnemyAtKingdom(playerList, player);
			else
				defeatArmy = selectedArmy;
			
			//Comparo si alguno de los territorios adyacentes pertenece al derrotado
			Kingdom defeatTarget = getBorderKingdom(playerList, defeatArmy);
			
			
			boolean aniquilation = 
				(result == 3 && defeatArmy.getPlayer().getId() != player.getId())
				||
				(result == 0 && defeatArmy.getPlayer().getId() == player.getId());
			
			if(defeatTarget == null || aniquilation){
				
				defeatArmy.setDefeat(true);
				//Masacre al defensor
				if(defeatArmy.getPlayer().getId() != player.getId()){
					attackerHasDestroyed = true;
					textB = "ATTACKER HAS DESTROYED";
				}
				//Masacrea al atacante
				else{
					arrackerHasBeendestroyed = true;
					textB = "ATTACKER HAS BEEN DESTROYED";
				}
			}else{
				defeatArmy.setDefeat(true);
				//Daño
				int casualtiesFromArmy = 0;
				int casualtiesFromEnemy = 0;
				if(result == 1){//Ejercito selecionado pierde
					casualtiesFromArmy = (selectedArmy.getCost() * 25) / 100; 
					casualtiesFromEnemy = (enemy.getCost() * 50) / 100; 
				}else if(result == 2){//Ejercito selecionado gana
					casualtiesFromArmy = (selectedArmy.getCost() * 50) / 100; 
					casualtiesFromEnemy = (enemy.getCost() * 25) / 100;
				}
				selectedArmy.setDamage(casualtiesFromEnemy);
				enemy.setDamage(casualtiesFromArmy);
				textB = 
						"ATTACKER LOST" + " " +
						casualtiesFromEnemy + " " + "LOSSES" + " " +
						"DEFENDER LOST" + " " +
						casualtiesFromArmy + " " + "LOSSES";
				
				if(defeatArmy.getPlayer().getId() != player.getId()){
					attackerWins = true;
				}else{
					attackerLost = true;
				}
				
			}
			//Si el atacante gana, le quita el territorio al defensor
			if(selectedArmy != null && result > 1){
				selectedArmy.getKingdom().setState(0);//control
				selectedArmy.getKingdom().setTarget(-1);
			}
		}
		
		//No hay ejercito enemigo pues se combate en un territorio vacio
		else{
			if(result > 1){
				//Resolucion del combate
				int newState = selectedArmy.getKingdom().getState()+1;
				int totalStates = selectedArmy.getKingdom().getTotalStates();
				if(newState < totalStates){
					selectedArmy.getKingdom().setState(newState);
					selectedArmy.getKingdom().setTarget(-1);
				}else{//Conquista
					defeatPlayer = getPlayerByKingdom(playerList, selectedArmy.getKingdom());
					
					selectedArmy.getKingdom().setState(0);
					selectedArmy.getKingdom().setTarget(-1);
					addNewConquest(playerList, player, selectedArmy.getKingdom());
					
					//Cambio de capital
					changeCapital = defeatPlayer != null && defeatPlayer.changeCapital();
					
					//Eliminacion jugador
					deletePlayer = defeatPlayer != null && defeatPlayer.getCapitalkingdom() == null;
				}
			}
		}
		
		
		
		
		
		
		
		
		
		
		
		if(attackerWins){
			String message = "ATTACKER WINS";
			
			if(enemy != null){
				//dataSender.addNotification(enemy.getPlayer().getName(), message);
			}
		}
		if(attackerLost){
			String message =  "ATTACKER DEFEAT";
			if(enemy != null){
				//dataSender.addNotification(enemy.getPlayer().getName(), message);
			}
		}
		if(attackerHasDestroyed){
			String message =  "ATTACKER HAS DESTROYED";
			if(enemy != null){
				//dataSender.addNotification(enemy.getPlayer().getName(), message);
			}
		}
		if(arrackerHasBeendestroyed){
			String message =  "ATTACKER HAS BEEN DESTROYED";
			if(enemy != null){
				//dataSender.addNotification(enemy.getPlayer().getName(), message);
			}
		}
		
		if(changeCapital){
			String message = "CHANGE HIS CAPITAL";
			//dataSender.addNotification(defeatPlayer.getName(), message);
			
		}
		if(deletePlayer){
			String message = "YOU LOST THE GAME";
			//dataSender.addNotification(defeatPlayer.getName(), message);
		}
		
		
		
		if(defeatArmy != null){
			Kingdom defeatTarget = getBorderKingdom(playerList, defeatArmy);
			putArmyAtKingdom(defeatArmy, defeatTarget);
			resolveScape(playerList, player, defeatArmy);
		}
	}
	
	
	private int calculateDifficult(Terrain terrain, Army armyAtack, Army armyDefense){
		int value=0;
			
		int pAtack = armyAtack.getPower(terrain);
		int pDefense = armyDefense != null ? armyDefense.getPower(terrain):GameParams.TERRAIN_DEFENSE[terrain.getType()];
			
		value = GameParams.ROLL_SYSTEM-((armyAtack.getPower(terrain) * GameParams.ROLL_SYSTEM)/(pAtack+pDefense));
			
		return value;
	}
	
	private Army getEnemyAtKingdom(List<Player> playerList, Player player){
		if(player.getSelectedArmy() != null){
			Kingdom kingdom = player.getSelectedArmy().getKingdom();
			return getEnemyAtKingdom(playerList, player, kingdom);
		}else{
			return null;
		}
	}
	
	/**
	 * Devuelve true si el ejercito del reino que se pasa por parametro contiene un ejercito enemigo (Explorar fronteras)
	 */
	private Army getEnemyAtKingdom(List<Player> playerList, Player player, Kingdom kingdom){
		Army enemy = null;
		for(Player p : playerList){
			if(p.getId() != player.getId()){
				for(Army a : p.getArmyList()){
					if(a.getKingdom().getId() == kingdom.getId()){
						enemy = a;
						break;
					}
				}
			}
		}
		return enemy;
	}
	
	
	private Kingdom getBorderKingdom(List<Player> playerList, Army army) {
		//Comparo si alguno de los territorios adyacentes pertenece al derrotado
		Kingdom defeatTarget = null;
		for(Kingdom domain : army.getPlayer().getKingdomList()){
			for(Kingdom border : army.getKingdom().getBorderList()){
				if(domain.getId() == border.getId() 
						&& getEnemyAtKingdom(playerList, army.getPlayer(), border) == null){
					defeatTarget = domain;
					break;
				}
			}
		}
		return defeatTarget;
	}
	
	private Player getPlayerByKingdom(List<Player> playerList, Kingdom kingdom){
		Player player = null;
		for(int i = 0; i < playerList.size() && player == null; i++){
			for(int j = 0; j < playerList.get(i).getKingdomList().size() && player == null; j++){
				if(playerList.get(i).getKingdomList().get(j).getId() == kingdom.getId()){
					player = playerList.get(i);
				}
			}
		}
		return player;
	}
	
	private void addNewConquest(List<Player> playerList, Player player, Kingdom kingdom){
		//Elimino el territorio del domino de cualquier jugador
		for(Player p : playerList){
			p.removeKingdom(kingdom);
		}
		
		player.getKingdomList().add(kingdom);
	}
	
	
	private void resolveMovement(List<Player> playerList, Player player, Army selectedArmy){
		//getSelectedArmy().setX(getSelectedArmy().getKingdom().getX());
		//getSelectedArmy().setY(getSelectedArmy().getKingdom().getY());
		
		//Si tengo un ejercito en la zona y no soy yo ese ejercito me uno
		List <Army> armyFiendList = new ArrayList<Army>();
		for(int i = 0; i < player.getArmyList().size(); i++){
			if(player.getArmyList().get(i).getKingdom().getId() == selectedArmy.getKingdom().getId()){
				armyFiendList.add(player.getArmyList().get(i));
			}
		}
		if(armyFiendList.size() > 1){
			int cost = join(playerList, armyFiendList.get(0), armyFiendList.get(1));
			
			if(cost > 0){
				player.setGold(player.getGold()+cost);
			}
		
		}else{
			//Si hay un ejercito enemigo
			if(getEnemyAtKingdom(playerList, player) != null){
				
				combat(playerList, player, selectedArmy.getKingdom().getTerrainList().get(0), 
						selectedArmy,
						getEnemyAtKingdom(playerList, player));
			}else{
				//Si el territorio es mio
				if(!player.hasKingom(selectedArmy.getKingdom())){
					//Si es la IA, solo se muestra la ventana de combate si se va a producir un combate
					if(
						selectedArmy.getIaDecision().getDecision() == ActionIA.DECISION_ATACK
						||
						selectedArmy.getIaDecision().getDecision() == ActionIA.DECISION_MOVE_AND_ATACK
					){
						combat(playerList, player, selectedArmy.getKingdom().getTerrainList().get(0), selectedArmy, null);
					}
				}
			}
		}
	}
	
	private void resolveScape(List<Player> playerList, Player player, Army defeatArmy){
		//defeat.setX(defeat.getKingdom().getX());
		//defeat.setY(defeat.getKingdom().getY());
		defeatArmy.getKingdom().setTarget(-1);
		
		//Si hay un ejercito amigo, se unen
		//Si tengo un ejercito en la zona y no soy yo ese ejercito me uno
		List <Army> armyFiendList = new ArrayList<Army>();
		for(int i = 0; i < defeatArmy.getPlayer().getArmyList().size(); i++){
			if(defeatArmy.getPlayer().getArmyList().get(i).getKingdom().getId() == defeatArmy.getKingdom().getId()){
				armyFiendList.add(defeatArmy.getPlayer().getArmyList().get(i));
			}
		}
		if(armyFiendList.size() > 1){
			int cost = join(playerList, armyFiendList.get(0), armyFiendList.get(1));
			defeatArmy.getPlayer().setGold(player.getGold()+cost);
		}
	}
	
	private int join(List<Player> playerList, Army army1, Army army2){
		int cost = 0;
		for(Troop troop : army2.getTroopList()){
			if(army1.getTroopList().size() < GameParams.MAX_NUMBER_OF_TROOPS){
				troop.setSubject(false);
				army1.getTroopList().add(troop);
			}else{
				cost += GameParams.TROOP_COST[troop.getType()]/2;
			}
		}
		army1.setSelected(true);
		army1.setDefeat(army1.isDefeat() || army2.isDefeat());
		removeArmy(playerList, army2);
		
		return cost;
	}
	
	private void removeArmy(List<Player> playerList, Army army){
		for(Player player: playerList){
			for(int i = 0; i < player.getArmyList().size(); i++){
				if(player.getArmyList().get(i).getId() == army.getId()){
					player.getArmyList().remove(i);
					break;
				}
			}
		}
	}
	
	private void putArmyAtKingdom(Army army, Kingdom newKingdom){
		army.setLastKingdom(army.getKingdom());
		army.setKingdom(newKingdom);
	}

}
