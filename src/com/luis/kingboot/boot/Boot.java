package com.luis.kingboot.boot;

import com.luis.kingboot.connection.OnlineInputOutput;
import com.luis.kingboot.main.Main;
import com.luis.strategy.datapackage.scene.PreSceneData;
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
		SceneListData sceneListData = OnlineInputOutput.getInstance().reviceSceneListData(name);
		for(SceneData sceneData : sceneListData.getSceneDataList()){
			
			if(sceneData.getNextPlayer().equals(name)){
				int playerIndex = sceneData.getPlayerIndex();
				
				SceneData sd = null;
					if(sceneData.getState() == 0){
						sd = OnlineInputOutput.getInstance().reviceSceneData(OnlineInputOutput.URL_GET_START_SCENE, ""+sceneData.getId());
					}else{
						sd = OnlineInputOutput.getInstance().reviceSceneData(OnlineInputOutput.URL_GET_SCENE, ""+sceneData.getId());
					}
				do{
					playerIndex = playerIndex+1%sd.getPlayerDataList().size();
				}
				while(sd.getPlayerDataList().get(playerIndex).getCapitalKingdom() == -1);
				sd.setState(1);
				sd.setPlayerIndex(playerIndex);
				sd.setNextPlayer(sd.getPlayerDataList().get(sd.getPlayerIndex()).getName());
				OnlineInputOutput.getInstance().sendDataPackage(OnlineInputOutput.URL_UPDATE_SCENE, sd);
				
				if(playerIndex==0)
					sceneData.setTurnCount(sceneData.getTurnCount()+1);
				System.out.println("Boot " + name + " has responded game");
			}
		}
		
		
	}

}
