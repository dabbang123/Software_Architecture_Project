package com.sa.sls.notification.dao;
/*
 * @Author: Ranatosh Sarkar
 */
import java.util.List;

import com.sa.sls.notification.model.Notification;

public interface INotificationDAO {
	public void update(Notification notification);
	
	public List<Notification> checkNotifications();
}
