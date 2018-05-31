package com.luis.strategy.map;

public class Terrain extends MapObject{
	
	private int type;
	private boolean conquest;
	
	public Terrain( MapObject map, int type, boolean conquest) {
		super(map);
		this.type = type;
		this.conquest = conquest;
	}
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isConquest() {
		return conquest;
	}
	public void setConquest(boolean conquest) {
		this.conquest = conquest;
	}
	
	
	

}
