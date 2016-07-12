package com.zyuternity.erp.database.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ZYuTernity on 7/12/2016.
 */
public class InstructorModel extends RealmObject {
    private String email;
    private String phone;
    private String image;
    private String name;
    private String team;
    @PrimaryKey
    private String code;
    private RealmList<InstructorClassModel> instructorClassList;

    public String getImage() {
        return image;
    }

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

    public RealmList<InstructorClassModel> getInstructorClassList() {
        return instructorClassList;
    }

    public void setInstructorClassList(RealmList<InstructorClassModel> instructorClassList) {
        this.instructorClassList = instructorClassList;
    }
}
