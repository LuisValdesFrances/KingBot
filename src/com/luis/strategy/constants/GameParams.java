package com.luis.strategy.constants;

public class GameParams {
	
	//Id terreno
	public static final int PLAIN = 0;
	public static final int FOREST = 1;
	public static final int MONTAIN = 2;
	public static final int SMALL_CITY = 3;
	public static final int MEDIUM_CITY = 4;
	public static final int BIG_CITY = 5;
	public static final int CASTLE = 6;
	
	public static final int[] INFANTRY_COMBAT = 	new int[]{3, 2, 1, 1, 1, 1, 1};
	public static final int[] KNIGHTS_COMBAT = 		new int[]{5, 2, 1, 1, 1, 1, 2};
	public static final int[] HARASSERS_COMBAT = 	new int[]{1, 2, 2, 1, 1, 1, 2};
	public static final int[] SIEGE_COMBAT = 		new int[]{0, 0, 0, 5, 5, 5, 5};
	
	public static final int[] TERRAIN_DEFENSE =		new int[]{3, 5, 8, 10, 15, 25, 8};
	//Ganancias reportadas por el territorio
	public static final int[] TERRAIN_TAX =			new int[]{50, 30, 10, 20, 40, 80, 10};
	
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
