package com.zyuternity.erp.database.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ZYuTernity on 7/13/2016.
 */
public class RoleModel extends RealmObject {
    @PrimaryKey
    private String code;
    private String title;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static RoleModel create(String code, String title){
        RoleModel roleModel = new RoleModel();
        roleModel.code = code;
        roleModel.title = title;
        return roleModel;
    }
}
