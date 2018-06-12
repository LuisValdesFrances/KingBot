package com.luis.strategy.datapackage.scene;

import java.io.Serializable;
import java.util.List;

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
    private List<PlayerData> playerList;

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

	public List<PlayerData> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(List<PlayerData> playerList) {
		this.playerList = playerList;
	}
}
