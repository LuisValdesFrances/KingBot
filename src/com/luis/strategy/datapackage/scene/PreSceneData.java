package com.luis.strategy.datapackage.scene;

import java.io.Serializable;

/**
 *
 * @author lvaldes
 */
public class PreSceneData implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private int id;
    private int map;
    private String name;
    private String host;
    private int playerCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMap() {
        return map;
    }

    public void setMap(int map) {
        this.map = map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
    
    
    
}
