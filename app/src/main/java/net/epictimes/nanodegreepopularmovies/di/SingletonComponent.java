package net.epictimes.nanodegreepopularmovies.di;

import net.epictimes.nanodegreepopularmovies.MoviesApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

@Singleton
@Component(modules = {
        SingletonModule.class,
        AndroidInjectionModule.class,
        ActivityBuilderModule.class})
public interface SingletonComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(MoviesApp application);

        SingletonComponent build();
    }

    void inject(MoviesApp chameleonApplication);

}