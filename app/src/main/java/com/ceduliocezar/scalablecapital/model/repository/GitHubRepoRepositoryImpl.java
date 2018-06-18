package com.ceduliocezar.scalablecapital.model.repository;

import com.ceduliocezar.scalablecapital.model.datasource.GitHubRepoDataSource;
import com.ceduliocezar.scalablecapital.model.entities.GitHubRepo;

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
