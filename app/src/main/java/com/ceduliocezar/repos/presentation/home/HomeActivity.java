package com.ceduliocezar.repos.presentation.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;

import com.ceduliocezar.repos.R;
import com.ceduliocezar.repos.di.SimpleDIContainer;

public class HomeActivity extends AppCompatActivity {

    public IdlingResource idlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SimpleDIContainer.getInstance().inject(this);
    }

    @VisibleForTesting
    @NonNull
    @SuppressWarnings("unused")
    public IdlingResource getIdlingResource() {
        return idlingResource;
    }
}
