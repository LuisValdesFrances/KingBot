package com.luis.strategy.map;

import java.util.ArrayList;
import java.util.List;

import com.luis.strategy.constants.GameParams;

public class Army extends MapObject{
	
	public Player player;
	private static int idCount;
	private int id;
	
	private int flag;
	
	public boolean defeat;
	
	private List<Troop> troopList;
	private Kingdom lastKingdom;
	private Kingdom kingdom;
	
	private int state;
	public static final int STATE_OFF = 0;
	public static final int STATE_ON = 1;
	
	//IA
	private IADecision iaDecision;
	
	public Army (MapObject map,
			Player player,
			Kingdom kingdom,
			int flag) {
		
		super(map);
		this.id = idCount++;
		this.player = player;
		this.troopList = new ArrayList<Troop>();
		this.kingdom = kingdom;
		this.lastKingdom = kingdom;
		this.flag = flag;
		
		if(getPlayer().getActionIA() != null){
			iaDecision = new IADecision();
		}
	}
	
	public void initTroops() {
		// Añado el minimo de tropas
		for (int i = 0; i < GameParams.TROOP_START.length; i++) {
			for (int j = 0; j < GameParams.TROOP_START[i]; j++) {
				getTroopList().add(new Troop(i, true));
			}
		}
	}
	
	public int getNumberTroops(int type){
		int n = 0;
		for(int i = 0; i < troopList.size(); i++){
			if(troopList.get(i).getType() == type)
				n++;
		}
		return n;
	}
	
	public int getPower(Terrain terrain){
		int power = 0;
		for(int i = 0; i < troopList.size(); i++){
			switch(troopList.get(i).getType()){
			case GameParams.INFANTRY:
				power += GameParams.INFANTRY_COMBAT[terrain.getType()];
				break;
			case GameParams.KNIGHT:
				power += GameParams.KNIGHTS_COMBAT[terrain.getType()];
				break;
			case GameParams.HARASSERES:
				power += GameParams.HARASSERS_COMBAT[terrain.getType()];
				break;
			case GameParams.SIEGE:
				power += GameParams.SIEGE_COMBAT[terrain.getType()];
				break;
			}
		}
		return power;
	}
	
	public int getCost(){
		int cost = 0;
		for(int i = 0; i < troopList.size(); i++){
			cost +=  GameParams.TROOP_COST [troopList.get(i).getType()];
		}
		return cost;
	}
	
	public void setDamage(int costTarget){
		int costCount = 0;
		for(int i = 0; i < troopList.size() && costCount < costTarget; i++){
			//Maquinas de asedio
			if(!troopList.get(i).isSubject() && troopList.get(i).getType() == GameParams.SIEGE){
				costCount+= GameParams.TROOP_COST[GameParams.SIEGE];
				troopList.remove(i);
				i--;
			}
		}
		
		for(int i = 0; i < troopList.size() && costCount < costTarget; i++){
			//Maquinas de asedio
			if(!troopList.get(i).isSubject() && troopList.get(i).getType() == GameParams.HARASSERES){
				costCount+= GameParams.TROOP_COST[GameParams.HARASSERES];
				troopList.remove(i);
				i--;
			}
		}
		
		for(int i = 0; i < troopList.size() && costCount < costTarget; i++){
			//Maquinas de asedio
			if(!troopList.get(i).isSubject() && troopList.get(i).getType() == GameParams.KNIGHT){
				costCount+= GameParams.TROOP_COST[GameParams.KNIGHT];
				troopList.remove(i);
				i--;
			}
		}
		
		for(int i = 0; i < troopList.size() && costCount < costTarget; i++){
			//Maquinas de asedio
			if(!troopList.get(i).isSubject() && troopList.get(i).getType() == GameParams.INFANTRY){
				costCount+= GameParams.TROOP_COST[GameParams.INFANTRY];
				troopList.remove(i);
				i--;
			}
		}
	}

	public Kingdom getKingdom() {
		return kingdom;
	}

	public void setKingdom(Kingdom kingdom) {
		this.kingdom = kingdom;
	}

	
	public Kingdom getLastKingdom() {
		return lastKingdom;
	}

	public void setLastKingdom(Kingdom lastKingdom) {
		this.lastKingdom = lastKingdom;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDefeat() {
		return defeat;
	}

	public void setDefeat(boolean defeat) {
		this.defeat = defeat;
	}

	public List<Troop> getTroopList() {
		return troopList;
	}

	public void setTroopList(List<Troop> troopList) {
		this.troopList = troopList;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public IADecision getIaDecision() {
		return iaDecision;
	}

	public void createIADecision() {
		this.iaDecision = new IADecision();
	}

	public static int getIdCount() {
		return idCount;
	}

	public static void setIdCount(int idCount) {
		Army.idCount = idCount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void discardTroop(int discardNumber){
		int discardCount = 0;
		
		//Obtengo el minimo de tropas
		int min = 0;
		for(int i = 0; i < GameParams.TROOP_START[i]; i++){
			min+=GameParams.TROOP_START[i];
		}
		
		for(int i = 0; 
			troopList.size() > min && discardCount > discardNumber; i++){
		
				if(!troopList.get(i).isSubject()){
				troopList.remove(i);
				discardCount++;
			}
		}
	}
	

	public class IADecision{
		private int decision;
		private int kingdomDecision;
		
		public int getDecision() {
			return decision;
		}

		public void setDecision(int decision) {
			this.decision = decision;
		}
		
		public int getKingdomDecision() {
			return kingdomDecision;
		}

		public void setKingdomDecision(int kingdomDecision) {
			this.kingdomDecision = kingdomDecision;
		}
	}
}
