package com.luis.strategy.datapackage.scene;

import java.io.Serializable;

public class BuildingData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int type;
	private int state;
	private int level;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	

}
