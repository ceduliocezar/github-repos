package com.ceduliocezar.repos.di;

import android.os.Handler;

import com.ceduliocezar.repos.model.datasource.CloudGitHubRepoDataSource;
import com.ceduliocezar.repos.model.datasource.GitHubAPI;
import com.ceduliocezar.repos.model.datasource.GitHubRepoDataSource;
import com.ceduliocezar.repos.model.datasource.JsonReader;
import com.ceduliocezar.repos.model.interactor.GetGitHubRepoList;
import com.ceduliocezar.repos.model.repository.GitHubRepoRepository;
import com.ceduliocezar.repos.model.repository.GitHubRepoRepositoryImpl;
import com.ceduliocezar.repos.presentation.home.GitHubRepoListContract;
import com.ceduliocezar.repos.presentation.home.GitHubRepoListFragment;
import com.ceduliocezar.repos.presentation.home.GitHubRepoListPresenter;
import com.ceduliocezar.repos.presentation.home.HomeActivity;
import com.ceduliocezar.repos.threading.MainPostExecutionThread;
import com.ceduliocezar.repos.threading.PostExecutionThread;
import com.ceduliocezar.repos.threading.SimpleIdlingResource;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimpleDIContainer {

    private static SimpleDIContainer INSTANCE = null;

    private ThreadPoolExecutor executor = null;
    private SimpleIdlingResource simpleIdlingResource = null;

    private SimpleDIContainer() {
        // access by static method
    }

    public static SimpleDIContainer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SimpleDIContainer();
        }

        return INSTANCE;
    }

    public void inject(GitHubRepoListFragment gitHubRepoListFragment) {
        gitHubRepoListFragment.presenter = providesHomePresenter();
        gitHubRepoListFragment.idlingResource = providesIdlingResource();
    }

    private SimpleIdlingResource providesIdlingResource() {
        if (simpleIdlingResource == null) {
            simpleIdlingResource = new SimpleIdlingResource();
        }
        return simpleIdlingResource;
    }

    private GitHubRepoListContract.Presenter providesHomePresenter() {
        return new GitHubRepoListPresenter(providesGetGitHubRepoList());
    }

    private GetGitHubRepoList providesGetGitHubRepoList() {
        return new GetGitHubRepoList(providesExecutor(), providesPostExecutionThread(), providesGitHubRepoRepository());
    }

    private PostExecutionThread providesPostExecutionThread() {
        return new MainPostExecutionThread(providesMainHandler());
    }

    private Handler providesMainHandler() {
        return new Handler();
    }

    private Executor providesExecutor() {
        int corePoolSize = 5;
        int maxPoolSize = 10;
        long keepAliveTime = 5000;

        if (executor == null) {
            executor = new ThreadPoolExecutor(
                    corePoolSize,
                    maxPoolSize,
                    keepAliveTime,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>());
        }

        return executor;
    }

    private GitHubRepoRepository providesGitHubRepoRepository() {
        return new GitHubRepoRepositoryImpl(providesGitHubRepoDataSource());
    }

    private GitHubRepoDataSource providesGitHubRepoDataSource() {
        return new CloudGitHubRepoDataSource(provideGitHubApi());
    }

    private GitHubAPI provideGitHubApi() {
        return new GitHubAPI(providesJsonReader());
    }

    private JsonReader providesJsonReader() {
        return new JsonReader();
    }


    public void inject(HomeActivity homeActivity) {
        homeActivity.idlingResource = providesIdlingResource();
    }
}
