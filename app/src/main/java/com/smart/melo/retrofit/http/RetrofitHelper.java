package com.smart.melo.retrofit.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by melo on 2017/3/6.
 * 定义retrofit类
 */
public class RetrofitHelper {
    private static volatile RetrofitHelper instance = null;

    private RetrofitHelper() {

    }

    public static RetrofitHelper getInstance() {
        if (instance == null) {
            synchronized (RetrofitHelper.class) {
                if (instance == null) {
                    instance = new RetrofitHelper();
                }
            }
        }
        return instance;
    }


    public OkHttpClient createHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                //添加拦截器
                .addInterceptor(new NetInterceptor())
                .build();
        return client;
    }

    public Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(UrlConfig.BASE_URL)
                .client(createHttpClient())
                //gson解析要加
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .build();
    }

    public Api getService() {
        Retrofit retrofit = createRetrofit();
        Api apiService = retrofit.create(Api.class);
        return apiService;
    }

    /**
     * @return
     */
    public Gson createGson() {
        return new GsonBuilder()
                .serializeNulls()
                .enableComplexMapKeySerialization()
//                .setDateFormat("")
                .create();
    }
}
