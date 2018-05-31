package com.luis.strategy.data;

import java.util.ArrayList;
import java.util.List;

import com.luis.strategy.constants.GameParams;
import com.luis.strategy.map.Kingdom;
import com.luis.strategy.map.MapObject;
import com.luis.strategy.map.Terrain;

public class DataKingdom {
	
	/**Establece los dos reinos con los que se empieza por defecto
	 * Dimension 0: mapa
	 * Dimension 1: jugador
	 * Dimension 2. kingdoms
	*/
	public static final int[][][] INIT_MAP_DATA =
		{
		{{1,2}, {7,8}},
		{{1,2}, {14,15}, {19,21}, {25,26}, {36,37}, {51,53}},
		{{1,2}, {7,8}, {3,4}},
		};
	
	public static List<Kingdom> getGenterex(MapObject map){
		
		List<Kingdom> kingdomList = new ArrayList<Kingdom>();
		
		Kingdom k1 = new Kingdom(map);
		k1.setId(1);
		k1.setName("Genterex");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		List<Terrain> terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,GameParams.BIG_CITY, false));
		k1.setTerrainList(terrainList);
		
		Kingdom k2 = new Kingdom(map);
		k2.setId(2);
		k2.setName("Surett");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,GameParams.FOREST, false));
		terrainList.add(new Terrain(map,GameParams.FOREST, false));
		terrainList.add(new Terrain(map,GameParams.MEDIUM_CITY, false));
		k2.setTerrainList(terrainList);
		
		Kingdom k3 = new Kingdom(map);
		k3.setId(3);
		k3.setName("Lyecee");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,GameParams.FOREST, false));
		terrainList.add(new Terrain(map,GameParams.MEDIUM_CITY, false));
		k3.setTerrainList(terrainList);
		
		Kingdom k4 = new Kingdom(map);
		k4.setId(4);
		k4.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,GameParams.FOREST, false));
		terrainList.add(new Terrain(map,GameParams.MONTAIN, false));
		terrainList.add(new Terrain(map,GameParams.MONTAIN, false));
		k4.setTerrainList(terrainList);
		
		Kingdom k5 = new Kingdom(map);
		k5.setId(5);
		k5.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,GameParams.FOREST, false));
		terrainList.add(new Terrain(map,GameParams.MONTAIN, false));
		k5.setTerrainList(terrainList);
		
		Kingdom k6 = new Kingdom(map);
		k6.setId(6);
		k6.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,GameParams.FOREST, false));
		terrainList.add(new Terrain(map,GameParams.MONTAIN, false));
		k6.setTerrainList(terrainList);
		
		Kingdom k7 = new Kingdom(map);
		k7.setId(7);
		k7.setName("Tiraslye");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,GameParams.FOREST, false));
		terrainList.add(new Terrain(map,GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,GameParams.BIG_CITY, false));
		k7.setTerrainList(terrainList);
		
		Kingdom k8 = new Kingdom(map);
		k8.setId(8);
		k8.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,GameParams.FOREST, false));
		k8.setTerrainList(terrainList);
		
		k1.getBorderList().add(k2);
		k1.getBorderList().add(k4);
		k1.getBorderList().add(k5);
		k1.getBorderList().add(k6);
		
		k2.getBorderList().add(k1);
		k2.getBorderList().add(k3);
		k2.getBorderList().add(k5);
		
		k3.getBorderList().add(k2);
		k3.getBorderList().add(k5);
		k3.getBorderList().add(k7);
		k3.getBorderList().add(k8);
		
		k4.getBorderList().add(k1);
		k4.getBorderList().add(k6);
		k4.getBorderList().add(k7);
		
		k5.getBorderList().add(k1);
		k5.getBorderList().add(k2);
		k5.getBorderList().add(k3);
		k5.getBorderList().add(k6);
		k5.getBorderList().add(k7);
		
		k6.getBorderList().add(k1);
		k6.getBorderList().add(k4);
		k6.getBorderList().add(k5);
		k6.getBorderList().add(k7);
		
		k7.getBorderList().add(k3);
		k7.getBorderList().add(k4);
		k7.getBorderList().add(k5);
		k7.getBorderList().add(k6);
		k7.getBorderList().add(k8);
		
		k8.getBorderList().add(k7);
		k8.getBorderList().add(k3);
		
		
		kingdomList.add(k1);
		kingdomList.add(k2);
		kingdomList.add(k3);
		kingdomList.add(k4);
		kingdomList.add(k5);
		kingdomList.add(k6);
		kingdomList.add(k7);
		kingdomList.add(k8);
		
		return kingdomList;
	}
	
	public static List<Kingdom> getCrom(MapObject map){
		
		List<Kingdom> kingdomList = new ArrayList<Kingdom>();
		/*
		Kingdom k1 = new Kingdom(map,
			(530f/mapWidth*100), (410f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k1.setId(1);
		k1.setName("Genterex");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		List<Terrain> terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(430f/mapWidth*100), (510f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(500f/mapWidth*100), (510f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(570f/mapWidth*100), (510f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.FOREST).getWidth(), GfxManager.imgTerrain.get(GameParams.FOREST).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(405f/mapWidth*100), (400f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.BIG_CITY).getWidth(), GfxManager.imgTerrain.get(GameParams.BIG_CITY).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.BIG_CITY, false));
		k1.setTerrainList(terrainList);
		
		Kingdom k2 = new Kingdom(map,
				(360f/(float)mapWidth*100f), (595f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k2.setId(2);
		k2.setName("Surett");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(290f/(float)mapWidth*100f), (680f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(360f/(float)mapWidth*100f), (680f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(260f/(float)mapWidth*100f), (592f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.MEDIUM_CITY).getWidth(), GfxManager.imgTerrain.get(GameParams.MEDIUM_CITY).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MEDIUM_CITY, false));
		k2.setTerrainList(terrainList);
		
		Kingdom k3 = new Kingdom(map,
			(460f/(float)mapWidth*100f), (790f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k3.setId(3);
		k3.setName("Lyecee");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(382f/(float)mapWidth*100f), (940f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(452f/(float)mapWidth*100f), (940f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(372f/(float)mapWidth*100f), (852f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.MEDIUM_CITY).getWidth(), GfxManager.imgTerrain.get(GameParams.MEDIUM_CITY).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MEDIUM_CITY, false));
		k3.setTerrainList(terrainList);
		
		Kingdom k4 = new Kingdom(map,
			(730f/(float)mapWidth*100f), (530f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k4.setId(4);
		k4.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(790f/(float)mapWidth*100f), (575f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(860f/(float)mapWidth*100f), (575f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		terrainList.add(new Terrain(map,
			(930f/(float)mapWidth*100f), (575f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		k4.setTerrainList(terrainList);
		
		Kingdom k5 = new Kingdom(map,
			(500f/(float)mapWidth*100f), (610f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k5.setId(5);
		k5.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(460f/(float)mapWidth*100f), (710f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(530f/(float)mapWidth*100f), (710f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		k5.setTerrainList(terrainList);
		
		Kingdom k6 = new Kingdom(map,
			(660f/(float)mapWidth*100f), (570f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k6.setId(6);
		k6.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(650f/(float)mapWidth*100f), (660f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(720f/(float)mapWidth*100f), (660f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		k6.setTerrainList(terrainList);
		
		Kingdom k7 = new Kingdom(map,
			(580f/(float)mapWidth*100f), (840f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k7.setId(7);
		k7.setName("Tiraslye");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(640f/(float)mapWidth*100f), (840f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(710f/(float)mapWidth*100f), (840f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(780f/(float)mapWidth*100f), (840f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(650f/(float)mapWidth*100f), (750f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.BIG_CITY).getWidth(), GfxManager.imgTerrain.get(GameParams.BIG_CITY).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MEDIUM_CITY, false));
		k7.setTerrainList(terrainList);
		
		Kingdom k8 = new Kingdom(map,
			(830f/(float)mapWidth*100f), (940f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k8.setId(8);
		k8.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(620f/(float)mapWidth*100f), (980f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(690f/(float)mapWidth*100f), (980f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(760f/(float)mapWidth*100f), (980f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k8.setTerrainList(terrainList);
		
		
		//Daergonais
		Kingdom k9 = new Kingdom(map,
			(340f/(float)mapWidth*100f), (80f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k9.setId(9);
		k9.setName("Krull");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(245f/(float)mapWidth*100f), (95f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(150f/(float)mapWidth*100f), (95f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.SMALL_CITY, false));
		k9.setTerrainList(terrainList);
		
		Kingdom k10 = new Kingdom(map,
			(485f/(float)mapWidth*100f), (275f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k10.setId(10);
		k10.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(350f/(float)mapWidth*100f), (210f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.FOREST).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k10.setTerrainList(terrainList);
		
		Kingdom k11 = new Kingdom(map,
			(305f/(float)mapWidth*100f), (250f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k11.setId(11);
		k11.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(220f/(float)mapWidth*100f), (240f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.FOREST).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k11.setTerrainList(terrainList);
		
		Kingdom k12 = new Kingdom(map,
			(695f/(float)mapWidth*100f), (400f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k12.setId(12);
		k12.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(635f/(float)mapWidth*100f), (375f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.FOREST).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k12.setTerrainList(terrainList);
		
		Kingdom k13 = new Kingdom(map,
			(780f/(float)mapWidth*100f), (410f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k13.setId(13);
		k13.setName("Daergonais");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(730f/(float)mapWidth*100f), (330f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.FOREST).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(800f/(float)mapWidth*100f), (330f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.FOREST).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		terrainList.add(new Terrain(map,
			(870f/(float)mapWidth*100f), (430f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.FOREST).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.SMALL_CITY, false));
		k13.setTerrainList(terrainList);
		
		
		//Quaca
		Kingdom k14 = new Kingdom(map,
		(400/(float)mapWidth*100f), (1070f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k14.setId(14);
		k14.setName("Quaca");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(270f/(float)mapWidth*100f), (1025f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(340f/(float)mapWidth*100f), (1025/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		terrainList.add(new Terrain(map,
			(300f/(float)mapWidth*100f), (1135f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MEDIUM_CITY, false));
		k14.setTerrainList(terrainList);
		
		Kingdom k15 = new Kingdom(map,
			(125/(float)mapWidth*100f), (1240f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k15.setId(15);
		k15.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(200f/(float)mapWidth*100f), (1210f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k15.setTerrainList(terrainList);
		
		Kingdom k16 = new Kingdom(map,
			(470/(float)mapWidth*100f), (1130f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k16.setId(16);
		k16.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(485f/(float)mapWidth*100f), (1060f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(555f/(float)mapWidth*100f), (1060f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		k16.setTerrainList(terrainList);
		
		Kingdom k17 = new Kingdom(map,
			(480/(float)mapWidth*100f), (1235f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k17.setId(17);
		k17.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(380f/(float)mapWidth*100f), (1240f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		k17.setTerrainList(terrainList);
		
		Kingdom k18 = new Kingdom(map,
			(575/(float)mapWidth*100f), (1340f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k18.setId(18);
		k18.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(490f/(float)mapWidth*100f), (1380f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k18.setTerrainList(terrainList);
		
		//Lye
		Kingdom k19 = new Kingdom(map,
		(1030/(float)mapWidth*100f), (250f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k19.setId(19);
		k19.setName("Lye");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(925f/(float)mapWidth*100f), (360f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(995f/(float)mapWidth*100f), (360/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(910f/(float)mapWidth*100f), (250f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MEDIUM_CITY, false));
		k19.setTerrainList(terrainList);
		
		Kingdom k20 = new Kingdom(map,
			(900/(float)mapWidth*100f), (70f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k20.setId(20);
		k20.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(800f/(float)mapWidth*100f), (90f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		k20.setTerrainList(terrainList);
		
		Kingdom k21 = new Kingdom(map,
			(1440/(float)mapWidth*100f), (160f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k21.setId(21);
		k21.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1150f/(float)mapWidth*100f), (200f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1220f/(float)mapWidth*100f), (200f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1290f/(float)mapWidth*100f), (200f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(1250f/(float)mapWidth*100f), (140f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.SMALL_CITY, false));
		k21.setTerrainList(terrainList);
		
		Kingdom k22 = new Kingdom(map,
			(1580/(float)mapWidth*100f), (180f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k22.setId(22);
		k22.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1690f/(float)mapWidth*100f), (255f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1760f/(float)mapWidth*100f), (225f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.SMALL_CITY).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.SMALL_CITY, false));
		k22.setTerrainList(terrainList);
		
		Kingdom k23 = new Kingdom(map,
			(1270/(float)mapWidth*100f), (315f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k23.setId(23);
		k23.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1110f/(float)mapWidth*100f), (340f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(1180f/(float)mapWidth*100f), (340f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		k23.setTerrainList(terrainList);
		
		Kingdom k24 = new Kingdom(map,
			(1520/(float)mapWidth*100f), (260f/(float)mapHeight*100f), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k24.setId(24);
		k24.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1360f/(float)mapWidth*100f), (300f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1430f/(float)mapWidth*100f), (300f/(float)mapHeight*100f), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k24.setTerrainList(terrainList);
		
		//Crom
		Kingdom k25 = new Kingdom(map,
			(1575f/mapWidth*100), (660f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k25.setId(25);
		k25.setName("Crom");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1410f/mapWidth*100), (760f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1480f/mapWidth*100), (760f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1550f/mapWidth*100), (760f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.FOREST).getWidth(), GfxManager.imgTerrain.get(GameParams.FOREST).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		terrainList.add(new Terrain(map,
			(1450f/mapWidth*100), (620f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.BIG_CITY).getWidth(), GfxManager.imgTerrain.get(GameParams.BIG_CITY).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.BIG_CITY, false));
		k25.setTerrainList(terrainList);
		
		Kingdom k26 = new Kingdom(map,
			(1300f/mapWidth*100), (450f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k26.setId(26);
		k26.setName("Cromgast");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1020f/mapWidth*100), (470f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		terrainList.add(new Terrain(map,
			(1080f/mapWidth*100), (470f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		terrainList.add(new Terrain(map,
			(1190f/mapWidth*100), (440f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.FOREST).getWidth(), GfxManager.imgTerrain.get(GameParams.FOREST).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MEDIUM_CITY, false));
		k26.setTerrainList(terrainList);
		
		Kingdom k27 = new Kingdom(map,
			(1410f/mapWidth*100), (460f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k27.setId(27);
		k27.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1580f/map.getWidth()*90), (380f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(1650f/map.getWidth()*90), (380f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		k27.setTerrainList(terrainList);
		
		Kingdom k28 = new Kingdom(map,
			(1580f/mapWidth*100), (500f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k28.setId(28);
		k28.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1510f/mapWidth*100), (480f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		k28.setTerrainList(terrainList);
		
		Kingdom k29 = new Kingdom(map,
			(1055f/mapWidth*100), (580f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k29.setId(29);
		k29.setName("");
			//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(910f/mapWidth*100), (675f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(980f/mapWidth*100), (675f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		k29.setTerrainList(terrainList);
		
		Kingdom k30 = new Kingdom(map,
			(1195f/mapWidth*100), (600f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k30.setId(30);
		k30.setName("");
			//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1280f/mapWidth*100), (700f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		k30.setTerrainList(terrainList);
		
		Kingdom k31 = new Kingdom(map,
			(1680f/mapWidth*100), (650f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k31.setId(31);
		k31.setName("");
			//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1745f/mapWidth*100), (675f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1815f/mapWidth*100), (675f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k31.setTerrainList(terrainList);
		
		Kingdom k32 = new Kingdom(map,
			(1070f/mapWidth*100), (780f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k32.setId(32);
		k32.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1050f/mapWidth*100), (920f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1120f/mapWidth*100), (920f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(985f/mapWidth*100), (800f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MEDIUM_CITY, false));
		k32.setTerrainList(terrainList);
		
		Kingdom k33 = new Kingdom(map,
			(1210f/mapWidth*100), (795f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k33.setId(33);
		k33.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1210f/mapWidth*100), (890f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1280f/mapWidth*100), (890f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1350f/mapWidth*100), (820f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.SMALL_CITY, false));
		k33.setTerrainList(terrainList);
		
		Kingdom k34 = new Kingdom(map,
			(1550f/mapWidth*100), (1030f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k34.setId(34);
		k34.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1620f/mapWidth*100), (1040f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1690f/mapWidth*100), (1040f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1750f/mapWidth*100), (1040f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(1530f/mapWidth*100), (930f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MEDIUM_CITY, false));
		k34.setTerrainList(terrainList);
		
		Kingdom k35 = new Kingdom(map,
			(1650f/mapWidth*100), (795f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k35.setId(35);
		k35.setName("");
			//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1700f/mapWidth*100), (890f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k35.setTerrainList(terrainList);
		
		//Gapeangue
		Kingdom k36 = new Kingdom(map,
			(1285f/mapWidth*100), (1030f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k36.setId(36);
		k36.setName("Gapeangue");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1260f/mapWidth*100), (1140f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1330f/mapWidth*100), (1140f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1400f/mapWidth*100), (1140f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.FOREST).getWidth(), GfxManager.imgTerrain.get(GameParams.FOREST).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1145f/mapWidth*100), (1085f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.BIG_CITY).getWidth(), GfxManager.imgTerrain.get(GameParams.BIG_CITY).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MEDIUM_CITY, false));
		k36.setTerrainList(terrainList);
		
		Kingdom k37 = new Kingdom(map,
			(1015f/mapWidth*100), (1065f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k37.setId(37);
		k37.setName("");
			//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(930f/mapWidth*100), (1010f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k37.setTerrainList(terrainList);
		
		Kingdom k38 = new Kingdom(map,
			(1045f/mapWidth*100), (1200f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k38.setId(38);
		k38.setName("");
			//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(900f/mapWidth*100), (1165f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(970f/mapWidth*100), (1165f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(830f/mapWidth*100), (1150f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.SMALL_CITY, false));
		k38.setTerrainList(terrainList);
		
		Kingdom k39 = new Kingdom(map,
			(815f/mapWidth*100), (1275f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k39.setId(39);
		k39.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(750f/mapWidth*100), (1250f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		k39.setTerrainList(terrainList);
		
		Kingdom k40 = new Kingdom(map,
			(1355f/mapWidth*100), (1310f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k40.setId(40);
		k40.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1180f/mapWidth*100), (1260f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1140f/mapWidth*100), (1320f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.SMALL_CITY, false));
		k40.setTerrainList(terrainList);
		
		Kingdom k41 = new Kingdom(map,
			(1440f/mapWidth*100), (1360f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k41.setId(41);
		k41.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1370f/mapWidth*100), (1270f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1440f/mapWidth*100), (1270f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k41.setTerrainList(terrainList);
		
		Kingdom k42 = new Kingdom(map,
			(735f/mapWidth*100), (1085f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k42.setId(42);
		k42.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(600f/mapWidth*100), (1160f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(670f/mapWidth*100), (1160f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k42.setTerrainList(terrainList);
		
		//Myr
		Kingdom k43 = new Kingdom(map,
			(1600f/mapWidth*100), (1235f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k43.setId(43);
		k43.setName("Myr");
			//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1750f/mapWidth*100), (1240f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1820f/mapWidth*100), (1240f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(1660f/mapWidth*100), (1160f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.SMALL_CITY, false));
		k43.setTerrainList(terrainList);
		
		
		Kingdom k44 = new Kingdom(map,
			(1830f/mapWidth*100), (1370f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k44.setId(44);
		k44.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1720f/mapWidth*100), (1375f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		k44.setTerrainList(terrainList);
		
		Kingdom k45 = new Kingdom(map,
			(2055f/mapWidth*100), (1315f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k45.setId(45);
		k45.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1980f/mapWidth*100), (1315f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		k45.setTerrainList(terrainList);
		
		//Montag
		Kingdom k46 = new Kingdom(map,
			(1920f/mapWidth*100), (450f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k46.setId(46);
		k46.setName("Montag");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1955f/mapWidth*100), (555f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(2025f/mapWidth*100), (555f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		terrainList.add(new Terrain(map,
			(2005f/mapWidth*100), (440f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.SMALL_CITY, false));
		k46.setTerrainList(terrainList);
		
		Kingdom k47 = new Kingdom(map,
			(1730f/mapWidth*100), (500f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k47.setId(47);
		k47.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1610f/mapWidth*100), (370f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1680f/mapWidth*100), (370f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1750f/mapWidth*100), (370f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k47.setTerrainList(terrainList);
		
		Kingdom k48 = new Kingdom(map,
			(2120f/mapWidth*100), (370f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k48.setId(48);
		k48.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(2210f/mapWidth*100), (430f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(2280f/mapWidth*100), (430f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k48.setTerrainList(terrainList);
		
		Kingdom k49 = new Kingdom(map,
			(2130f/mapWidth*100), (550f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k49.setId(49);
		k49.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(2155f/mapWidth*100), (640f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		k49.setTerrainList(terrainList);
		
		Kingdom k50 = new Kingdom(map,
			(2280f/mapWidth*100), (525f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k50.setId(50);
		k50.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(2260f/mapWidth*100), (635f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(2210f/mapWidth*100), (570f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.SMALL_CITY, false));
		k50.setTerrainList(terrainList);
		
		
		//Levantia
		Kingdom k51 = new Kingdom(map,
			(2015f/mapWidth*100), (950f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k51.setId(51);
		k51.setName("Levantia");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(2020f/mapWidth*100), (1050f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(2090f/mapWidth*100), (1050f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(2160f/mapWidth*100), (1050f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(2140f/mapWidth*100), (950f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MEDIUM_CITY, false));
		k51.setTerrainList(terrainList);
		
		Kingdom k52 = new Kingdom(map,
			(2170f/mapWidth*100), (750f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k52.setId(52);
		k52.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(2240f/mapWidth*100), (760f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(2310f/mapWidth*100), (760f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k52.setTerrainList(terrainList);
		
		Kingdom k53 = new Kingdom(map,
			(1935f/mapWidth*100), (660f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k53.setId(53);
		k53.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1920f/mapWidth*100), (765f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.PLAIN, false));
		terrainList.add(new Terrain(map,
			(1975f/mapWidth*100), (830f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.SMALL_CITY, false));
		k53.setTerrainList(terrainList);
		
		Kingdom k54 = new Kingdom(map,
			(1830f/mapWidth*100), (760f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k54.setId(54);
		k54.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1755f/mapWidth*100), (785f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k54.setTerrainList(terrainList);
		
		Kingdom k55 = new Kingdom(map,
			(1875f/mapWidth*100), (960f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k55.setId(55);
		k55.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1810f/mapWidth*100), (880f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		terrainList.add(new Terrain(map,
			(1880f/mapWidth*100), (880f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		k55.setTerrainList(terrainList);
		
		Kingdom k56 = new Kingdom(map,
			(1885f/mapWidth*100), (1110f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k56.setId(56);
		k56.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(1970f/mapWidth*100), (1140f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.MONTAIN, false));
		terrainList.add(new Terrain(map,
			(1965f/mapWidth*100), (1190f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.SMALL_CITY, false));
		k56.setTerrainList(terrainList);
		
		Kingdom k57 = new Kingdom(map,
			(2145f/mapWidth*100), (1180f/mapHeight*100), map.getX(), map.getY(), map.getWidth(), map.getHeight());
		k57.setId(57);
		k57.setName("");
		//La posicion del terreno se expresa en porcentajes respecto al ancho y alto del mapa
		terrainList = new ArrayList<Terrain>();
		terrainList.add(new Terrain(map,
			(2070f/mapWidth*100), (1175f/mapHeight*100), GfxManager.imgTerrain.get(GameParams.PLAIN).getWidth(), GfxManager.imgTerrain.get(GameParams.PLAIN).getHeight(), map.getX(), map.getY(), map.getWidth(), map.getHeight(), GameParams.FOREST, false));
		k57.setTerrainList(terrainList);
		
		
		
		//Genterex
		k1.getBorderList().add(k2);
		k1.getBorderList().add(k4);
		k1.getBorderList().add(k5);
		k1.getBorderList().add(k6);
		k1.getBorderList().add(k6);
		k1.getBorderList().add(k6);
		k1.getBorderList().add(k10);
		k1.getBorderList().add(k11);
		k1.getBorderList().add(k12);
		
		k2.getBorderList().add(k1);
		k2.getBorderList().add(k3);
		k2.getBorderList().add(k5);
		
		k3.getBorderList().add(k2);
		k3.getBorderList().add(k5);
		k3.getBorderList().add(k7);
		k3.getBorderList().add(k8);
		k3.getBorderList().add(k14);
		k3.getBorderList().add(k16);
		
		k4.getBorderList().add(k1);
		k4.getBorderList().add(k6);
		k4.getBorderList().add(k7);
		k4.getBorderList().add(k12);
		k4.getBorderList().add(k13);
		k4.getBorderList().add(k26);
		k4.getBorderList().add(k29);
		
		k5.getBorderList().add(k1);
		k5.getBorderList().add(k2);
		k5.getBorderList().add(k3);
		k5.getBorderList().add(k6);
		k5.getBorderList().add(k7);
		
		k6.getBorderList().add(k1);
		k6.getBorderList().add(k4);
		k6.getBorderList().add(k5);
		k6.getBorderList().add(k7);
		
		k7.getBorderList().add(k3);
		k7.getBorderList().add(k4);
		k7.getBorderList().add(k5);
		k7.getBorderList().add(k6);
		k7.getBorderList().add(k8);
		k7.getBorderList().add(k32);
		
		k8.getBorderList().add(k7);
		k8.getBorderList().add(k3);
		k8.getBorderList().add(k16);
		k8.getBorderList().add(k42);
		
		//Daergonais
		k9.getBorderList().add(k10);
		k9.getBorderList().add(k11);
		
		k10.getBorderList().add(k9);
		k10.getBorderList().add(k11);
		k10.getBorderList().add(k1);
		k10.getBorderList().add(k12);
		
		k11.getBorderList().add(k9);
		k11.getBorderList().add(k10);
		k11.getBorderList().add(k1);
		
		k12.getBorderList().add(k10);
		k12.getBorderList().add(k1);
		k12.getBorderList().add(k4);
		k12.getBorderList().add(k13);
		
		k13.getBorderList().add(k12);
		k13.getBorderList().add(k20);
		k13.getBorderList().add(k19);
		k13.getBorderList().add(k4);
		k13.getBorderList().add(k26);
		
		//Quaca
		k14.getBorderList().add(k3);
		k14.getBorderList().add(k16);
		k14.getBorderList().add(k17);
		k14.getBorderList().add(k15);
		
		k15.getBorderList().add(k14);
		k15.getBorderList().add(k17);
		
		k16.getBorderList().add(k14);
		k16.getBorderList().add(k3);
		k16.getBorderList().add(k8);
		k16.getBorderList().add(k17);
		k16.getBorderList().add(k42);
		
		k17.getBorderList().add(k15);
		k17.getBorderList().add(k14);
		k17.getBorderList().add(k16);
		k17.getBorderList().add(k18);
		k17.getBorderList().add(k42);
		
		k18.getBorderList().add(k17);
		
		//Lye
		k19.getBorderList().add(k13);
		k19.getBorderList().add(k20);
		k19.getBorderList().add(k21);
		k19.getBorderList().add(k23);
		k19.getBorderList().add(k26);
		
		k20.getBorderList().add(k13);
		k20.getBorderList().add(k19);
		
		k21.getBorderList().add(k19);
		k21.getBorderList().add(k22);
		k21.getBorderList().add(k23);
		k21.getBorderList().add(k24);
		
		k22.getBorderList().add(k21);
		k22.getBorderList().add(k24);
		k22.getBorderList().add(k47);
		
		k23.getBorderList().add(k19);
		k23.getBorderList().add(k21);
		k23.getBorderList().add(k24);
		k23.getBorderList().add(k27);
		k23.getBorderList().add(k26);
		
		k24.getBorderList().add(k21);
		k24.getBorderList().add(k22);
		k24.getBorderList().add(k23);
		k24.getBorderList().add(k27);
		k24.getBorderList().add(k47);
		
		//Crom
		k25.getBorderList().add(k26);
		k25.getBorderList().add(k27);
		k25.getBorderList().add(k28);
		k25.getBorderList().add(k31);
		k25.getBorderList().add(k35);
		k25.getBorderList().add(k34);
		k25.getBorderList().add(k33);
		k25.getBorderList().add(k30);
		
		k26.getBorderList().add(k30);
		k26.getBorderList().add(k29);
		k26.getBorderList().add(k4);
		k26.getBorderList().add(k13);
		k26.getBorderList().add(k19);
		k26.getBorderList().add(k23);
		k26.getBorderList().add(k27);
		k26.getBorderList().add(k25);
		
		k27.getBorderList().add(k28);
		k27.getBorderList().add(k25);
		k27.getBorderList().add(k26);
		k27.getBorderList().add(k23);
		k27.getBorderList().add(k24);
		k27.getBorderList().add(k47);
		
		k28.getBorderList().add(k31);
		k28.getBorderList().add(k25);
		k28.getBorderList().add(k27);
		k28.getBorderList().add(k47);
		
		k29.getBorderList().add(k4);
		k29.getBorderList().add(k26);
		k29.getBorderList().add(k30);
		k29.getBorderList().add(k32);
		k29.getBorderList().add(k7);
		
		k30.getBorderList().add(k33);
		k30.getBorderList().add(k32);
		k30.getBorderList().add(k29);
		k30.getBorderList().add(k26);
		k30.getBorderList().add(k25);
		
		k31.getBorderList().add(k35);
		k31.getBorderList().add(k25);
		k31.getBorderList().add(k28);
		k31.getBorderList().add(k47);
		k31.getBorderList().add(k53);
		k31.getBorderList().add(k54);
		
		k32.getBorderList().add(k7);
		k32.getBorderList().add(k29);
		k32.getBorderList().add(k30);
		k32.getBorderList().add(k33);
		
		k33.getBorderList().add(k32);
		k33.getBorderList().add(k30);
		k33.getBorderList().add(k25);
		k33.getBorderList().add(k34);
		k33.getBorderList().add(k36);
		
		k34.getBorderList().add(k33);
		k34.getBorderList().add(k25);
		k34.getBorderList().add(k35);
		k34.getBorderList().add(k36);
		k34.getBorderList().add(k43);
		k34.getBorderList().add(k55);
		
		k35.getBorderList().add(k34);
		k35.getBorderList().add(k25);
		k35.getBorderList().add(k31);
		k35.getBorderList().add(k54);
		k35.getBorderList().add(k55);
		
		//Gapeangue
		k36.getBorderList().add(k37);
		k36.getBorderList().add(k32);
		k36.getBorderList().add(k33);
		k36.getBorderList().add(k34);
		k36.getBorderList().add(k41);
		k36.getBorderList().add(k40);
		k36.getBorderList().add(k38);
		k36.getBorderList().add(k43);
		
		k37.getBorderList().add(k32);
		k37.getBorderList().add(k36);
		k37.getBorderList().add(k38);
		
		k38.getBorderList().add(k39);
		k38.getBorderList().add(k42);
		k38.getBorderList().add(k37);
		k38.getBorderList().add(k36);
		k38.getBorderList().add(k40);
		k38.getBorderList().add(k37);
		k38.getBorderList().add(k36);
		
		k39.getBorderList().add(k18);
		k39.getBorderList().add(k38);
		
		k40.getBorderList().add(k38);
		k40.getBorderList().add(k36);
		k40.getBorderList().add(k41);
		
		k41.getBorderList().add(k40);
		k41.getBorderList().add(k36);
		k41.getBorderList().add(k43);
		k41.getBorderList().add(k44);
		
		k42.getBorderList().add(k17);
		k42.getBorderList().add(k16);
		k42.getBorderList().add(k8);
		k42.getBorderList().add(k38);
		
		//Myr
		k43.getBorderList().add(k41);
		k43.getBorderList().add(k36);
		k43.getBorderList().add(k34);
		k43.getBorderList().add(k44);
		k43.getBorderList().add(k45);
		k43.getBorderList().add(k55);
		k43.getBorderList().add(k56);
		
		k44.getBorderList().add(k41);
		k44.getBorderList().add(k43);
		k44.getBorderList().add(k45);
		
		k45.getBorderList().add(k44);
		k45.getBorderList().add(k43);
		k45.getBorderList().add(k56);
		k45.getBorderList().add(k57);
		
		//Montag
		k46.getBorderList().add(k47);
		k46.getBorderList().add(k48);
		k46.getBorderList().add(k49);
		k46.getBorderList().add(k53);
		
		k47.getBorderList().add(k22);
		k47.getBorderList().add(k46);
		k47.getBorderList().add(k31);
		k47.getBorderList().add(k28);
		k47.getBorderList().add(k27);
		k47.getBorderList().add(k24);
		k47.getBorderList().add(k53);
		
		k48.getBorderList().add(k46);
		k48.getBorderList().add(k49);
		k48.getBorderList().add(k50);
		
		k49.getBorderList().add(k46);
		k49.getBorderList().add(k48);
		k49.getBorderList().add(k50);
		k49.getBorderList().add(k52);
		
		k50.getBorderList().add(k49);
		k50.getBorderList().add(k48);
		k50.getBorderList().add(k52);
		
		//Levantia
		k51.getBorderList().add(k57);
		k51.getBorderList().add(k56);
		k51.getBorderList().add(k55);
		k51.getBorderList().add(k53);
		
		k52.getBorderList().add(k50);
		k52.getBorderList().add(k49);
		k52.getBorderList().add(k53);
		
		k53.getBorderList().add(k52);
		k53.getBorderList().add(k49);
		k53.getBorderList().add(k46);
		k53.getBorderList().add(k47);
		k53.getBorderList().add(k31);
		k53.getBorderList().add(k54);
		k53.getBorderList().add(k55);
		k53.getBorderList().add(k51);
		
		k54.getBorderList().add(k53);
		k54.getBorderList().add(k31);
		k54.getBorderList().add(k35);
		k54.getBorderList().add(k55);
		
		k55.getBorderList().add(k53);
		k55.getBorderList().add(k54);
		k55.getBorderList().add(k35);
		k55.getBorderList().add(k34);
		k55.getBorderList().add(k43);
		k55.getBorderList().add(k56);
		k55.getBorderList().add(k51);
		
		k56.getBorderList().add(k51);
		k56.getBorderList().add(k55);
		k56.getBorderList().add(k43);
		k56.getBorderList().add(k45);
		k56.getBorderList().add(k57);
		
		k57.getBorderList().add(k51);
		k57.getBorderList().add(k56);
		k57.getBorderList().add(k45);
		
		
		kingdomList.add(k1);
		kingdomList.add(k2);
		kingdomList.add(k3);
		kingdomList.add(k4);
		kingdomList.add(k5);
		kingdomList.add(k6);
		kingdomList.add(k7);
		kingdomList.add(k8);
		
		kingdomList.add(k9);
		kingdomList.add(k10);
		kingdomList.add(k11);
		kingdomList.add(k12);
		kingdomList.add(k13);
		
		kingdomList.add(k14);
		kingdomList.add(k15);
		kingdomList.add(k16);
		kingdomList.add(k17);
		kingdomList.add(k18);
		
		kingdomList.add(k19);
		kingdomList.add(k20);
		kingdomList.add(k21);
		kingdomList.add(k22);
		kingdomList.add(k23);
		kingdomList.add(k24);
		
		kingdomList.add(k25);
		kingdomList.add(k26);
		kingdomList.add(k27);
		kingdomList.add(k28);
		kingdomList.add(k29);
		kingdomList.add(k30);
		kingdomList.add(k31);
		kingdomList.add(k32);
		kingdomList.add(k33);
		kingdomList.add(k34);
		kingdomList.add(k35);
		
		kingdomList.add(k36);
		kingdomList.add(k37);
		kingdomList.add(k38);
		kingdomList.add(k39);
		kingdomList.add(k40);
		kingdomList.add(k41);
		kingdomList.add(k42);
		
		kingdomList.add(k43);
		kingdomList.add(k44);
		kingdomList.add(k45);
		
		kingdomList.add(k46);
		kingdomList.add(k47);
		kingdomList.add(k48);
		kingdomList.add(k49);
		kingdomList.add(k50);
		
		kingdomList.add(k51);
		kingdomList.add(k52);
		kingdomList.add(k53);
		kingdomList.add(k54);
		kingdomList.add(k55);
		kingdomList.add(k56);
		kingdomList.add(k57);
		*/
		return kingdomList;
	}

}
