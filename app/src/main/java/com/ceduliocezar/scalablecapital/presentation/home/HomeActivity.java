package com.ceduliocezar.scalablecapital.presentation.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;

import com.ceduliocezar.scalablecapital.R;
import com.ceduliocezar.scalablecapital.di.SimpleDIContainer;

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
