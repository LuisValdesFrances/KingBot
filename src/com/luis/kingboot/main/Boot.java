package com.luis.kingboot.main;

import com.luis.kingboot.connection.OnlineInputOutput;
import com.luis.strategy.data.GameBuilder;
import com.luis.strategy.datapackage.scene.PreSceneListData;
import com.luis.strategy.datapackage.scene.SceneData;
import com.luis.strategy.datapackage.scene.SceneListData;

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
				
				playTurn = new PlayTurn();
				playTurn.play(gameState);
				
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
				
				String message = "TURN:" + (gameState.getGameScene().getTurnCount()+1);
				playTurn.sendNotification(gameState, 
						gameState.getGameScene().getPlayerList().get(playerIndex).getName(), message);
				
				System.out.println(name + " has responded game");
			}
		}
		
		
	}

	
	

	

}
