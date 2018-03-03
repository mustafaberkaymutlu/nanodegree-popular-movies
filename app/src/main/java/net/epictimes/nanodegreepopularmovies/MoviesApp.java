package net.epictimes.nanodegreepopularmovies;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

public class MoviesApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

}
