package com.ceduliocezar.repos.model.repository;

import com.ceduliocezar.repos.model.datasource.GitHubRepoDataSource;
import com.ceduliocezar.repos.model.entities.GitHubRepo;

import java.util.List;

public class GitHubRepoRepositoryImpl implements GitHubRepoRepository {

    private GitHubRepoDataSource gitHubRepoDataSource;

    public GitHubRepoRepositoryImpl(GitHubRepoDataSource gitHubRepoDataSource) {
        this.gitHubRepoDataSource = gitHubRepoDataSource;
    }

    @Override
    public List<GitHubRepo> list(String user) throws Exception {
        return gitHubRepoDataSource.list(user);
    }
}
