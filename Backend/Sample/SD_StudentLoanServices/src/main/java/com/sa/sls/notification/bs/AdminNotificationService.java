package com.sa.sls.notification.bs;
/*
 * @Author: Ranatosh Sarkar
 */
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sa.sls.notification.bl.NotificationBL;
import com.sa.sls.observer.dp.Observer;

@Service
public class AdminNotificationService implements Observer {
	
	@Autowired
	private NotificationBL notificationBL;

	@Override
	public void update(Map<String, Object> values) {
		notificationBL.update(values);
	}

}
