package ru.mobiledev.demonetwork.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import ru.mobiledev.demonetwork.R;
import ru.mobiledev.demonetwork.data.model.Repository;
import ru.mobiledev.demonetwork.view.Fragments.RepositoryFragment;

import static ru.mobiledev.demonetwork.view.Fragments.RepositoryFragment.EXTRA_REPOSITORY;

public class RepositoryActivity extends AppCompatActivity {
	private static final String TAG = "RepositoryActivity";

	public static Intent newIntent(Context context, Repository repository) {
		Intent intent = new Intent(context, RepositoryActivity.class);
		intent.putExtra(EXTRA_REPOSITORY, repository);
		return intent;
	}

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Repository repository = getIntent().getParcelableExtra(EXTRA_REPOSITORY);

		FragmentManager fm = getSupportFragmentManager();
		Fragment fragment = fm.findFragmentById(R.id.fragment_container);
		if (fragment == null) {
			fragment = RepositoryFragment.newInstance(repository);
			fm.beginTransaction()
					.add(R.id.fragment_container, fragment)
					.commit();
		}
	}
}
