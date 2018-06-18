package com.ceduliocezar.scalablecapital.model.repository;

import com.ceduliocezar.scalablecapital.model.entities.GitHubRepo;

import java.util.List;

public interface GitHubRepoRepository {

    List<GitHubRepo> list(String user) throws Exception;

}
