package com.ceduliocezar.repos.model.repository;

import com.ceduliocezar.repos.model.entities.GitHubRepo;

import java.util.List;

public interface GitHubRepoRepository {

    List<GitHubRepo> list(String user) throws Exception;

}
