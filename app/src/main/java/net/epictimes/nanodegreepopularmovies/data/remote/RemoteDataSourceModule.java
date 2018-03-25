package net.epictimes.nanodegreepopularmovies.data.remote;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;

import net.epictimes.nanodegreepopularmovies.BuildConfig;
import net.epictimes.nanodegreepopularmovies.data.MoviesDataSource;
import net.epictimes.nanodegreepopularmovies.data.VideosDataSource;
import net.epictimes.nanodegreepopularmovies.di.qualifier.RemoteDataSource;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

@Module
public abstract class RemoteDataSourceModule {

    @RemoteDataSource
    @Singleton
    @Binds
    abstract MoviesDataSource provideMoviesRemoteDataSource(MoviesRemoteDataSource moviesRemoteDataSource);

    @RemoteDataSource
    @Singleton
    @Binds
    abstract VideosDataSource provideVideosRemoteDataSource(VideosRemoteDataSource videosRemoteDataSource);

    @Singleton
    @Provides
    static Services provideServices(@NonNull Retrofit retrofit) {
        return retrofit.create(Services.class);
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(@NonNull OkHttpClient okHttpClient, @NonNull Gson gson) {
        return new Retrofit.Builder()
                .baseUrl(Endpoint.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }

    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient(@NonNull AuthInterceptor authInterceptor,
                                            @Nullable HttpLoggingInterceptor httpLoggingInterceptor) {
        final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        okHttpClientBuilder.addInterceptor(authInterceptor);

        if (httpLoggingInterceptor != null) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);
        }

        return okHttpClientBuilder.build();
    }

    @Singleton
    @Provides
    static Gson provideGson() {
        return new Gson();
    }

    @Singleton
    @Provides
    static AuthInterceptor provideAuthInterceptor() {
        return new AuthInterceptor();
    }

    @Singleton
    @Nullable
    @Provides
    static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        if (BuildConfig.DEBUG) {
            return new HttpLoggingInterceptor();
        }

        return null;
    }

}
