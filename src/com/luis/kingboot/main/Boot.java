package com.luis.kingboot.main;

import java.util.List;
import java.util.Random;

import com.luis.kingboot.connection.OnlineInputOutput;
import com.luis.strategy.data.DataKingdom;
import com.luis.strategy.data.GameBuilder;
import com.luis.strategy.datapackage.scene.PlayerData;
import com.luis.strategy.datapackage.scene.PreSceneData;
import com.luis.strategy.datapackage.scene.PreSceneListData;
import com.luis.strategy.datapackage.scene.SceneData;
import com.luis.strategy.datapackage.scene.SceneListData;
import com.luis.strategy.map.Player;

public class Boot extends Thread{
	
	private Object mutex;
	private String name;
	
	
	private static final int MAX_PREESCENARI = 5;
	
	public Boot(Object mutex, String name){
		this.mutex = mutex;
		this.name = name;
	}
	 
	@Override
	public void run(){
		super.run();
		while(true){
			try{
				if(Main.configCreateScenary){
					createScenary();
				}
				if(Main.configExcludeScenary != null){
					joinScenary();
				}
				play();
				
				long sleep;
				if(Main.TEST){
					sleep = 5000;
				}else{
					sleep = getRandom(Main.MIN_SLEEP, Main.MAX_SLEEP);
					System.out.println(name + " sleep " + (sleep/1000) + " seconds");
				}
				
				
				Thread.sleep(sleep);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	private void createScenary(){
		synchronized (mutex) {
			
			PreSceneListData preSceneListData =  OnlineInputOutput.getInstance().reviceAllPreSceneListData();
			
			if(preSceneListData != null && preSceneListData.getPreSceneDataList().size() < MAX_PREESCENARI){
				int map = Main.getRandom(0, 4);
				String host = name;
				String sceneName = "Scene by " + name;
				
				OnlineInputOutput.getInstance().sendPreScene(""+map, host, sceneName);
				
				System.out.println(name + " has created a new scene");
			}
		}
	}
	
	private void joinScenary(){
		synchronized (mutex) {
			
			PreSceneListData preSceneListData =  OnlineInputOutput.getInstance().revicePreSceneListData(name);
			
			if(preSceneListData != null){
				for(int i = 0; i < preSceneListData.getPreSceneDataList().size(); i++){
					
					//Obtengo al usuario que ha creado la escena
					String author = preSceneListData.getPreSceneDataList().get(i).getHost();
					
					//Si el escenario esta creado por un boot, no se une
					//Si el escenario ya contiene a boot, no se une
					//Si el escenario esta en la lista de excluidos, no se une
					boolean isCreatedByBoot = false;
					for(int j = 0; j < Main.BOOT_NAME_LIST.length && !isCreatedByBoot; j++){
						isCreatedByBoot = author.equals(Main.BOOT_NAME_LIST[j]);
					}
					
					boolean isBootPresent = false;
					List<PlayerData> players = preSceneListData.getPreSceneDataList().get(i).getPlayerList();
					for(int j = 0; j < players.size() && !isBootPresent; j++){
						isBootPresent = players.get(j).getName().equals(getName());
					}
					
					if(!isCreatedByBoot && !isBootPresent && !isExcludedScenary(preSceneListData.getPreSceneDataList().get(i).getId())){
						//Me uno a la partida
						PreSceneData preSceneData = preSceneListData.getPreSceneDataList().get(i);
						int insCount = (preSceneData.getPlayerList().size());
						
						if(insCount ==  DataKingdom.INIT_MAP_DATA[preSceneData.getMap()].length){
							System.out.println("Scene " + preSceneData.getId() + " ya contiene el total de jugadores");
						}else{
							System.out.println(name + " se ha unido a la escena " + preSceneData.getId() + " creada por " + preSceneData.getHost());
							String create = (insCount+1) == DataKingdom.INIT_MAP_DATA[preSceneData.getMap()].length ? "create" : null;
							OnlineInputOutput.getInstance().sendInscription(""+preSceneData.getId(), name, create);
						}
					}
				}
			}
		}
	}
	
	private boolean isExcludedScenary(int id){
		for(int i = 0; i < Main.configExcludeScenary.length; i++){
			if(id == Integer.parseInt(Main.configExcludeScenary[i].trim())){
				return true;
			}
		}
		return false;
	}
	
	private void play(){
		synchronized (mutex) {
			//recivo todas las partidas en las que participo
			SceneListData sceneListData = OnlineInputOutput.getInstance().reviceSceneListData(name, "active");
			if(sceneListData != null){
				for(SceneData sceneData : sceneListData.getSceneDataList()){
					//Si el siguiente jugador soy yo:
					if(sceneData.getNextPlayer().equals(name)){
						
						//espero un poco (Tema de notificaciones)
						try{
							Thread.sleep(3000);
						}catch(Exception e){
							e.printStackTrace();
						}
						
						PlayTurn playTurn = null;
						
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
						}
						
						System.err.println("\nTurn " + sceneData.getTurnCount() + " for " + sceneData.getNextPlayer() + " for scene " + sceneData.getId());
						
						
						playTurn = new PlayTurn();
						boolean isFinishGame = playTurn.play(gameState);
						
						SceneData sd = null;
						if(!isFinishGame){
							
							int playerIndex = gameState.getGameScene().getPlayerIndex();
							do{
								playerIndex = (playerIndex+1)%gameState.getGameScene().getPlayerList().size();
							}
							while(gameState.getGameScene().getPlayerList().get(playerIndex).getCapitalkingdom() == null);
							
							if(playerIndex==0){
								gameState.getGameScene().setTurnCount(gameState.getGameScene().getTurnCount()+1);
							}
							gameState.getGameScene().setPlayerIndex(playerIndex);
							sd = GameBuilder.getInstance().buildSceneData(gameState, 1);
						
							System.out.println(name + " has responded game");
						}else{
							sd = GameBuilder.getInstance().buildSceneData(gameState, 2);
							
							System.out.println(name + " has finish game");
						}
						OnlineInputOutput.getInstance().sendDataPackage(OnlineInputOutput.URL_UPDATE_SCENE, sd);
						
						/*
						String message = "TURN:" + (gameState.getGameScene().getTurnCount()+1);
						playTurn.sendNotification(gameState, 
								gameState.getGameScene().getPlayerList().get(playerIndex).getName(), message);
						*/
						
					}
				}
			}
		}
	}
	
	private Random random;// = new Random(0);
    //Obtiene un randon entre el primer parametro(Numero menor) y el segundo parametro(numero mayor). 
    //Ambos incluidos.
    public long getRandom(long _i0, long _i1) {
        if(random == null) random = new Random();
        return _i0 + Math.abs(random.nextLong() % (1 + _i1 - _i0));
    }

}
