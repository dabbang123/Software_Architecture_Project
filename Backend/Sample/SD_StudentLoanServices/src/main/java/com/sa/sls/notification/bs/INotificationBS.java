package com.sa.sls.notification.bs;
/*
 * @Author: Ranatosh Sarkar
 */
import java.util.List;
import java.util.Map;

import com.sa.sls.notification.model.Notification;

public interface INotificationBS {

	public void update(Map<String, Object> values);
	
	public List<Notification> checkNotifications();

}
