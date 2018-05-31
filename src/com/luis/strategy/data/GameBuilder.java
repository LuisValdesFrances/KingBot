package com.luis.strategy.data;

import java.util.ArrayList;
import java.util.List;

import com.luis.kingboot.main.GameState;
import com.luis.strategy.constants.GameParams;
import com.luis.strategy.datapackage.scene.ArmyData;
import com.luis.strategy.datapackage.scene.SceneData;
import com.luis.strategy.datapackage.scene.KingdomData;
import com.luis.strategy.datapackage.scene.PlayerData;
import com.luis.strategy.datapackage.scene.TroopData;
import com.luis.strategy.map.ActionIA;
import com.luis.strategy.map.Army;
import com.luis.strategy.map.GameScene;
import com.luis.strategy.map.Kingdom;
import com.luis.strategy.map.Player;
import com.luis.strategy.map.Troop;

public class GameBuilder {
	
	private static GameBuilder instance;
	
	public static GameBuilder getInstance(){
		if(instance == null){
			instance = new GameBuilder();
		}
		return instance;
	}
	
	
	
	public GameScene buildStartGameScene(GameState gameState){
		GameScene gameScene = new GameScene(gameState.getMap());

		switch (gameState.getMap()) {
		case 0:
		case 2:
			gameScene.setKingdomList(DataKingdom.getGenterex(gameScene.getMapObject()));
			break;
		case 1:
			gameScene.setKingdomList(DataKingdom.getCrom(gameScene.getMapObject()));
			break;
		}
		
		List<Player> playerList = new ArrayList<Player>();
		
		for(int i = 0; i < gameState.getSceneData().getPlayerDataList().size(); i++){
			PlayerData playerData =  gameState.getSceneData().getPlayerDataList().get(i);
			
			int k1 = DataKingdom.INIT_MAP_DATA[gameState.getMap()][i][0];
			int k2 = DataKingdom.INIT_MAP_DATA[gameState.getMap()][i][1];
			
			Player player = new Player(
					playerData.getName(), 
					null, 
					i, 
					k1);
			
			player.setGold(GameParams.START_GOLD);
			player.getKingdomList().add(gameScene.getKingdom(k1));
			player.getKingdomList().add(gameScene.getKingdom(k2));
			
			Army army = new Army(
					gameScene.getMapObject(), player, gameScene.getKingdom(k1), player.getFlag());
			army.initTroops();
			player.getArmyList().add(army);
			
			playerList.add(player);
		}
		
		/*//Deben de venir ordenados por fecha de inscripción del servidor
		//Ordeno los players para que, en caso de una nueva partida, sea el host el primero
		if(gameState.getSceneData().getState() == 0){
			for(int i = 0; i < playerList.size(); i++){
				if(i > 0 && playerList.get(i).getName().equals(gameState.getName())){
					Player first = playerList.get(i);
					Player aux = playerList.get(0);
					playerList.set(0, first);
					playerList.set(i, aux);
				}
			}
		}
		*/
		gameScene.setPlayerList(playerList);
		return gameScene;
	}
	
	public GameScene buildGameScene(GameState gameState){
		
		GameScene gameScene = new GameScene(gameState.getMap());
		gameScene.setTurnCount(gameState.getSceneData().getTurnCount());
		gameScene.setPlayerIndex(gameState.getSceneData().getPlayerIndex());
		
		List<Player>playerList = new ArrayList<Player>();
		
		switch(gameState.getMap()){
    	case 0:
    	case 2:
    		gameScene.setKingdomList(DataKingdom.getGenterex(gameScene.getMapObject()));
            break;
    	case 1:
    		gameScene.setKingdomList(DataKingdom.getCrom(gameScene.getMapObject()));
    		break;
    	}
		
		for(PlayerData playerData: gameState.getSceneData().getPlayerDataList()){
			
			String name = playerData.getName();
			int flag = playerData.getFlag();
			int capitalkingdom = playerData.getCapitalKingdom();
			int gold = playerData.getGold();
			
			Player player = new Player(name, null, flag, capitalkingdom);
			player.setGold(gold);
			
			for(KingdomData kingdomData : playerData.getKingdomList()){
				Kingdom k = gameScene.getKingdom(kingdomData.getId());
				k.setState(kingdomData.getState());
				player.getKingdomList().add(k);
			}
			
			for(ArmyData armyData : playerData.getArmyList()){
				
				Kingdom k = gameScene.getKingdom(armyData.getKingdom().getId());
				k.setState(armyData.getKingdom().getState());
				
				Army army = new Army(
						gameScene.getMapObject(), player, k, player.getFlag());
				for(TroopData td : armyData.getTroopList()){
					Troop troop = new Troop(td.getType(), td.isSubject());
					army.getTroopList().add(troop);
				}
				
				player.getArmyList().add(army);
			}
			playerList.add(player);
		}
		gameScene.setPlayerList(playerList);
		
		return gameScene;
	}
	
	public SceneData buildSceneData(GameState gameState, int state){
		SceneData sceneData = gameState.getSceneData();
		
		sceneData.setPlayerIndex(gameState.getGameScene().getPlayerIndex());
		sceneData.setNextPlayer(gameState.getGameScene().getPlayerList().get(sceneData.getPlayerIndex()).getName());
		sceneData.setTurnCount(gameState.getGameScene().getTurnCount());
		sceneData.setState(state);
		
		List<PlayerData> playerDataList = new ArrayList<PlayerData>();
		for(Player p : gameState.getGameScene().getPlayerList()){
			PlayerData pd = new PlayerData();
			pd.setId(p.getId());
			pd.setName(p.getName());
			pd.setGold(p.getGold());
			pd.setCapitalKingdom(p.getCapitalkingdom() != null ? p.getCapitalkingdom().getId(): -1);
			pd.setFlag(p.getFlag());
			pd.setIA(false);
			
			//Add army list to player object
			List<ArmyData> adList = new ArrayList<ArmyData>();
			for(Army a : p.getArmyList()){
				ArmyData ad = new ArmyData();
				KingdomData kingdomData = new KingdomData();
				kingdomData.setId(a.getKingdom().getId());
				kingdomData.setState(a.getKingdom().getState());
				ad.setKingdom(kingdomData);
				//Añado las tropas
				List<TroopData> troopList = new ArrayList<TroopData>();
				for(Troop t : a.getTroopList()){
					TroopData troopData = new TroopData();
					troopData.setType(t.getType());
					troopData.setSubject(t.isSubject());
					troopList.add(troopData);
				}
				ad.setTroopList(troopList);
				adList.add(ad);
			}
			pd.setArmyList(adList);
			
			//Add kingdom list to player object
			List<KingdomData> kdList = new ArrayList<KingdomData>();
			for(Kingdom k : p.getKingdomList()){
				KingdomData kd = new KingdomData();
				kd.setId(k.getId());
				kd.setState(k.getState());
				kdList.add(kd);
			}
			pd.setKingdomList(kdList);
			
			
			playerDataList.add(pd);
		}
		
		//Añado las lista de jugadores
		sceneData.setPlayerDataList(playerDataList);
		
		return sceneData;
	}

}
