package com.reddit.rickandmortyapp.network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class RetryInterceptor implements Interceptor {
    private static final int MAX_RETRIES = 3;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;

        int retryCount = 0;
        while (retryCount < MAX_RETRIES && (response == null || !response.isSuccessful())) {
            try {
                response = chain.proceed(request);
            } catch (IOException e) {
                retryCount++;
                if (retryCount == MAX_RETRIES) {
                    throw e;
                }
            }
        }

        return response;
    }
}

