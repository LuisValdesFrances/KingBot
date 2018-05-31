package com.luis.strategy.map;

public class Troop {
	
	private static int idCount;
	private int id;
	private int type;
	//Subditos que no se pagan y no se pierden
	private boolean subject;
	
	public Troop(int type, boolean subject){
		this.type = type;
		this.id = idCount++;
		this.subject = subject;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isSubject() {
		return subject;
	}

	public void setSubject(boolean subject) {
		this.subject = subject;
	}
	
	

}
