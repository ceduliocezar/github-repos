package com.ceduliocezar.scalablecapital.presentation;


public interface BasePresenter<T> {

    void onViewResume(T view);

    void onViewPause();

    void onViewDestroyed();
}
