package ru.mobiledev.demonetwork.view;

import android.support.v4.app.Fragment;

import ru.mobiledev.demonetwork.view.Fragments.MainFragment;

public class MainActivity extends SingleFragmentActivity {
	@Override
	protected Fragment createFragment() {
		return MainFragment.newInstance();
	}
}
