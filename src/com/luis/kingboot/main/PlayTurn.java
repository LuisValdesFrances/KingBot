package com.luis.kingboot.main;

import java.util.ArrayList;
import java.util.List;

import com.luis.kingboot.connection.OnlineInputOutput;
import com.luis.strategy.constants.GameParams;
import com.luis.strategy.map.ActionIA;
import com.luis.strategy.map.Army;
import com.luis.strategy.map.Kingdom;
import com.luis.strategy.map.Player;
import com.luis.strategy.map.Terrain;
import com.luis.strategy.map.Troop;

public class PlayTurn {
	
	public boolean play(GameState gameState) {
		
		String infoList = "";
		for(Player p : gameState.getGameScene().getPlayerList()){
			infoList += ("\n" + p.getName() + " Kingdom's: ");
			for(Kingdom k : p.getKingdomList()){
				infoList+=(k.getId() + " - ");
			}
		}
		System.out.println(infoList);
		
		Player player = 
				gameState.getGameScene().getPlayerList().get(gameState.getGameScene().getPlayerIndex());
		
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
		
		player.getActionIA().
			management(gameState.getGameScene().getMapObject(), 
			gameState.getGameScene().getPlayerList());
		
		for(int i = 0; i < player.getArmyList().size(); i++){
			
			Army selectedArmy = player.getArmyList().get(i);
			
			player.getActionIA().buildDecision(
					gameState.getGameScene().getPlayerList(), selectedArmy);
			
			if(selectedArmy.getIaDecision().getDecision() != ActionIA.DECISION_NONE){
				
				if(
						selectedArmy.getIaDecision().getDecision() == ActionIA.DECISION_MOVE ||
						selectedArmy.getIaDecision().getDecision() == ActionIA.DECISION_MOVE_AND_ATACK){
					
					for(Kingdom k : gameState.getGameScene().getKingdomList()){
						if(k.getId() == selectedArmy.getIaDecision().getKingdomDecision()){
							putArmyAtKingdom(selectedArmy, k);
						}
					}
				}
				resolveMovement(gameState, player, selectedArmy);
			}
		}
		
		return isFinishGame(gameState, player);
		
	}
	
	private boolean isFinishGame(GameState gameState, Player player){
		boolean end = true;
		for(int i = 0; i < gameState.getGameScene().getPlayerList().size() && end; i++){
			if(gameState.getGameScene().getPlayerList().get(i).getId() != 
					player.getId()){
				if(gameState.getGameScene().getPlayerList().get(i).getCapitalkingdom() != null){
					end = false;
				}
			}
		}
		return end;
	}
	
	private void combat(GameState gameState, List<Player> playerList, Player player, Terrain terrain, Army armyAtack, Army armyDefense){
		
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
		resolveCombat(gameState, playerList, player, armyAtack, result);
	}
	
	private void resolveCombat(GameState gameState, List<Player> playerList, Player player, Army selectedArmy, int result){
		boolean removeArmy = false;
		Player defeatPlayer = null;
		Army defeatArmy = null;
		Army enemy = getEnemyAtKingdom(playerList, player, selectedArmy);
		
		/*
		 * El ejercito ganador genera la mitad de su valor en daño al ejercito perdedor
		 * El ejercito perdedor genera la la cuarta parte de su daño al ejercito ganador
		 */
		boolean changeCapital = false;
		boolean deletePlayer = false;
		
		boolean attackerWins=false;
		boolean attackerLost=false;
		boolean attackerHasDestroyed=false;
		boolean arrackerHasBeendestroyed=false;
		
		//Hay ejercito enemigo
		if(enemy != null){
			//Resolucion del combate
			if(result > 1)
				defeatArmy = getEnemyAtKingdom(playerList, player, selectedArmy);
			else
				defeatArmy = selectedArmy;
			
			//Comparo si alguno de los territorios adyacentes pertenece al derrotado
			Kingdom defeatTarget = getBorderKingdom(playerList, defeatArmy);
			
			boolean aniquilation = 
				(result == 3 && defeatArmy.getPlayer().getId() != player.getId())
				||
				(result == 0 && defeatArmy.getPlayer().getId() == player.getId());
			
			if(defeatTarget == null || aniquilation){
				removeArmy = true;
				defeatArmy.setDefeat(true);
				//Masacre al defensor
				if(defeatArmy.getPlayer().getId() != player.getId()){
					attackerHasDestroyed = true;
				}
				//Masacrea al atacante
				else{
					arrackerHasBeendestroyed = true;
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
		
		String message = null;
		if(attackerWins){
			message = "Attacker win";
			
			if(enemy != null){
				//dataSender.addNotification(enemy.getPlayer().getName(), message);
				sendNotification(gameState, player.getName(), enemy.getPlayer().getName(), 
						1, OnlineInputOutput.CODE_NOTIFICATION_YOUR_ARMY_DEFEATED);
			}
		}
		if(attackerLost){
			message =  "Attacker loses";
			if(enemy != null){
				sendNotification(gameState, player.getName(), enemy.getPlayer().getName(), 
						1, OnlineInputOutput.CODE_NOTIFICATION_YOUR_ARMY_WON);
			}
		}
		if(attackerHasDestroyed){
			message =  "The attacker has destroyed the enemy";
			if(enemy != null){
				sendNotification(gameState, player.getName(), enemy.getPlayer().getName(), 
						1, OnlineInputOutput.CODE_NOTIFICATION_YOUR_ARMY_DESTROYED);
			}
		}
		if(arrackerHasBeendestroyed){
			message =  "The attacker has been destroyed";
			if(enemy != null){
				sendNotification(gameState, player.getName(), enemy.getPlayer().getName(),
						1, OnlineInputOutput.CODE_NOTIFICATION_YOUR_ARMY_DESTROYED_ENEMY);
			}
		}
		
		if(changeCapital){
			message = defeatPlayer.getName() + " change his capital";
			
			for(Player p : gameState.getGameScene().getPlayerList()){
				if(p != null && p.getId() != player.getId()){
					sendNotification(gameState, defeatPlayer.getName(), p.getName(), 
						1, OnlineInputOutput.CODE_NOTIFICATION_CHANGE_CAPITAL);
				}
			}
		}
		
		if(deletePlayer){
			message = "Player " + defeatPlayer.getName() + " lost the game";
			
			sendNotification(gameState, player.getName(), defeatPlayer.getName(),
					0, OnlineInputOutput.CODE_NOTIFICATION_YOU_LOST_GAME);
			
			//Notifico al resto de jugadores que uno ha perdido
			for(Player p : gameState.getGameScene().getPlayerList()){
				if(p != null && p.getId() != player.getId() && p.getId() != defeatPlayer.getId()){
					sendNotification(gameState, defeatPlayer.getName(), p.getName(), 
						1, OnlineInputOutput.CODE_NOTIFICATION_LOST_GAME);
				}
			}
		}
		if(message != null){
			System.out.println(message);
		}
		
		if(removeArmy){
			removeArmy(playerList, defeatArmy);
		}else{
			if(defeatArmy != null){
				resolveScape(playerList, player, defeatArmy);
			}
		}
	}
	
	
	private int calculateDifficult(Terrain terrain, Army armyAtack, Army armyDefense){
		int value=0;
			
		int pAtack = armyAtack.getPower(terrain);
		int pDefense = armyDefense != null ? armyDefense.getPower(terrain):GameParams.TERRAIN_DEFENSE[terrain.getType()];
			
		value = GameParams.ROLL_SYSTEM-((armyAtack.getPower(terrain) * GameParams.ROLL_SYSTEM)/(pAtack+pDefense));
			
		return value;
	}
	
	private Army getEnemyAtKingdom(List<Player> playerList, Player player, Army selectedArmy){
		if(selectedArmy != null){
			Kingdom kingdom = selectedArmy.getKingdom();
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
	
	
	private void resolveMovement(GameState gameState, Player player, Army selectedArmy){
		//getSelectedArmy().setX(getSelectedArmy().getKingdom().getX());
		//getSelectedArmy().setY(getSelectedArmy().getKingdom().getY());
		
		List<Player> playerList = gameState.getGameScene().getPlayerList();
		
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
			if(getEnemyAtKingdom(playerList, player, selectedArmy) != null){
				
				combat(gameState, playerList, player, selectedArmy.getKingdom().getTerrainList().get(0), 
						selectedArmy,
						getEnemyAtKingdom(playerList, player, selectedArmy));
			}else{
				//Si no el territorio es mio
				if(!player.hasKingom(selectedArmy.getKingdom())){
					if(
						selectedArmy.getIaDecision().getDecision() == ActionIA.DECISION_ATACK
						||
						selectedArmy.getIaDecision().getDecision() == ActionIA.DECISION_MOVE_AND_ATACK
					){
						combat(gameState, playerList, player, selectedArmy.getKingdom().getTerrainList().get(0), selectedArmy, null);
					}
				}
			}
		}
	}
	
	private void resolveScape(List<Player> playerList, Player player, Army defeatArmy){
		
		Kingdom defeatTarget = getBorderKingdom(playerList, defeatArmy);
		putArmyAtKingdom(defeatArmy, defeatTarget);
		
		
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
	
	//Notifications
	public void sendNotification(GameState gameState, String from, String to, int type, int message){
		
		String msg = "" + message;
		String t = "" + type;
		
		OnlineInputOutput.getInstance().sendNotifiation("" + gameState.getSceneData().getId(), from, to, msg, t);
	}

}
