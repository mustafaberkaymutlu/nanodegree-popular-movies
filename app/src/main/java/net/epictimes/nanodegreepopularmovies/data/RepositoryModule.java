package net.epictimes.nanodegreepopularmovies.data;

import net.epictimes.nanodegreepopularmovies.di.qualifier.Repository;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

@Module
public abstract class RepositoryModule {

    @Repository
    @Singleton
    @Binds
    abstract MoviesDataSource provideMovieRepository(MovieRepository repository);

}
