package com.luis.strategy.datapackage.scene;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Luis
 */
public class NotificationListData implements Serializable{
	
    private static final long serialVersionUID = 1L;
    
    private List<NotificationData> notificationDataList;

    public List<NotificationData> getNotificationDataList() {
        return notificationDataList;
    }

    public void setNotificationDataList(List<NotificationData> notificationDataList) {
        this.notificationDataList = notificationDataList;
    }
    
}
