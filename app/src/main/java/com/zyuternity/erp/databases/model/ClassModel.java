package com.zyuternity.erp.databases.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ZYuTernity on 7/12/2016.
 */
public class ClassModel extends RealmObject {
    private String title;
    @PrimaryKey
    private String code;

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

    public static ClassModel create(String code, String title) {
        ClassModel classModel = new ClassModel();
        classModel.code = code;
        classModel.title = title;
        return classModel;
    }
}
