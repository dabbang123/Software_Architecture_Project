package com.sa.sls.notification.bs;
/*
 * @Author: Ranatosh Sarkar
 */
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.sls.notification.bl.INotificationBL;
import com.sa.sls.notification.model.Notification;
import com.sa.sls.observer.dp.Observer;

@Service
public class NotificationBS implements INotificationBS, Observer {
	
	@Autowired
	private INotificationBL notificationBL;

	@Override
	public void update(Map<String, Object> values) {
		notificationBL.update(values);
	}
	
	@Override
	public List<Notification> checkNotifications()
	{
		return notificationBL.checkNotifications();
	}

}
