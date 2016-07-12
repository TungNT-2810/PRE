package com.zyuternity.erp.network.network_interface;

import com.zyuternity.erp.network.APIUrls;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ZYuTernity on 7/11/2016.
 */
public interface LoginService {
    @FormUrlEncoded
    @POST(APIUrls.LOGIN)
    Call<Void> loginUser(@Field("login") String login, @Field("pass") String pass, @Field("dbId") String dbId);
}
