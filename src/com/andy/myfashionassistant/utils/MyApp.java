package com.andy.myfashionassistant.utils;

import android.app.Application;

public class MyApp extends Application {

	// TODO Create boolean connect
	private boolean connect;

	public boolean isConnect() {
		return connect;
	}

	public void setConnect(boolean connect) {
		this.connect = connect;
	}
	

}