package com.ceduliocezar.scalablecapital.presentation.home;

import com.ceduliocezar.scalablecapital.model.entities.GitHubRepo;
import com.ceduliocezar.scalablecapital.presentation.BasePresenter;

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
