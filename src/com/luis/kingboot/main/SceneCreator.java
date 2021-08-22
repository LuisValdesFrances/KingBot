package com.luis.kingboot.main;

import java.util.Date;
import java.util.Scanner;

import com.luis.kingboot.connection.OnlineInputOutput;
import com.luis.strategy.data.DataKingdom;
import com.luis.strategy.datapackage.scene.PreSceneData;

public class SceneCreator extends Thread {
	
	public SceneCreator(){
	}
	
	public void run(){
		System.out.println("");
		System.out.println("SCENE CREATOR -> Press key 'c' to create a new scene");
		System.out.println("SCENE CREATOR -> Press key 'j' to join a boot to scene");
		System.out.println("");
		while(true){
			
			String key = new Scanner (System.in).nextLine();
			
			if("c".equals(key)){
				this.createScene();
			} else if("j".equals(key)){
				this.joinScene();
			}
			long time = new Date().getTime();
		}
	}
	
	private void createScene(){
		try{
			System.out.print("\nSCENE CREATOR -> Select Scene (0 to 4):");
			int map = Integer.parseInt(new Scanner (System.in).nextLine());
			printBoots();
			System.out.print("SCENE CREATOR -> Select a index boot host:");
			int hostIndex = Integer.parseInt(new Scanner (System.in).nextLine());
			
			if(
					(map >= 0 && map <= 4) && 
					(hostIndex >= 0 && hostIndex <= Main.BOOT_NAME_LIST.length-1)){
				
				String hostName = Main.BOOT_NAME_LIST[hostIndex];
				String sceneName = "Scene by " + hostName;
				String result = OnlineInputOutput.getInstance().sendPreScene(""+map, hostName, sceneName);
				System.out.println("\nSCENE CREATOR -> Result: " + result);
				
			} else {
				System.out.println("\nSCENE CREATOR -> Scenario no valido");
			}
		} catch(Exception e){
			System.err.println("\nSCENE CREATOR -> Error creando escena: " + e.toString());
		}
	}
	
	private void joinScene(){
		try{
			System.out.print("\nSCENE CREATOR -> Select Scene code: ");
			int preSceneId = Integer.parseInt(new Scanner (System.in).nextLine());
			printBoots();
			System.out.print("SCENE CREATOR -> Select a index boot to join:");
			int bootIndex = Integer.parseInt(new Scanner (System.in).nextLine());
			System.out.print("\nSCENE CREATOR -> SCENE CREATOR? (It is last player) y/n:");
			String create = new Scanner (System.in).nextLine();
			
			if((bootIndex >= 0 && bootIndex <= Main.BOOT_NAME_LIST.length-1)){
				String bootName = Main.BOOT_NAME_LIST[bootIndex];
				
				String result = OnlineInputOutput.getInstance().sendInscription(""+preSceneId, bootName, 
						"y".equals(create) ? "create" : null);
				
				System.out.println("\nCREATE" + " SCENE -> Result: " + result);
				
			} else {
				System.out.println("\nSCENE CREATOR -> Scenario no valido");
			}
		} catch(Exception e){
			System.err.println("\nSCENE CREATOR -> Error creando uniendo al boot a la escena: " + e.toString());
		}
	}

	private void printBoots(){
		String boots = "\n";
		for(int i = 0; i < Main.BOOT_NAME_LIST.length; i++){
			boots += "" + i + " : " + Main.BOOT_NAME_LIST[i] + 
					(i < Main.BOOT_NAME_LIST.length-1
					? "\n"
					:"");
		}
		System.out.println(boots);
	}

}
