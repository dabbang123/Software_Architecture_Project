package com.sa.sls.observer.dp;
/*
 * @Author: Ranatosh Sarkar
 */
import java.util.Map;

public interface Observer {
	//Update or Notify the observer
	public void update (Map<String, Object> values);
}
