package com.ceduliocezar.scalablecapital.model.datasource;

import com.ceduliocezar.scalablecapital.model.entities.GitHubRepo;

import java.util.List;

public interface GitHubRepoDataSource {

    List<GitHubRepo> list(String user) throws Exception;
}
