package com.luis.strategy.datapackage.scene;

import java.io.Serializable;

/**
 *
 * @author Luis
 */
public class NotificationData implements Serializable{
	
    private static final long serialVersionUID = 1L;
    
    private int id;
    private int sceneId;
    private String message;
    private boolean isRead;
    private int type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSceneId() {
        return sceneId;
    }

    public void setSceneId(int sceneId) {
        this.sceneId = sceneId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
    
}
