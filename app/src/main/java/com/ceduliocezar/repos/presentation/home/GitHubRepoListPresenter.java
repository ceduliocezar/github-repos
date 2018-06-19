package com.ceduliocezar.repos.presentation.home;


import com.ceduliocezar.repos.logging.AppLog;
import com.ceduliocezar.repos.model.entities.GitHubRepo;
import com.ceduliocezar.repos.model.interactor.GetGitHubRepoList;
import com.ceduliocezar.repos.model.interactor.UseCaseCallback;

import java.util.List;


public class GitHubRepoListPresenter implements GitHubRepoListContract.Presenter {

    private static final String TAG = "GitHubRepoListPresenter";

    private GetGitHubRepoList getGitHubRepoList;
    GitHubRepoListContract.View view;

    public GitHubRepoListPresenter(GetGitHubRepoList getGitHubRepoList) {
        this.getGitHubRepoList = getGitHubRepoList;
    }

    @Override
    public void onViewResume(GitHubRepoListContract.View view) {
        AppLog.d(TAG, "onViewResume: " + view);
        attachView(view);
        loadRepositories();
    }

    private void attachView(GitHubRepoListContract.View view) {
        AppLog.d(TAG, "attachView: " + view);
        this.view = view;
    }

    private void loadRepositories() {
        AppLog.d(TAG, "loadRepositories: ");
        view.showLoading();
        getGitHubRepoList.execute(view.getUserName(), new UseCaseCallback<List<GitHubRepo>>() {
            @Override
            public void onComplete(List<GitHubRepo> data) {
                AppLog.d(TAG, "loadRepositories: " + data);
                if (hasViewAttached()) {
                    view.hideLoading();
                    view.showRepos(data);
                }
            }

            @Override
            public void onError(Exception exception) {
                AppLog.e(TAG, "loadRepositories: ", exception);
                if (hasViewAttached()) {
                    view.hideLoading();
                }
            }
        });
    }

    private boolean hasViewAttached() {
        return view != null;
    }

    @Override
    public void onViewPause() {
        AppLog.d(TAG, "onViewPause: ");
    }

    @Override
    public void onViewDestroyed() {
        AppLog.d(TAG, "onViewDestroyed: ");
        detachView();
    }

    private void detachView() {
        this.view = null;
    }
}
