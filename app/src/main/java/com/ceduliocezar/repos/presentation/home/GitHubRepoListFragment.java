package com.ceduliocezar.repos.presentation.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ceduliocezar.repos.R;
import com.ceduliocezar.repos.di.SimpleDIContainer;
import com.ceduliocezar.repos.logging.AppLog;
import com.ceduliocezar.repos.model.entities.GitHubRepo;
import com.ceduliocezar.repos.threading.SimpleIdlingResource;

import java.util.ArrayList;
import java.util.List;


public class GitHubRepoListFragment extends Fragment implements GitHubRepoListContract.View {

    private static final String TAG = "GitHubRepoListFragment";
    public GitHubRepoListContract.Presenter presenter;
    public GitHubRepoRecyclerViewAdapter adapter;
    public SimpleIdlingResource idlingResource;

    private View loadingContainer;


    public GitHubRepoListFragment() {
        // mandatory
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_githubrepo_list, container, false);

        SimpleDIContainer.getInstance().inject(this);

        loadingContainer = view.findViewById(R.id.loading_container);

        adapter = new GitHubRepoRecyclerViewAdapter(new ArrayList<GitHubRepo>());
        RecyclerView recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        AppLog.d(TAG, "onResume: ");
        presenter.onViewResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        AppLog.d(TAG, "onPause: ");
        presenter.onViewPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        AppLog.d(TAG, "onDestroy: ");
        presenter.onViewDestroyed();
    }

    @Override
    public void showLoading() {
        AppLog.d(TAG, "showLoading: ");
        loadingContainer.setVisibility(View.VISIBLE);
        idlingResource.setIdleState(false);
    }

    @Override
    public void hideLoading() {
        AppLog.d(TAG, "hideLoading: ");
        loadingContainer.setVisibility(View.GONE);
        idlingResource.setIdleState(true);

    }

    @Override
    public void showRepos(List<GitHubRepo> repositories) {
        AppLog.d(TAG, "showRepos: " + repositories);
        adapter.addRepos(repositories);
    }

    @Override
    public String getUserName() {
        return "mralexgray";// static by now
    }
}
