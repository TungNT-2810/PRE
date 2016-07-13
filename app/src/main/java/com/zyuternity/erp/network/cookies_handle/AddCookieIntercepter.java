package com.zyuternity.erp.network.cookies_handle;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ZYuTernity on 7/12/2016.
 */
public class AddCookieIntercepter implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        HashSet<String> cookies = NetworkPreference.getInstance().getSessionId();
        if(cookies != null) {
            for(String cookie : cookies) {
                builder.addHeader("Cookie", cookie);
//                Log.v("OkHttp", "Adding Header: " + cookie);
            }
        }
        return chain.proceed(builder.build());
    }


}
