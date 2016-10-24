package ru.mobiledev.demonetwork;

import android.app.Application;
import android.content.Context;

import ru.mobiledev.demonetwork.data.DataManager;
import ru.mobiledev.demonetwork.data.remote.NetworkService;

public class DemoApplication extends Application {
	private NetworkService networkService;
	private DataManager dataManager;

	@Override
	public void onCreate() {
		super.onCreate();
	}

	public static DemoApplication getInstance(Context context) {
		return (DemoApplication) context.getApplicationContext();
	}

	public NetworkService getNetworkService() {
		if (networkService == null) {
			networkService = new NetworkService();
		}

		return networkService;
	}

	public DataManager getDataManager() {
		if (dataManager == null) {
			dataManager = new DataManager(this);
		}

		return dataManager;
	}
}
