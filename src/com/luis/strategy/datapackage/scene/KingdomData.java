package com.luis.strategy.datapackage.scene;

import java.io.Serializable;

public class KingdomData  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int state;
	
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
}
