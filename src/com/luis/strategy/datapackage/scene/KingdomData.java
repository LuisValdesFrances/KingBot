package com.luis.strategy.datapackage.scene;

import java.io.Serializable;
import java.util.List;

public class KingdomData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int state;
	private boolean protectedByFaith;
	
	private List<BuildingData> buildingList;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public List<BuildingData> getBuildingList() {
		return buildingList;
	}
	public void setBuildingList(List<BuildingData> buildingList) {
		this.buildingList = buildingList;
	}
	public boolean isProtectedByFaith() {
		return protectedByFaith;
	}
	public void setProtectedByFaith(boolean protectedByFaith) {
		this.protectedByFaith = protectedByFaith;
	}
	
	
}
