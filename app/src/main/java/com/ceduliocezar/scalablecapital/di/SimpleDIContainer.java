package com.ceduliocezar.scalablecapital.di;

import android.os.Handler;

import com.ceduliocezar.scalablecapital.model.datasource.CloudGitHubRepoDataSource;
import com.ceduliocezar.scalablecapital.model.datasource.GitHubAPI;
import com.ceduliocezar.scalablecapital.model.datasource.GitHubRepoDataSource;
import com.ceduliocezar.scalablecapital.model.datasource.JsonReader;
import com.ceduliocezar.scalablecapital.model.interactor.GetGitHubRepoList;
import com.ceduliocezar.scalablecapital.model.repository.GitHubRepoRepository;
import com.ceduliocezar.scalablecapital.model.repository.GitHubRepoRepositoryImpl;
import com.ceduliocezar.scalablecapital.presentation.home.GitHubRepoListFragment;
import com.ceduliocezar.scalablecapital.presentation.home.GitHubRepoListPresenter;
import com.ceduliocezar.scalablecapital.presentation.home.GitHubRepoListContract;
import com.ceduliocezar.scalablecapital.threading.MainPostExecutionThread;
import com.ceduliocezar.scalablecapital.threading.PostExecutionThread;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SimpleDIContainer {

    private static SimpleDIContainer INSTANCE = null;

    private ThreadPoolExecutor executor = null;

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


}