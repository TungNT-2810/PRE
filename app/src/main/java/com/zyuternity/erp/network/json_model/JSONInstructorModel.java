package com.zyuternity.erp.network.json_model;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by ZYuTernity on 7/12/2016.
 */
public class JSONInstructorModel {
    private String email;
    private String phone;
    private String image;
    private String name;
    private String team;
    private String code;
    private List<JSONInstructorClassesModel> classes;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        String decodedImage = this.image;
        String[] parts = decodedImage.split(",");
        return parts[1];
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<JSONInstructorClassesModel> getClasses() {
        return classes;
    }

    public void setClasses(List<JSONInstructorClassesModel> classes) {
        this.classes = classes;
    }
}
