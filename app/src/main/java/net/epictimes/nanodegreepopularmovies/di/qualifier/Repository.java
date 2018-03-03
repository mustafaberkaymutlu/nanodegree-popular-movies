package net.epictimes.nanodegreepopularmovies.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */


@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Repository {
}
