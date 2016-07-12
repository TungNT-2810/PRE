package com.zyuternity.erp.database.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ZYuTernity on 7/12/2016.
 */
public class InstructorClassModel extends RealmObject {
    private String role;
    @PrimaryKey
    private String classCode;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }
}
