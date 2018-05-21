package com.luis.strategy.datapackage.scene;

import java.io.Serializable;

public class TroopData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int type;
	private boolean subject;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isSubject() {
		return subject;
	}
	public void setSubject(boolean subject) {
		this.subject = subject;
	}
}
