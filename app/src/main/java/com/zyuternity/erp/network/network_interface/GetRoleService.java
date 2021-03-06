package com.zyuternity.erp.network.network_interface;


import com.zyuternity.erp.network.APIUrls;
import com.zyuternity.erp.network.json_model.JSONRoleListModel;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ZYuTernity on 7/12/2016.
 */
public interface GetRoleService {
    @GET(APIUrls.GET_ROLE)
    Call<JSONRoleListModel> rolesModelCall();
}
