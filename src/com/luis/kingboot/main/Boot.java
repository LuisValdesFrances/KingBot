package com.luis.kingboot.main;

import java.util.Random;

import com.luis.kingboot.connection.OnlineInputOutput;
import com.luis.strategy.data.DataKingdom;
import com.luis.strategy.data.GameBuilder;
import com.luis.strategy.datapackage.scene.PreSceneData;
import com.luis.strategy.datapackage.scene.PreSceneListData;
import com.luis.strategy.datapackage.scene.SceneData;
import com.luis.strategy.datapackage.scene.SceneListData;

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
				createScenary();
				joinScenary();
				play();
				
				long sleep;
				if(Main.TEST){
					sleep = 3000;
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
					
					//Si es un boot no se une a la partida del boot
					boolean found = false;
					for(int j = 0; j < Main.BOOT_NAME_LIST.length && !found; j++){
						found = author.equals(Main.BOOT_NAME_LIST[j]);
					}
					
					if(found){
						preSceneListData.getPreSceneDataList().remove(i);
						i--;
					}
				}
				
				//Me uno a la partida
				if(preSceneListData.getPreSceneDataList().size() > 0){
					PreSceneData preSceneData = preSceneListData.getPreSceneDataList().get(0);
					int insCount = (preSceneData.getPlayerCount());
					
					String create = null;
					if(insCount+1 ==  DataKingdom.INIT_MAP_DATA[preSceneData.getMap()].length){
						create = "create";
						System.out.println("Scene " + preSceneData.getId() + " ya contiene el total de jugadores");
					}
					
					System.out.println(name + " se ha unido a la escena " + preSceneData.getId() + " creada por " + preSceneData.getHost());
					OnlineInputOutput.getInstance().sendInscription(""+preSceneData.getId(), name, create);
				}
			}
		}
	}
	
	private void play(){
		synchronized (mutex) {
			//recivo todas las partidas en las que participo
			SceneListData sceneListData = OnlineInputOutput.getInstance().reviceSceneListData(name);
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
						
						System.err.println("\n\nTurn " + sceneData.getTurnCount() + " for " + sceneData.getNextPlayer() + " for scene " + sceneData.getId());
						
						
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
