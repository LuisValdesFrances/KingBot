package com.luis.kingboot.main;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main {
	
	public static final String[] BOOT_NAME_LIST = {
												"SUPER PEPETONI", 
												"SUPER CAMI", 
												"DON COCO", 
												"BURUFULOT", 
												"MARIANUFLO", 
												"XOKOLATE"
	};
	
	/*
	delete from notification;
	delete from rel_pre_scene_users;
	delete from scene;
	delete from pre_scene;
	 */

	private static Object mutex;
	public static void main(String[] args) {
		mutex = new Object();
		List<Boot> bootList = new ArrayList<Boot>();
		
		for(String name : BOOT_NAME_LIST){
			bootList.add(new Boot(mutex, name));
		}
		
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
