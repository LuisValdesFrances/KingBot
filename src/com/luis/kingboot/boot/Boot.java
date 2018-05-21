package com.luis.kingboot.boot;

import com.luis.kingboot.connection.OnlineInputOutput;
import com.luis.kingboot.main.Main;
import com.luis.strategy.datapackage.scene.PreSceneData;
import com.luis.strategy.datapackage.scene.PreSceneListData;

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
			Thread.sleep(10000);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private void createScenary(){
		synchronized (mutex) {
			
			PreSceneListData preSceneListData =  OnlineInputOutput.getInstance().reviceAllPreSceneListData(OnlineInputOutput.URL_GET_PRE_SCENE_LIST);
			
			if(preSceneListData.getPreSceneDataList().size() < MAX_PREESCENARI){
				PreSceneData preSceneData = new PreSceneData();
				preSceneData.setMap(Main.getRandom(0, 2));
				preSceneData.setName("Boot map");
				preSceneData.setHost(name);
			}
			
			
		}
		
	}

}
