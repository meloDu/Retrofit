package com.smart.melo.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.smart.melo.retrofit.http.GuoNeiBean;
import com.smart.melo.retrofit.http.RetrofitHelper;
import com.smart.melo.retrofit.http.ShehuiBean;
import com.smart.melo.retrofit.http.UrlConfig;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //社会  get请求
        RetrofitHelper.getInstance().getService().getString().enqueue(new Callback<ShehuiBean>() {
            @Override
            public void onResponse(Call<ShehuiBean> call, Response<ShehuiBean> response) {
                if (response.isSuccessful()){
                    ShehuiBean shehuiBean = response.body();
                    Log.i("tag", "社会：" +shehuiBean.getResult().getData().get(0).getTitle());
                }
            }

            @Override
            public void onFailure(Call<ShehuiBean> call, Throwable t) {
                Log.i("tag","下载失败");
            }
        });


        //国内 post请求
        HashMap<String, String> map = new HashMap<>();
        map.put("type", "guonei");
        map.put("key", "723060051cc0b5baa348a8024b1bb7e1");
        RetrofitHelper.getInstance().getService().getGuoNeiData(UrlConfig.GUONEI,map).enqueue(new Callback<GuoNeiBean>() {
            @Override
            public void onResponse(Call<GuoNeiBean> call, Response<GuoNeiBean> response) {
                if (response.isSuccessful()) {
                    GuoNeiBean guoNeiBean = response.body();
                    Log.i("tag", "国内：" + guoNeiBean.getResult().getData().get(0).getTitle());
                }
            }

            @Override
            public void onFailure(Call<GuoNeiBean> call, Throwable t) {

            }
        });
    }
}
