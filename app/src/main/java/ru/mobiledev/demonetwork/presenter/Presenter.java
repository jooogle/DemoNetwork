package ru.mobiledev.demonetwork.presenter;

public interface Presenter<V> {
	void attachView(V view);

	void detachView();
}
