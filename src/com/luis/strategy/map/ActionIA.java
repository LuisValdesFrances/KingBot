package com.luis.strategy.map;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.luis.strategy.constants.GameParams;

public class ActionIA {
	
	private Player player;
	
	public static final int DECISION_NONE = 0;//No hace nada
	public static final int DECISION_ATACK = 1;//Continua con la conquista
	public static final int DECISION_MOVE = 2;//Mueve solamente
	public static final int DECISION_MOVE_AND_ATACK = 3;//Mueve y ataca
	
	private Random random;// = new Random(0);
    //Obtiene un randon entre el primer parametro(Numero menor) y el segundo parametro(numero mayor). 
    //Ambos incluidos.
    public int getRandom(int _i0, int _i1) {
        if(random == null) random = new Random();
        return _i0 + Math.abs(random.nextInt() % (1 + _i1 - _i0));
    }
	
	
	public boolean scape(Army attacker, Army defenser){
		
		if(defenser.getPower(defenser.getKingdom().getTerrainList().get(0)) >= attacker.getPower(defenser.getKingdom().getTerrainList().get(0))){
			
			System.out.println(defenser.getPlayer().getName() + " tiene un ejercito superior por lo que COMBATE batalla");
			return false;
		}else{
			boolean scape = getRandom(0, 100)>=75;
			
			if(scape){
				System.out.println(defenser.getPlayer().getName() + " tiene un ejercito inferior por lo que HUYE batalla");
			}else{
				System.out.println(defenser.getPlayer().getName() + " tiene un ejercito inferior PERO COMBATE la batalla");
			}
			
			return scape;
		}
	}
	
	/**
	 * Crea ejercitos nuevos y compra tropas a los que se encuentren en ciudades
	 */
	public void management(MapObject map, List<Player> playerList){
		/*
		int cost = player.getCost(false);
		int salary = player.getTaxes();
		int gold = player.getGold();
		*/
		boolean canBuy;//Controla que haya alguna ciudad libre (Generar ejercito )o haya algún ejercito en una ciudad (Aquirir tropas)
		
		//Presupuestos
		int armyBudget = (int)(player.getGold()*0.30f);
		int troopBudget = (int)(player.getGold()*0.30f);
		
		do{
			canBuy = false;
			for(Kingdom k : player.getKingdomList()){
				//Busco si la ciudad esta libre
				boolean isFree = true;
				for(Player player : playerList){
					for(Army army : player.getArmyList()){
						if(army.getKingdom().getId() == k.getId()){
							isFree = false;
						}
					}
				}
				if(isFree && k.isACity()){
					canBuy = true;
					
					if(armyBudget >= GameParams.ARMY_COST){
						System.out.println(player.getName() +  " has recruited a new army");
						
						armyBudget -= GameParams.ARMY_COST;
						player.setGold(player.getGold()-GameParams.ARMY_COST);
						
						Army army = new Army(
								map, 
								player,
								k,
								player.getFlag());
						army.initTroops();
						player.getArmyList().add(army);
					}
				}
			}
		}while(armyBudget >= GameParams.ARMY_COST && canBuy);
		
		
		do{
			canBuy = false;
			for(Army army : player.getArmyList()){
				if(		army.getTroopList().size() < GameParams.MAX_NUMBER_OF_TROOPS &&
						player.hasKingom(army.getKingdom()) && 
						army.getKingdom().isACity()){
					canBuy = true;
					int troop = getRandom(0, GameParams.SIEGE);
					//Si sigo teniendo presupuesto, compro:
					if(troopBudget >= GameParams.TROOP_COST[troop]){
						String text = player.getName() +  " has been acquired a new troop";
						System.out.println(text);
						//NotificationBox.getInstance().addMessage(text);
						troopBudget-= GameParams.TROOP_COST[troop];
						player.setGold(player.getGold()-GameParams.TROOP_COST[troop]);
						army.getTroopList().add(new Troop(troop, false));
					}
				}
			}
		}while(troopBudget >= GameParams.TROOP_COST[GameParams.HARASSERES] && canBuy);
	}
	
	public void discard(){
		do{
			for(Army army: player.getArmyList()){
				Troop troop = army.getTroopList().get(army.getTroopList().size()-1);
				if(!troop.isSubject()){
					String text = player.getName() +  " descarta tropa: " + troop.getType();
					System.out.println(text);
					//NotificationBox.getInstance().addMessage(text);
					player.setGold(army.getPlayer().getGold() + GameParams.TROOP_COST[troop.getType()]);
					army.getTroopList().remove(army.getTroopList().size()-1);
				}
			}
		}while(player.getGold() < 0);
	}
	
	public void buildDecision(List<Player> playerList, Army army) {
		
		if(army != null){
			// Orden de prioridades
			
			// Sigo con las conquistas a medias(Si procede)
			if(army.getKingdom().getState() > 0 && !player.hasKingom(army.getKingdom())){
				army.getIaDecision().setDecision(DECISION_ATACK);
				return;
			}
	
			// Muevo y ataco al ejercito enemigo mas debil(Si procede)
			for (Kingdom k : army.getKingdom().getBorderList()) {
				Army enemy = getArmyAtKingdom(playerList, k);
				if(enemy != null && enemy.getPlayer().getId() != player.getId()){
					// Comparo fuerzas
					if (army.getPower(k.getTerrainList().get(0)) > enemy.getPower(k.getTerrainList().get(0))) {
						//Si mi fuerza es mayor, hay un 70% de pos de atacar
						int r = getRandom(0, 100);
						if(r >= 75){
							army.getIaDecision().setDecision(DECISION_MOVE_AND_ATACK);
							army.getIaDecision().setKingdomDecision(k.getId());
						return;
						}
					} else if (army.getPower(k.getTerrainList().get(0)) == enemy.getPower(k.getTerrainList().get(0))) {
						if (getRandom(0, 100) >= 50) {
							army.getIaDecision().setDecision(DECISION_MOVE_AND_ATACK);
							army.getIaDecision().setKingdomDecision(k.getId());
							return;
						}
					}
				}
			}
			
	
			// Inicio una conquista si el territorio en el que estoy es mas debil(Si procede)
			if(!player.hasKingom(army.getKingdom()) && comparePower(army, army.getKingdom())){
				army.getIaDecision().setDecision(DECISION_ATACK);
				return;
			}
	
			// Muevo y ataco a un territorio VACIO mas debil(Si procede)
			Kingdom target = getKingdomForeigner(playerList, army, false);
			if(target != null){
				army.getIaDecision().setDecision(DECISION_MOVE_AND_ATACK);
				army.getIaDecision().setKingdomDecision(target.getId());
				return;
			}
				
			
			
			// Muevo a una de mis ciudades libres, si no estoy en una de mis ciudades(Si procede)
			target = getKingdomDomainCity(playerList, army, false);
			if(!army.getKingdom().isACity() && target != null){
				army.getIaDecision().setDecision(DECISION_MOVE);
				army.getIaDecision().setKingdomDecision(target.getId());
				return;
			}
			
			
			// Muevo a uno de mis territorios(Si procede)
			target = getKingdomDomain(playerList, army, false);
			if(target != null){
				army.getIaDecision().setDecision(DECISION_MOVE);
				army.getIaDecision().setKingdomDecision(target.getId());
				return;
			}
			
			// Random
			army.getIaDecision().setDecision(-1);
			int r = getRandom(0, 100);
			if(r >= 25){//Me quedo donde estoy
				army.getIaDecision().setKingdomDecision(army.getKingdom().getId());
			}else{
				r = getRandom(0, army.getKingdom().getBorderList().size()-1);
				army.getIaDecision().setKingdomDecision(army.getKingdom().getBorderList().get(r).getId());
				return;
			}
		}
	}
	
	public Army getActiveArmy(List<Player> playerList){
		Army activeArmy = null;
		for(int i = 0; i < player.getArmyList().size() && activeArmy == null; i++){
			if(player.getArmyList().get(i).getState() == Army.STATE_ON){
				activeArmy = player.getArmyList().get(i);
			}
		}
		
		buildDecision(playerList, activeArmy);
		
		if(activeArmy != null && activeArmy.getIaDecision().getDecision() == DECISION_NONE){
			return null;
		}else{
			return activeArmy;
		}
	}
	
	public boolean isKingdomToMove(GameScene map, Kingdom kingdom){
		
		switch(getSelectedArmy().getIaDecision().getDecision()){
			case DECISION_ATACK:
				return getSelectedArmy().getKingdom().getId() == kingdom.getId();
			
			case DECISION_MOVE:
			case DECISION_MOVE_AND_ATACK:
				return getSelectedArmy().getIaDecision().getKingdomDecision() == kingdom.getId();
			default:
				int r = getRandom(0, getSelectedArmy().getKingdom().getBorderList().size()-1);
				return getSelectedArmy().getKingdom().getBorderList().get(r).getId() == kingdom.getId();
		}
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	private Army getSelectedArmy(){
		Army selected = null;
		for(Army army : player.getArmyList()){
			if(army.isSelected()){
				selected = army;
				break;
			}
		}
		return selected;
	}
	
	/**
	 * Compara si el poder del ejercito es superior al del kingdom
	 * @param army
	 * @param kingdom
	 * @return
	 */
	private boolean comparePower(Army army, Kingdom kingdom){
		boolean higherPower = true;
		
		for(Terrain terrain : kingdom.getTerrainList()){
			if(army.getPower(terrain) < GameParams.TERRAIN_DEFENSE[terrain.getType()]){
				higherPower = false;
			}
		}
		return higherPower;
	}
	
	/**
	 * Devuelve el ejercito del reino
	 * @param playerList
	 * @param kingdom
	 * @return
	 */
	private Army getArmyAtKingdom(List<Player> playerList, Kingdom kingdom){
		Army army = null;
		for(Player player : playerList){
			army = player.getArmy(kingdom);
			if(army != null)
				break;
		}
		return army;
	}
	
	/**
	 * Devuelve una ciudad adyacente del dominio aleatorio
	 * @param army
	 * @return
	 */
	private Kingdom getKingdomDomainCity(List<Player> playerList, Army army, boolean includeEnemyArmy){
		
		List <Kingdom> dTargetList = new ArrayList<Kingdom>();
		for(Kingdom k : army.getKingdom().getBorderList()){
			if(army.getPlayer().hasKingom(k) && k.isACity() && (getArmyAtKingdom(playerList, k) == null || includeEnemyArmy)){
				dTargetList.add(k);
			}
		}
		
		if(dTargetList.isEmpty()){
			return null;
		}else{
			int r = getRandom(0, dTargetList.size()-1);
			return dTargetList.get(r);
		}
	}
	
	/**
	 * Devuelve un territorio del dominio aleatorio
	 * @param army
	 * @return
	 */
	private Kingdom getKingdomDomain(List<Player> playerList, Army army, boolean includeEnemyArmy){
		List <Kingdom> dTargetList = new ArrayList<Kingdom>();
		for(Kingdom k : army.getKingdom().getBorderList()){
			if(army.getPlayer().hasKingom(k) && (getArmyAtKingdom(playerList, k) == null || includeEnemyArmy)){
				dTargetList.add(k);
			}
		}
		
		if(dTargetList.isEmpty()){
			return null;
		}else{
			int r = getRandom(0, dTargetList.size()-1);
			return dTargetList.get(r);
		}
	}
	
	/**
	 * Devuelve un territorio que no es del dominio aleatorio mas debil
	 * @param army
	 * @return
	 */
	private Kingdom getKingdomForeigner(List<Player> playerList, Army army, boolean includeEnemyArmy){
		List <Kingdom> dTargetList = new ArrayList<Kingdom>();
		for(Kingdom k : army.getKingdom().getBorderList()){
			if(!army.getPlayer().hasKingom(k) && (getArmyAtKingdom(playerList, k) == null || includeEnemyArmy)){
				if(comparePower(army, k)){
					dTargetList.add(k);
				}
			}
		}
		
		if(dTargetList.isEmpty()){
			return null;
		}else{
			int r = getRandom(0, dTargetList.size()-1);
			return dTargetList.get(r);
		}
	}
	
	

}
