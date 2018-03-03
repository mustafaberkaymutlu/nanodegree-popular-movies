package net.epictimes.nanodegreepopularmovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
