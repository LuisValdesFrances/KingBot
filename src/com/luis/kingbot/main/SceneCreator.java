package com.luis.kingbot.main;

import java.util.Date;
import java.util.Scanner;

import com.luis.kingbot.connection.OnlineInputOutput;
import com.luis.strategy.data.DataKingdom;
import com.luis.strategy.datapackage.scene.PreSceneData;

public class SceneCreator extends Thread {
	
	public SceneCreator(){
	}
	
	public void run(){
		System.out.println("");
		System.out.println("SCENE CREATOR -> Press key 'c' to create a new scene");
		System.out.println("SCENE CREATOR -> Press key 'j' to join a bot to scene");
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
			printBots();
			System.out.print("SCENE CREATOR -> Select a index bot host:");
			int hostIndex = Integer.parseInt(new Scanner (System.in).nextLine());
			
			if(
					(map >= 0 && map <= 4) && 
					(hostIndex >= 0 && hostIndex <= Main.BOT_NAME_LIST.length-1)){
				
				String hostName = Main.BOT_NAME_LIST[hostIndex];
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
			printBots();
			System.out.print("SCENE CREATOR -> Select a index bot to join:");
			int botIndex = Integer.parseInt(new Scanner (System.in).nextLine());
			System.out.print("\nSCENE CREATOR -> SCENE CREATOR? (It is last player) y/n:");
			String create = new Scanner (System.in).nextLine();
			
			if((botIndex >= 0 && botIndex <= Main.BOT_NAME_LIST.length-1)){
				String botName = Main.BOT_NAME_LIST[botIndex];
				
				String result = OnlineInputOutput.getInstance().sendInscription(""+preSceneId, botName, 
						"y".equals(create) ? "create" : null);
				
				System.out.println("\nCREATE" + " SCENE -> Result: " + result);
				
			} else {
				System.out.println("\nSCENE CREATOR -> Scenario no valido");
			}
		} catch(Exception e){
			System.err.println("\nSCENE CREATOR -> Error creando uniendo al bot a la escena: " + e.toString());
		}
	}

	private void printBots(){
		String bots = "\n";
		for(int i = 0; i < Main.BOT_NAME_LIST.length; i++){
			bots += "" + i + " : " + Main.BOT_NAME_LIST[i] + 
					(i < Main.BOT_NAME_LIST.length-1
					? "\n"
					:"");
		}
		System.out.println(bots);
	}

}
