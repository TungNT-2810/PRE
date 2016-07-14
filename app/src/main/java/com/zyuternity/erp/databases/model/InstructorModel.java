package com.zyuternity.erp.databases.model;

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
        return image;
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

    public void addToList(InstructorClassModel instructorClassModel){
        this.instructorClassList.add(instructorClassModel);
    }

//    public void updateInstructorClassList(RealmList<InstructorClassModel> instructorClassList) {
//        for(InstructorClassModel instructorClassModel : this.instructorClassList) {
//            instructorClassModel.deleteFromRealm();
//        }
//    }

    public static InstructorModel create(String email, String phone, String image, String name
            , String team, String code, RealmList<InstructorClassModel> instructorClassModelList){
        InstructorModel instructorModel = new InstructorModel();
        instructorModel.setEmail(email);
        instructorModel.setPhone(phone);
        instructorModel.setImage(image);
        instructorModel.setTeam(team);
        instructorModel.setCode(code);
        instructorModel.setName(name);
        instructorModel.setInstructorClassList(instructorClassModelList);
        return instructorModel;
    }

}
