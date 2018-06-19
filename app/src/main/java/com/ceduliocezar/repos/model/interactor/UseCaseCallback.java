package com.ceduliocezar.repos.model.interactor;

public interface UseCaseCallback<T> {
    void onComplete(T data);

    void onError(Exception exception);
}
