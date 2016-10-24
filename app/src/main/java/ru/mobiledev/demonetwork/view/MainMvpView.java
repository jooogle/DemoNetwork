package ru.mobiledev.demonetwork.view;

import java.util.List;
import ru.mobiledev.demonetwork.data.model.Repository;

public interface MainMvpView extends MvpView {
	void showRepositories(List<Repository> repositories);

	void showMessage(int stringId);

	void showProgressIndicator();
}
