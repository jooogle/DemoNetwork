package ru.mobiledev.demonetwork.view.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.mobiledev.demonetwork.R;
import ru.mobiledev.demonetwork.data.model.Repository;
import ru.mobiledev.demonetwork.data.model.User;
import ru.mobiledev.demonetwork.presenter.RepositoryPresenter;
import ru.mobiledev.demonetwork.view.RepositoryMvpView;

public class RepositoryFragment extends Fragment implements RepositoryMvpView {
	private static final String TAG = "RepositoryFragment";

	public static final String EXTRA_REPOSITORY = "EXTRA_REPOSITORY";

	private Toolbar mToolbar;
	private TextView mDescriptionText;
	private TextView mHomepageText;
	private TextView mLanguageText;
	private TextView mForkText;
	private TextView mOwnerNameText;
	private TextView mOwnerEmailText;
	private TextView mOwnerLocationText;
	private ImageView mOwnerImage;
	private View mOwnerLayout;

	private RepositoryPresenter mPresenter;
	private Repository mRepository;

	private AppCompatActivity mActivity;

	public static RepositoryFragment newInstance(Repository repository) {
		Bundle args = new Bundle();
		args.putParcelable(EXTRA_REPOSITORY, repository);

		RepositoryFragment fragment = new RepositoryFragment();
		fragment.setArguments(args);

		return fragment;
	}

	@Override
	public void onAttach(Context context) {
		super.onAttach(context);

		mActivity = (AppCompatActivity) context;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle args = getArguments();
		mRepository = args.getParcelable(EXTRA_REPOSITORY);

		mPresenter = new RepositoryPresenter();
		mPresenter.attachView(this);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_repository, container, false);

		mToolbar = (Toolbar) v.findViewById(R.id.toolbar);
		mDescriptionText = (TextView) v.findViewById(R.id.text_repo_description);
		mHomepageText = (TextView) v.findViewById(R.id.text_homepage);
		mLanguageText = (TextView) v.findViewById(R.id.text_language);
		mForkText = (TextView) v.findViewById(R.id.text_fork);
		mOwnerNameText = (TextView) v.findViewById(R.id.text_owner_name);
		mOwnerEmailText = (TextView) v.findViewById(R.id.text_owner_email);
		mOwnerLocationText = (TextView) v.findViewById(R.id.text_owner_location);
		mOwnerImage = (ImageView) v.findViewById(R.id.image_owner);
		mOwnerLayout = v.findViewById(R.id.layout_owner);

		mActivity.setSupportActionBar(mToolbar);
		ActionBar actionBar = mActivity.getSupportActionBar();
		if (actionBar != null) {
			actionBar.setDisplayHomeAsUpEnabled(true);
		}

		bindRepositoryData(mRepository);
		mPresenter.loadOwner(mRepository.getOwner().getUrl());

		return v;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mPresenter.detachView();
	}

	@Override
	public void showOwner(User owner) {
		mOwnerNameText.setText(owner.getName());
		mOwnerEmailText.setText(owner.getEmail());
		mOwnerEmailText.setVisibility(owner.hasEmail() ? View.VISIBLE : View.GONE);
		mOwnerLocationText.setText(owner.getLocation());
		mOwnerLocationText.setVisibility(owner.hasLocation() ? View.VISIBLE : View.GONE);
		mOwnerLayout.setVisibility(View.VISIBLE);
	}

	private void bindRepositoryData(final Repository repository) {
		mActivity.getSupportActionBar().setTitle(repository.getName());
		mDescriptionText.setText(repository.getDescription());
		mHomepageText.setText(repository.getHomepage());
		mHomepageText.setVisibility(repository.hasHomepage() ? View.VISIBLE : View.GONE);
		mLanguageText.setText(getString(R.string.text_language, repository.getLanguage()));
		mLanguageText.setVisibility(repository.hasLanguage() ? View.VISIBLE : View.GONE);
		mForkText.setVisibility(repository.isFork() ? View.VISIBLE : View.GONE);

		Picasso.with(getActivity())
				.load(repository.getOwner().getAvatarUrl())
				.placeholder(R.drawable.placeholder)
				.into(mOwnerImage);
	}
}
