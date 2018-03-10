package net.epictimes.nanodegreepopularmovies.data.remote;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();

        final HttpUrl url = request.url()
                .newBuilder()
                .addQueryParameter("api_key", Endpoint.API_KEY)
                .build();

        request = request.newBuilder().url(url).build();

        return chain.proceed(request);
    }
}
