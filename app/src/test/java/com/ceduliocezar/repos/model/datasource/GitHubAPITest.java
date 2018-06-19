package com.ceduliocezar.repos.model.datasource;

import com.ceduliocezar.repos.TestUtils;
import com.ceduliocezar.repos.model.entities.GitHubRepo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GitHubAPITest {

    @InjectMocks
    private GitHubAPI gitHubAPI;

    @Mock
    private JsonReader jsonReader;

    @Mock
    private Exception exception;

    @Test
    public void test_repos() throws Exception {

        String responseJson = TestUtils.readContentFromResources(getClass(), "test-200-repo-response.json");
        when(jsonReader.read(any(URL.class))).thenReturn(responseJson);

        List<GitHubRepo> repositories = gitHubAPI.repos("mralexgray");

        assertEquals(2, repositories.size());
        GitHubRepo repository0 = repositories.get(0);
        assertEquals(repository0.getId(), "1296269");
        assertEquals(repository0.getName(), "octocat/Hello-World");

        GitHubRepo repository1 = repositories.get(1);
        assertEquals(repository1.getId(), "123456");
        assertEquals(repository1.getName(), "octocat/FakeRepo");
    }

    @Test
    public void test_emptyRepos() throws Exception {

        when(jsonReader.read(any(URL.class))).thenReturn("[]");

        List<GitHubRepo> repositories = gitHubAPI.repos("mralexgray");

        assertEquals(0, repositories.size());
    }

    @Test(expected = Exception.class)
    public void test_reposFail() throws Exception {

        doThrow(exception).when(jsonReader).read(any(URL.class));

        gitHubAPI.repos("mralexgray");
    }
}