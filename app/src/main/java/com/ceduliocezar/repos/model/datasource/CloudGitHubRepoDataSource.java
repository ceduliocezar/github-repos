package com.ceduliocezar.repos.model.datasource;

import com.ceduliocezar.repos.model.entities.GitHubRepo;

import java.util.List;

public class CloudGitHubRepoDataSource implements GitHubRepoDataSource {

    private static final String TAG = "CloudRepositoryDataSour";

    private GitHubAPI gitHubAPI;

    public CloudGitHubRepoDataSource(GitHubAPI gitHubAPI) {
        this.gitHubAPI = gitHubAPI;
    }

    @Override
    public List<GitHubRepo> list(String user) throws Exception {
        return gitHubAPI.repos(user);
    }
}
