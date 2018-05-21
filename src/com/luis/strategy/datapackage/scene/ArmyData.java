package com.luis.strategy.datapackage.scene;

import java.io.Serializable;
import java.util.List;

public class ArmyData implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<TroopData> troopList;
	private KingdomData kingdom;

	public List<TroopData> getTroopList() {
		return troopList;
	}

	public void setTroopList(List<TroopData> troopList) {
		this.troopList = troopList;
	}

	public KingdomData getKingdom() {
		return kingdom;
	}

	public void setKingdom(KingdomData kingdom) {
		this.kingdom = kingdom;
	}

	
}
