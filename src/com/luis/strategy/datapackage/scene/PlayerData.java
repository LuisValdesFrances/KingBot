package com.luis.strategy.datapackage.scene;

import java.io.Serializable;
import java.util.List;

public class PlayerData implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String name;
	private int gold;
	private int capitalKingdom;
	private int flag;
	private boolean isIA;

	private List<ArmyData> armyList;
	private List<KingdomData> kingdomList;
	
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
	public int getGold() {
		return gold;
	}
	public void setGold(int gold) {
		this.gold = gold;
	}
	public int getCapitalKingdom() {
		return capitalKingdom;
	}
	public void setCapitalKingdom(int capitalKingdom) {
		this.capitalKingdom = capitalKingdom;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public boolean isIA() {
		return isIA;
	}
	public void setIA(boolean isIA) {
		this.isIA = isIA;
	}
	
	public List<ArmyData> getArmyList() {
		return armyList;
	}
	public void setArmyList(List<ArmyData> armyList) {
		this.armyList = armyList;
	}
	public List<KingdomData> getKingdomList() {
		return kingdomList;
	}
	public void setKingdomList(List<KingdomData> kingdomList) {
		this.kingdomList = kingdomList;
	}
}
