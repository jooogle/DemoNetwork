package ru.mobiledev.demonetwork.presenter;

import android.util.Log;

import java.util.List;

import ru.mobiledev.demonetwork.DemoApplication;
import ru.mobiledev.demonetwork.R;
import ru.mobiledev.demonetwork.data.DataManager;
import ru.mobiledev.demonetwork.data.model.Repository;
import ru.mobiledev.demonetwork.view.MainMvpView;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter implements Presenter<MainMvpView> {
	private static final String TAG = "MainPresenter";

	private MainMvpView mMainMvpView;
	private Subscription mSubscription;
	private List<Repository> mRepositories;

	@Override
	public void attachView(MainMvpView view) {
		mMainMvpView = view;
	}

	@Override
	public void detachView() {
		mMainMvpView = null;
		if (mSubscription != null) mSubscription.unsubscribe();
	}

	public void loadRepositories(String usernameEntered) {
		String username = usernameEntered.trim();
		if (username.isEmpty()) return;

		mMainMvpView.showProgressIndicator();
		if (mSubscription != null) mSubscription.unsubscribe();

		DemoApplication application = DemoApplication.getInstance(mMainMvpView.getContext());
		DataManager manager = application.getDataManager();

		mSubscription = manager.getRepositories(username)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(new Subscriber<List<Repository>>() {
					@Override
					public void onCompleted() {
						Log.i(TAG, "Repositories loaded " + mRepositories);

						if (!mRepositories.isEmpty()) {
							mMainMvpView.showRepositories(mRepositories);
						} else {
							mMainMvpView.showMessage(R.string.text_empty_repos);
						}
					}

					@Override
					public void onError(Throwable e) {
						mMainMvpView.showMessage(R.string.error_loading_repos);
					}

					@Override
					public void onNext(List<Repository> repositories) {
						MainPresenter.this.mRepositories = repositories;
					}
				});
	}
}
