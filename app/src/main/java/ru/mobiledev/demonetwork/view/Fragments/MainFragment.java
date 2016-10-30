package ru.mobiledev.demonetwork.view.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import ru.mobiledev.demonetwork.R;
import ru.mobiledev.demonetwork.adapters.RepositoryAdapter;
import ru.mobiledev.demonetwork.data.model.Repository;
import ru.mobiledev.demonetwork.presenter.MainPresenter;
import ru.mobiledev.demonetwork.view.MainMvpView;
import ru.mobiledev.demonetwork.view.RepositoryActivity;

public class MainFragment extends Fragment implements MainMvpView {
	private static final String TAG = "MainFragment";

	private MainPresenter mPresenter;
	private Toolbar mToolbar;
	private RecyclerView mRecyclerView;
	private EditText mEditTextUsername;
	private ProgressBar mProgressBar;
	private TextView mInfoTextView;
	private ImageButton searchButton;

	public static MainFragment newInstance() {
		return new MainFragment();
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPresenter = new MainPresenter();
		mPresenter.attachView(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mPresenter.detachView();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_main, container, false);

		mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
		mInfoTextView = (TextView) view.findViewById(R.id.text_info);

		// Set up toolbar
		mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
		((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);

		// Set up RecyclerView
		mRecyclerView = (RecyclerView) view.findViewById(R.id.repos_recycler_view);
		setupRecyclerView(mRecyclerView);
		// Set up search button
		searchButton = (ImageButton) view.findViewById(R.id.button_search);
		searchButton.setOnClickListener(v -> mPresenter.loadRepositories(mEditTextUsername.getText().toString()));
		// Set up username EditText
		mEditTextUsername = (EditText) view.findViewById(R.id.edit_text_username);
		mEditTextUsername.addTextChangedListener(mHideShowButtonTextWatcher);
		mEditTextUsername.setOnEditorActionListener((v, actionId, event) -> {
			if (actionId == EditorInfo.IME_ACTION_SEARCH) {
				mPresenter.loadRepositories(mEditTextUsername.getText().toString());
				return true;
			}
			return false;
		});

		return view;
	}

	@Override
	public void showRepositories(List<Repository> repositories) {
		RepositoryAdapter adapter = (RepositoryAdapter) mRecyclerView.getAdapter();
		adapter.setRepositories(repositories);
		adapter.notifyDataSetChanged();
		mRecyclerView.requestFocus();
		hideSoftKeyboard();
		mProgressBar.setVisibility(View.INVISIBLE);
		mInfoTextView.setVisibility(View.INVISIBLE);
		mRecyclerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showMessage(int stringId) {
		mProgressBar.setVisibility(View.INVISIBLE);
		mInfoTextView.setVisibility(View.VISIBLE);
		mRecyclerView.setVisibility(View.INVISIBLE);
		mInfoTextView.setText(getString(stringId));
	}

	@Override
	public void showProgressIndicator() {
		mProgressBar.setVisibility(View.VISIBLE);
		mInfoTextView.setVisibility(View.INVISIBLE);
		mRecyclerView.setVisibility(View.INVISIBLE);
	}

	@Override
	public Context getContext() {
		return getActivity().getApplicationContext();
	}

	private void setupRecyclerView(RecyclerView recyclerView) {
		RepositoryAdapter adapter = new RepositoryAdapter();
		adapter.setCallback(repository -> {
			startActivity(RepositoryActivity.newIntent(getContext(), repository));
		});
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
	}

	private void hideSoftKeyboard() {
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(mEditTextUsername.getWindowToken(), 0);
	}

	private TextWatcher mHideShowButtonTextWatcher = new TextWatcher() {
		@Override
		public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

		}

		@Override
		public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
			searchButton.setVisibility(charSequence.length() > 0 ? View.VISIBLE : View.GONE);
		}

		@Override
		public void afterTextChanged(Editable editable) {

		}
	};
}
