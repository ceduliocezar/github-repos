package com.ceduliocezar.repos.threading;


import android.os.Handler;

public class MainPostExecutionThread implements PostExecutionThread {

    private Handler handler;

    public MainPostExecutionThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(Runnable runnable) {
        handler.post(runnable);
    }
}
