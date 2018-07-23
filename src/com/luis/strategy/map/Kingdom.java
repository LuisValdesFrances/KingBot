package com.luis.strategy.map;

import java.util.ArrayList;
import java.util.List;

import com.luis.strategy.constants.GameParams;

public class Kingdom extends MapObject{
	
	private String name;
	private int id;
	
	private boolean protectedByFaith;
	
	private List<Terrain> terrainList;
	private List<Kingdom> borderList;
	
	//Numero de combates por territorios = MONTANYA, BOSQUE, LLANO, CIUDAD
	private int state;
	private int totalStates;
	
	private int target;
	public static final int TARGET_BATTLE = 0;
	public static final int TARGET_DOMAIN = 1;
	public static final int TARGET_AGGREGATION = 2;
	
	private CityManagement cityManagement;
	
	
	public Kingdom(MapObject map) {
		super(map);
		
		this.state = 0;
		
		this.terrainList = new ArrayList<Terrain>();
		this.borderList = new ArrayList<Kingdom>();
		
		this.protectedByFaith = false;
		
		this.totalStates = terrainList.size();
		
		this.target = -1;
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
				(hasTerrain(GameParams.CITY)) ||
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
		for(int i = 0; i < borderList.size(); i++){
			if(borderList.get(i) == null){
				borderList.remove(i);
				i--;
			}
		}
		this.borderList = borderList;
	}
	
	public int getTaxes(){
		int tax = 0;
		
		for(Terrain terrain : getTerrainList()){
			
			//Sumo propiedades ciudades:
			int marketMod = 0;
			if(terrain.getType() == GameParams.CITY){
				if(cityManagement.getBuildingList().get(GameParams.MARKET).getActiveLevel() > -1){
					marketMod = GameParams.MARKET_TAX[cityManagement.getBuildingList().get(GameParams.MARKET).getActiveLevel()];
				}
			}
			tax += GameParams.TERRAIN_TAX[terrain.getType()]+marketMod;
		}
		return tax;
	}
	
	public int getDefense(int progressIndex){
		int defense = 0;
		
		//Sumo propiedades ciudades:
		int towerMod = 0;
		if(terrainList.get(progressIndex).getType() == GameParams.CITY){
			if(cityManagement.getBuildingList().get(GameParams.TOWER).getActiveLevel() > -1){
			 towerMod = GameParams.TOWER_DEFENSE[cityManagement.getBuildingList().get(GameParams.TOWER).getActiveLevel()];
			}
		}
		
		defense = GameParams.TERRAIN_DEFENSE[terrainList.get(progressIndex).getType()]+towerMod;
		
		return defense;
	}
	
	public CityManagement getCityManagement() {
		return cityManagement;
	}

	public void setCityManagement(CityManagement cityManagement) {
		if(isACity()){
			if(cityManagement != null)
				this.cityManagement = cityManagement;
			else
				this.cityManagement = new CityManagement();
		}
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
	
	public boolean isProtectedByFaith() {
		return protectedByFaith;
	}

	public void setProtectedByFaith(boolean protectedByFaith) {
		this.protectedByFaith = protectedByFaith;
	}

	
}
