package com.smart.melo.retrofit.http;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by melo on 2017/3/6.
 * 定义数据接口
 */
public interface Api {
    @GET(UrlConfig.SHEHUI)
    Call<ShehuiBean> getString();

    @POST("{path}?")
    Call<GuoNeiBean> getGuoNeiData(@Path("path") String path, @QueryMap() Map<String, String> map);



}
