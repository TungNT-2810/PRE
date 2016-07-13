package com.zyuternity.erp.network;



//import okhttp3.JavaNetCookieJar;

import com.zyuternity.erp.network.cookies_handle.AddCookieIntercepter;
import com.zyuternity.erp.network.cookies_handle.ReceiveCookiesInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ZYuTernity on 7/12/2016.
 */
public class ServiceFactory {
    private Retrofit retrofit;
    private static ServiceFactory ourInstance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return ourInstance;
    }

    private ServiceFactory() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(new ReceiveCookiesInterceptor())
                .addInterceptor(new AddCookieIntercepter()).build();

        retrofit = new Retrofit.Builder()
                .baseUrl(APIUrls.BASE_URL)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <ServiceClass> ServiceClass createService(Class<ServiceClass> serviceClass){
        return retrofit.create(serviceClass);
    }
}
