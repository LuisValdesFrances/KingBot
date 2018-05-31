package com.luis.kingboot.main;

import com.luis.strategy.datapackage.scene.SceneData;
import com.luis.strategy.map.GameScene;

public class GameState {

	//Game configuration menu
	private int map;
	private PlayerConf[] playerConfList;
	
	//Game serial data
	private GameScene gameScene;
	private SceneData sceneData;
	
	//User configuration
	private String name;
	private String password;
	private int gameMode;
	
	//private boolean isOnlineGameOn;//Chequea si se esta en medio de una partida on line
	
	public static final int GAME_MODE_CAMPAING = 0;
	public static final int GAME_MODE_PLAY_AND_PASS = 1;
	public static final int GAME_MODE_ONLINE = 2;
	
	public void init(int gameMode, int map, int numPlayer){
		this.gameMode = gameMode;
		this.map = map;
		playerConfList = new PlayerConf[numPlayer];
		for (int i = 0; i < playerConfList.length; i++) {
			playerConfList[i] = new PlayerConf();
		}
	}
	
	public void init(int gameMode, SceneData sceneData){
		this.gameMode = gameMode;
		this.sceneData = sceneData;
		this.map = sceneData.getMap();
		playerConfList = new PlayerConf[sceneData.getPlayerDataList().size()];
		for (int i = 0; i < playerConfList.length; i++) {
			playerConfList[i] = new PlayerConf();
		}
	}

	public GameScene getGameScene() {
		return gameScene;
	}

	public void setGameScene(GameScene gameScene) {
		this.gameScene = gameScene;
	}

	public SceneData getSceneData() {
		return sceneData;
	}

	public void setSceneData(SceneData sceneData) {
		this.sceneData = sceneData;
	}

	public int getMap() {
		return map;
	}

	public void setMap(int map) {
		this.map = map;
	}

	
	public PlayerConf[] getPlayerConfList() {
		return playerConfList;
	}

	public void setPlayerConfList(PlayerConf[] playerConfList) {
		this.playerConfList = playerConfList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGameMode() {
		return gameMode;
	}

	public void setGameMode(int gameMode) {
		this.gameMode = gameMode;
	}



	public class PlayerConf{
		public String name; 
		public int flag; 
		public boolean IA;
		
	}
}
