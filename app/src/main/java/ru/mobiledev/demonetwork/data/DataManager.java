package ru.mobiledev.demonetwork.data;

import android.content.Context;

import java.util.List;

import ru.mobiledev.demonetwork.DemoApplication;
import ru.mobiledev.demonetwork.data.model.Repository;
import ru.mobiledev.demonetwork.data.model.User;
import ru.mobiledev.demonetwork.data.remote.NetworkService;
import rx.Observable;


public class DataManager {
	private static final String TAG = "DataManager";

	private final Context mContext;

	public DataManager(Context context) {
		mContext = context;
	}

	public Observable<List<Repository>> getRepositories(String userName) {
		DemoApplication application = DemoApplication.getInstance(mContext);
		NetworkService networkService = application.getNetworkService();

		return networkService.getRestService()
				.publicRepositories(userName);
	}

	public Observable<User> userFromUrl(String userUrl) {
		DemoApplication application = DemoApplication.getInstance(mContext);
		NetworkService networkService = application.getNetworkService();

		return networkService.getRestService()
				.userFromUrl(userUrl);
	}
}
