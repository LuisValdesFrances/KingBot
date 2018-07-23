package com.luis.strategy.constants;

public class GameParams {
	
	//Id terreno
	public static final int PLAIN = 0;
	public static final int FOREST = 1;
	public static final int MONTAIN = 2;
	public static final int CITY = 3;
	public static final int CASTLE = 4;
	
	public static final int[] INFANTRY_COMBAT = 	new int[]{3, 2, 1, 1, 1};
	public static final int[] KNIGHTS_COMBAT = 		new int[]{5, 2, 1, 1, 2};
	public static final int[] HARASSERS_COMBAT = 	new int[]{1, 2, 2, 1, 2};
	public static final int[] SIEGE_COMBAT = 		new int[]{0, 0, 0, 6, 6};
	
	public static final int[] TERRAIN_DEFENSE =		new int[]{3, 5, 8, 10, 8};
	//Ganancias reportadas por el territorio
	public static final int[] TERRAIN_TAX =			new int[]{50, 30, 10, 20, 10};
	
	public static final int[] TOWER_DEFENSE = 		new int[]{6, 12, 18};
	public static final int[] MARKET_TAX = 			new int[]{30, 60, 80};
	
	public static final int[] FAITH_CHECK =			new int[]{10, 20, 30};
	
	//Building
	public static final int TOWER = 0;
	public static final int MARKET = 1;
	public static final int CHURCH = 2;
	public static final int[][] BUILDING_COST = { 
												{ 200, 400, 800 },
												{ 300, 600, 1200 }, 
												{ 400, 800, 1600 }};

	public static final int[][] BUILDING_STATE = { 
												 { 3, 5, 7 }, 
												 { 4, 7, 10 },
												 { 5, 9, 13 }};
	
	//Id tropa
	public static final int INFANTRY = 0;
	public static final int KNIGHT = 1;
	public static final int HARASSERES = 2;
	public static final int SIEGE = 3;
	
	public static final int ARMY_COST = 220;
	public static final int[] TROOP_COST = 			new int[]{70, 120, 50, 110};//aun no
	
	//Minino de tropas por ejercito (No se pagan)
	public static final int[] TROOP_START = 		new int[]{3, 0, 0, 0, 0};
	public static final int MAX_NUMBER_OF_TROOPS = 14;
	
	//GAME CONFIG:
	public static final float CAMERA_SPEED = 180f;//Pixeles por segundo
	public static final int BG_BLACK_ALPHA = 140;
	public static final int START_GOLD = 10;
	
	//ROLL CONFIG
	public static final int ROLL_SYSTEM = 10;

}
