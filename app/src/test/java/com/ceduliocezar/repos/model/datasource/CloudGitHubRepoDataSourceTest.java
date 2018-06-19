package com.ceduliocezar.repos.model.datasource;

import com.ceduliocezar.repos.model.entities.GitHubRepo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Test suite for {@link CloudGitHubRepoDataSource}
 */
@RunWith(MockitoJUnitRunner.class)
public class CloudGitHubRepoDataSourceTest {


    @InjectMocks
    private CloudGitHubRepoDataSource cloudRepositoryDataSource;

    @Mock
    private GitHubAPI gitHubAPI;

    @Mock
    private GitHubRepo repository;

    @Mock
    private Exception exception;

    @Test
    public void test_list() throws Exception {
        List<GitHubRepo> repositories = new ArrayList<>();
        repositories.add(repository);
        repositories.add(repository);
        when(gitHubAPI.repos("mralexgray")).thenReturn(repositories);

        List<GitHubRepo> cloudRepos = cloudRepositoryDataSource.list("mralexgray");

        assertEquals(repositories, cloudRepos);
        assertEquals(2, cloudRepos.size());
    }

    @Test(expected = Exception.class)
    public void test_listFail() throws Exception {

        doThrow(exception).when(gitHubAPI).repos("mralexgray");

        cloudRepositoryDataSource.list("mralexgray");
    }
}