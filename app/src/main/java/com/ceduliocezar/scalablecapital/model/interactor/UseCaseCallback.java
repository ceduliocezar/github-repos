package com.ceduliocezar.scalablecapital.model.interactor;

public interface UseCaseCallback<T> {
    void onComplete(T data);

    void onError(Exception exception);
}
