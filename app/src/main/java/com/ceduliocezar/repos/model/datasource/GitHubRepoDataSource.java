package com.ceduliocezar.repos.model.datasource;

import com.ceduliocezar.repos.model.entities.GitHubRepo;

import java.util.List;

public interface GitHubRepoDataSource {

    List<GitHubRepo> list(String user) throws Exception;
}
