package com.luis.kingboot.main;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Main {
	
	public static final boolean TEST = false;
	public static final long MIN_SLEEP = 10000;
	public static final long MAX_SLEEP = 1000*60*30;//treinta minutos
	
	public static boolean configCreateScenary = false;
	public static String[] configExcludeScenary;
	
	public static final String[] BOOT_NAME_LIST = {
												"SUPER PEPETONI",
												"SUPER CAMI", 
												"DON COCO", 
												"BURUFULOT", 
												"MARIANUFLO", 
												"XOKOLATE",
												"BERG"
	};
	
	/*
	delete from notification;
	delete from rel_pre_scene_users;
	delete from scene;
	delete from pre_scene;
	
	delete from scene_users_request where pre_scene_id = 387;
	delete from notification where pre_scene_id = 387;
	delete from rel_pre_scene_users where pre_scene_id = 387;
	delete from incidence where pre_scene_id = 387;
	delete from scene where id = 387;
	delete from pre_scene where id = 387;
	 */

	private static Object mutex;
	public static void main(String[] args) {
		mutex = new Object();
		System.out.println("\n#### Start KingBoot ####");
		
		System.out.print("\nNumber of boots? (1 to " + BOOT_NAME_LIST.length + "):  ");
		String bootNum = new Scanner (System.in).nextLine();
		System.out.print("\nCreate scenary? (Y/N): ");
		String create = new Scanner (System.in).nextLine();
		System.out.println("\nSelect excludes scenaries (Boots won't join to they).\n"
				+ "Examples:\n"
				+ "22,25   ->   (Exclude 22, 25)\n"
				+ "-1      ->   (NO exclude)\n"
				+ "'EMPTY' ->   (Exclude ALL)");
		String excludes = new Scanner (System.in).nextLine();
		
		System.out.println();
		
		//Boot verification
		List<Boot> bootList = new ArrayList<Boot>();
		int n = BOOT_NAME_LIST.length;
		try{
			n = Integer.parseInt(bootNum);
			n = n == 0 ? 1 : n;
			System.out.println("Boots activos: " + n);
		}catch(Exception e){
			System.err.println("El numero de boots que ha introducido no es correcto.");
			return;
		}
		for(int i = 0; i < n; i++){
			bootList.add(new Boot(mutex, BOOT_NAME_LIST[i]));
		}
		
		//Create scenaries verification
		configCreateScenary = create.toLowerCase().equals("y");
		System.out.println("Creacion de escenarios: " + configCreateScenary);
		
		//Exclude verification
		try{
			configExcludeScenary = excludes.split(",");
			if(configExcludeScenary != null && configExcludeScenary.length == 1 && configExcludeScenary[0].equals("")){
				configExcludeScenary = null;
			}
		}catch(Exception e){
			configExcludeScenary = null;
		}
		if(configExcludeScenary != null){
			String ex = "";
			for(String s : configExcludeScenary){ex += (s + " ");};
			System.out.println("Exclude scenaries: " + ex);
		}else{
			System.out.println("All scenaries have been excluded.");
		}
		
		//Start boots
		for(Boot boot : bootList){
			boot.start();
		}
	}

	
	//Resources:
    private static Random vRandom;// = new Random(0);
    //Obtiene un randon entre el primer parametro(Numero menor) y el segundo parametro(numero mayor). 
    //Ambos incluidos.
    public static int getRandom(int _i0, int _i1) {
        if(vRandom == null) vRandom = new Random();
        return _i0 + Math.abs(vRandom.nextInt() % (1 + _i1 - _i0));
    }
    
    public static int getRandom(int _iNumber) {
        if(vRandom == null) vRandom = new Random();
        if (_iNumber < 0) {
            return (vRandom.nextInt() % -_iNumber);
        }
        try {
            return Math.abs(vRandom.nextInt()) % _iNumber;
        } catch (Exception e) {
            e.printStackTrace();
           return 0;
        }
    }
}
