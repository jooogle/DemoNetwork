package ru.mobiledev.demonetwork.presenter;

import android.util.Log;

import ru.mobiledev.demonetwork.DemoApplication;
import ru.mobiledev.demonetwork.data.DataManager;
import ru.mobiledev.demonetwork.view.RepositoryMvpView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RepositoryPresenter implements Presenter<RepositoryMvpView> {

	private static final String TAG = "RepositoryPresenter";

	private RepositoryMvpView repositoryMvpView;
	private Subscription subscription;

	@Override
	public void attachView(RepositoryMvpView view) {
		this.repositoryMvpView = view;
	}

	@Override
	public void detachView() {
		this.repositoryMvpView = null;
		if (subscription != null) subscription.unsubscribe();
	}

	public void loadOwner(String userUrl) {
		DemoApplication application = DemoApplication.getInstance(repositoryMvpView.getContext());
		DataManager manager = application.getDataManager();

		subscription = manager.userFromUrl(userUrl)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(user -> {
					Log.i(TAG, "Full user data loaded " + user);
					repositoryMvpView.showOwner(user);
				});
	}
}
