package com.smart.melo.retrofit.http;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by melo on 2017/8/9.
 * header拦截器
 */
public class NetInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        Request requst = builder.addHeader("Content-type", "application/json")
                .addHeader("Content-Length","300")
                .build();
        return chain.proceed(requst);
    }
}
