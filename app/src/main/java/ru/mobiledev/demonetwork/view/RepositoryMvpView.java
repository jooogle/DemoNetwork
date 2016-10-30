package ru.mobiledev.demonetwork.view;

import ru.mobiledev.demonetwork.data.model.User;

public interface RepositoryMvpView extends MvpView {
	void showOwner(final User owner);
}
