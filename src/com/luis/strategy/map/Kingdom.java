package com.luis.strategy.map;

import java.util.ArrayList;
import java.util.List;

import com.luis.strategy.constants.GameParams;

public class Kingdom extends MapObject{
	
	private String name;
	private int id;
	
	private List<Terrain> terrainList;
	private List<Kingdom> borderList;
	
	//Numero de combates por territorios = MONTANYA, BOSQUE, LLANO, CIUDAD
	private int state;
	private int totalStates;
	
	private int target;
	public static final int TARGET_BATTLE = 0;
	public static final int TARGET_DOMAIN = 1;
	public static final int TARGET_AGGREGATION = 2;
	
	
	public Kingdom(MapObject map) {
		super(map);
		
		this.state = 0;
		
		terrainList = new ArrayList<Terrain>();
		borderList = new ArrayList<Kingdom>();
		
		totalStates = terrainList.size();
		
		target = -1;
	}
	
	public boolean hasTerrain(int type){
		boolean hasTerrain = false;
		for(int i = 0; i < terrainList.size() && !hasTerrain; i++){
			if(terrainList.get(i).getType()==type){
				hasTerrain = true;
			}
		}
		return hasTerrain;
	}
	
	public boolean isACity(){
		return 
				(hasTerrain(GameParams.SMALL_CITY)) ||
				(hasTerrain(GameParams.MEDIUM_CITY)) ||
				(hasTerrain(GameParams.BIG_CITY)) ||
				(hasTerrain(GameParams.CASTLE));
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Terrain> getTerrainList() {
		return terrainList;
	}

	public void setTerrainList(List<Terrain> terrainList) {
		this.terrainList = terrainList;
		totalStates = terrainList.size();
	}

	public List<Kingdom> getBorderList() {
		return borderList;
	}

	public void setBorderList(List<Kingdom> borderList) {
		this.borderList = borderList;
	}

	public int getTarget() {
		return target;
	}

	public void setTarget(int target) {
		this.target = target;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getTotalStates() {
		return totalStates;
	}

	public void setTotalStates(int totalStates) {
		this.totalStates = totalStates;
	}

	
}
