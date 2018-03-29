package net.epictimes.nanodegreepopularmovies.data.local;

import android.content.ContentResolver;

import net.epictimes.nanodegreepopularmovies.MoviesApp;
import net.epictimes.nanodegreepopularmovies.data.MoviesDataSource;
import net.epictimes.nanodegreepopularmovies.di.qualifier.LocalDataSource;
import net.epictimes.nanodegreepopularmovies.di.qualifier.RemoteDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by Mustafa Berkay Mutlu on 29.03.2018.
 */
@Module
public abstract class LocalDataSourceModule {

    @LocalDataSource
    @Singleton
    @Binds
    abstract MoviesDataSource provideMoviesLocalDataSource(MoviesLocalDataSource moviesLocalDataSource);

    @Singleton
    @Provides
    static ContentResolver provideContentResolver(MoviesApp moviesApp) {
        return moviesApp.getContentResolver();
    }

}
