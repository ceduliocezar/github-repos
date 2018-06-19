package com.ceduliocezar.repos.model.interactor;

import com.ceduliocezar.repos.logging.AppLog;
import com.ceduliocezar.repos.model.entities.GitHubRepo;
import com.ceduliocezar.repos.model.repository.GitHubRepoRepository;
import com.ceduliocezar.repos.threading.PostExecutionThread;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * Retrieves a list of {@link GitHubRepo} based on the user name passed as parameter.
 */
public class GetGitHubRepoList {

    private static final String TAG = "GetGitHubRepoList";
    private Executor executor;
    private PostExecutionThread postExecutionThread;
    private GitHubRepoRepository gitHubRepoRepository;

    public GetGitHubRepoList(Executor executor,
                             PostExecutionThread postExecutionThread,
                             GitHubRepoRepository gitHubRepoRepository) {
        this.executor = executor;
        this.postExecutionThread = postExecutionThread;
        this.gitHubRepoRepository = gitHubRepoRepository;
    }

    public void execute(final String user, final UseCaseCallback<List<GitHubRepo>> callback) {
        AppLog.d(TAG, "execute: " + user);
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    List<GitHubRepo> list = gitHubRepoRepository.list(user);
                    deliverResult(list, callback);
                } catch (Exception exception) {
                    notifyError(exception, callback);
                }
            }
        });
    }

    private void notifyError(final Exception exception, final UseCaseCallback<List<GitHubRepo>> callback) {
        postExecutionThread.execute(new Runnable() {
            @Override
            public void run() {
                callback.onError(exception);
            }
        });
    }

    private void deliverResult(final List<GitHubRepo> list, final UseCaseCallback<List<GitHubRepo>> callback) {
        postExecutionThread.execute(new Runnable() {
            @Override
            public void run() {
                callback.onComplete(list);
            }
        });

    }
}
