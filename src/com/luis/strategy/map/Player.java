package com.luis.strategy.map;

import java.util.ArrayList;
import java.util.List;

import com.luis.strategy.constants.GameParams;

public class Player {
	
	private static int idCount;
	
	private int id;
	private String name;
	private int gold;
	private int capitalKingdom;
	private int flag;
	private ActionIA actionIA;
	
	private List<Army> armyList;
	private List<Kingdom> kingdomList;
	
	public Player(String name, ActionIA actionIA, int flag, int capitalKingdom){
		this.id = idCount++;
		this.name = name;
		this.flag = flag;
		this.capitalKingdom = capitalKingdom;
		this.armyList = new ArrayList<Army>();
		this.kingdomList = new ArrayList<Kingdom>();
		this.actionIA = actionIA;
		
		if(actionIA!= null){
			actionIA.setPlayer(this);
		}
	}
	
	public boolean hasKingom(Kingdom kingdom){
		boolean k = false;
		for(int i = 0; !k && i < kingdomList.size(); i++){
			if(kingdomList.get(i).getId() == kingdom.getId())
				k= true;
		}
		return k;
	}
	
	public void removeKingdom(Kingdom kingdom){
		for(int i = 0; i < kingdomList.size(); i++){
			if(kingdomList.get(i).getId() == kingdom.getId()){
				kingdomList.remove(i);
			}
		}
	}
	
	public Army getArmy(Kingdom kingdom){
		Army army = null;
		for(int i = 0; army == null && i < armyList.size(); i++){
			if(armyList.get(i).getKingdom().getId() == kingdom.getId()){
				army = armyList.get(i);
			}
		}
		return army;
	}
	

	public int getTaxes() {
		int tax = 0;
		for(Kingdom kingdom : getKingdomList()){
			tax += kingdom.getTaxes();
		}
		return tax;
	}
	
	public int getCost(boolean includeSubject) {
		int salaries = 0;
		for(Army army : getArmyList()){
			for(Troop troop : army.getTroopList()){
				if(!troop.isSubject() || includeSubject){
					//Los salarios de las tropas corresponden a la mitad de su coste
					salaries += GameParams.TROOP_COST[troop.getType()]/2;
				}
			}
		}
		return salaries;
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

	public List<Army> getArmyList() {
		return armyList;
	}

	public void setArmyList(List<Army> armyList) {
		this.armyList = armyList;
	}

	public List<Kingdom> getKingdomList() {
		return kingdomList;
	}

	public void setKingdomList(List<Kingdom> kingdomList) {
		this.kingdomList = kingdomList;
	}


	public int getFlag() {
		return flag;
	}


	public void setFlag(int flag) {
		this.flag = flag;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	//Falta seleccionar aquella con un nivel mayor
	public boolean changeCapital(){
		if(getCapitalkingdom() == null){
			for(Kingdom k : kingdomList){
				if(k.getTerrainList().get(k.getTerrainList().size()-1).getType() == GameParams.CITY){
					setCapitalKingdom(k.getId());
					return true;
				}
			}
		}
		return false;
	}

	public ActionIA getActionIA() {
		return actionIA;
	}

	public void setActionIA(ActionIA actionIA) {
		this.actionIA = actionIA;
	}

	public Kingdom getCapitalkingdom() {
		Kingdom kingdom = null;
		for(int i = 0; i < kingdomList.size() && kingdom == null; i++){
			if(kingdomList.get(i).getId() == capitalKingdom){
				kingdom = kingdomList.get(i);
			}
		}
		return kingdom;
	}

	public void setCapitalKingdom(int capitalKingdom) {
		this.capitalKingdom = capitalKingdom;
	}
	
	
	
	

}
