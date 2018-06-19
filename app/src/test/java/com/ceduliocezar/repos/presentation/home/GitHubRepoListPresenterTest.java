package com.ceduliocezar.repos.presentation.home;

import com.ceduliocezar.repos.logging.AppLog;
import com.ceduliocezar.repos.model.entities.GitHubRepo;
import com.ceduliocezar.repos.model.interactor.GetGitHubRepoList;
import com.ceduliocezar.repos.model.interactor.UseCaseCallback;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({AppLog.class})
public class GitHubRepoListPresenterTest {

    @InjectMocks
    private GitHubRepoListPresenter gitHubRepoListPresenter;

    @Mock
    private GetGitHubRepoList getGitHubRepoList;

    @Mock
    private GitHubRepoListContract.View view;

    @Captor
    private ArgumentCaptor<UseCaseCallback<List<GitHubRepo>>> callbackCaptor;

    @Mock
    private GitHubRepo repo;

    @Before
    public void setUp() {
        mockStatic(AppLog.class);
    }

    @Test
    public void test_onViewResume() {

        List<GitHubRepo> repos = new ArrayList<>();
        repos.add(repo);
        repos.add(repo);
        repos.add(repo);
        when(view.getUserName()).thenReturn("mralexgray");

        gitHubRepoListPresenter.onViewResume(view);

        verify(getGitHubRepoList).execute(eq("mralexgray"), callbackCaptor.capture());

        callbackCaptor.getValue().onComplete(repos);

        verify(view).showLoading();
        verify(view).hideLoading();
        verify(view).showRepos(repos);
    }

    @Test
    public void test_onViewDestroyed() {

        gitHubRepoListPresenter.onViewResume(view);
        gitHubRepoListPresenter.onViewDestroyed();

        assertNull(gitHubRepoListPresenter.view);
    }
}