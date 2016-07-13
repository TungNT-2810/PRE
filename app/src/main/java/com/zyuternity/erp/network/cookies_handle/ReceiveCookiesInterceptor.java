package com.zyuternity.erp.network.cookies_handle;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by ZYuTernity on 7/12/2016.
 */
public class ReceiveCookiesInterceptor implements Interceptor {

    private static final String TAG = ReceiveCookiesInterceptor.class.toString();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

//        Log.d(TAG, "Outbound request intercepted");

        if (!originalResponse.headers("Set-Cookie").isEmpty()) {
//            Log.d(TAG, "Saving cookies");
            HashSet<String> cookies = new HashSet<>();

            for (String header : originalResponse.headers("Set-Cookie")) {
                cookies.add(header);
            }

            NetworkPreference.getInstance().setSessionId(cookies);
//            Log.d(TAG, "Saving cookies - Done");
        }

        return originalResponse;
    }
}
