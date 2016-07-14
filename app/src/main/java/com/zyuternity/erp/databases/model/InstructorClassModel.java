package com.zyuternity.erp.databases.model;

import io.realm.RealmObject;

/**
 * Created by ZYuTernity on 7/12/2016.
 */
public class InstructorClassModel extends RealmObject {
    private String role;
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

    public static InstructorClassModel create(String role, String classCode){
        InstructorClassModel instructorClassModel = new InstructorClassModel();
        instructorClassModel.setRole(role);
        instructorClassModel.setClassCode(classCode);
        return instructorClassModel;
    }


}
