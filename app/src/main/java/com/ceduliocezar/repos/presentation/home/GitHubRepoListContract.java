package com.ceduliocezar.repos.presentation.home;

import com.ceduliocezar.repos.model.entities.GitHubRepo;
import com.ceduliocezar.repos.presentation.BasePresenter;

import java.util.List;

public class GitHubRepoListContract {


    interface View {
        void showLoading();

        void hideLoading();

        void showRepos(List<GitHubRepo> repositories);

        String getUserName();
    }

    public interface Presenter extends BasePresenter<GitHubRepoListContract.View> {

    }
}
