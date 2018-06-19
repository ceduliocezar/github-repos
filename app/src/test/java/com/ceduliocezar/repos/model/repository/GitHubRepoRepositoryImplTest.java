package com.ceduliocezar.repos.model.repository;

import com.ceduliocezar.repos.model.datasource.GitHubRepoDataSource;
import com.ceduliocezar.repos.model.entities.GitHubRepo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * Test suite for {@link GitHubRepoRepositoryImpl}
 */
@RunWith(MockitoJUnitRunner.class)
public class GitHubRepoRepositoryImplTest {

    @InjectMocks
    private GitHubRepoRepositoryImpl gitHubRepoRepository;

    @Mock
    private GitHubRepoDataSource gitHubRepoDataSource;

    @Mock
    private Exception exception;

    @Test
    public void test_list() throws Exception {

        List<GitHubRepo> repos = new ArrayList<>();
        when(gitHubRepoDataSource.list(eq("mralexgray"))).thenReturn(repos);

        List<GitHubRepo> userRepos = gitHubRepoRepository.list("mralexgray");

        assertEquals(repos, userRepos);
    }

    @Test(expected = Exception.class)
    public void test_listFail() throws Exception {
        doThrow(exception).when(gitHubRepoDataSource.list(eq("mralexgray")));

        gitHubRepoRepository.list("mralexgray");
    }
}