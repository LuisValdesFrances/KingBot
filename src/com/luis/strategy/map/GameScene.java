package com.luis.strategy.map;

import java.util.List;

public class GameScene{
	
	private int map;
	private int playerIndex;
	private int turnCount;
	
	private List<Player>playerList;
	private List<Kingdom> kingdomList;
	
	private MapObject mapObject;
	
	public GameScene(int map) {
		this.map = map;
		mapObject = new MapObject(null) {
		};
	}
	
	public void cleanKingdomTarget(){
		//Log.i("Debug", "Clean army");
		for (Kingdom k : kingdomList) {
			k.setTarget(-1);
		}
	}
	
	public Kingdom getKingdom(int id){
		Kingdom kingdom = null;
		for(int i = 0; kingdom == null && i < kingdomList.size(); i++){
			if(kingdomList.get(i).getId() == id)
				kingdom= kingdomList.get(i);
		}
		return kingdom;
	}

	
	public int getMap() {
		return map;
	}

	public void setMap(int map) {
		this.map = map;
	}

	public List<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<Player> playerList) {
		this.playerList = playerList;
	}

	public List<Kingdom> getKingdomList() {
		return kingdomList;
	}

	public void setKingdomList(List<Kingdom> kingdomList) {
		this.kingdomList = kingdomList;
	}

	public int getPlayerIndex() {
		return playerIndex;
	}

	public void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}

	public int getTurnCount() {
		return turnCount;
	}

	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount;
	}

	public MapObject getMapObject() {
		return mapObject;
	}

	public void setMapObject(MapObject mapObject) {
		this.mapObject = mapObject;
	}
}