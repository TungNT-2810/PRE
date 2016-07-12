package com.zyuternity.erp.database.connection;

import com.zyuternity.erp.database.model.ClassModel;
import com.zyuternity.erp.database.model.InstructorClassModel;
import com.zyuternity.erp.database.model.InstructorModel;
import com.zyuternity.erp.database.model.RoleModel;
import com.zyuternity.erp.network.json_model.JSONClassListModel;
import com.zyuternity.erp.network.json_model.JSONClassModel;
import com.zyuternity.erp.network.json_model.JSONInstructorClassesModel;
import com.zyuternity.erp.network.json_model.JSONInstructorListModel;
import com.zyuternity.erp.network.json_model.JSONInstructorModel;
import com.zyuternity.erp.network.json_model.JSONRoleListModel;
import com.zyuternity.erp.network.json_model.JSONRoleModel;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by ZYuTernity on 7/12/2016.
 */
public class DBContext {
    private Realm realm;

    private DBContext(){
    }

    private static DBContext inst;

    public static DBContext getInst(){
        if (inst == null){
            inst = new DBContext();
        }
        return inst;
    }

    public ClassModel getClassModelByCode(String code){
        realm = Realm.getDefaultInstance();
        return realm.where(ClassModel.class).equalTo("code", code).findFirst();
    }

    public InstructorModel getInstructorModelByCode(String code){
        realm = Realm.getDefaultInstance();
        return realm.where(InstructorModel.class).equalTo("code", code).findFirst();
    }

    public RoleModel getRoleModelByCode(String code){
        realm = Realm.getDefaultInstance();
        return realm.where(RoleModel.class).equalTo("code", code).findFirst();
    }

    public void saveClasses (JSONClassListModel jsonClassListModel){
        for (JSONClassModel jsonClassModel : jsonClassListModel.getItem()){
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            if (getClassModelByCode(jsonClassModel.getCode()) == null){
                ClassModel classModel = new ClassModel();
                classModel.setCode(jsonClassModel.getCode());
                classModel.setTitle(jsonClassModel.getTitle());
                realm.copyToRealmOrUpdate(classModel);
            }
            realm.commitTransaction();
        }
    }

    public void saveInstructor (JSONInstructorListModel jsonInstructorListModel){
        for (JSONInstructorModel jsonInstructorModel : jsonInstructorListModel.getItems()){
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            if (getInstructorModelByCode(jsonInstructorModel.getCode()) == null){
                InstructorModel instructorModel = new InstructorModel();
                instructorModel.setEmail(jsonInstructorModel.getEmail());
                instructorModel.setPhone(jsonInstructorModel.getPhone());
                instructorModel.setImage(jsonInstructorModel.getImage());
                instructorModel.setName(jsonInstructorModel.getName());
                instructorModel.setTeam(jsonInstructorModel.getTeam());
                instructorModel.setCode(jsonInstructorModel.getCode());
                RealmList<InstructorClassModel> instructorClassModels = new RealmList<>();
                for (JSONInstructorClassesModel jsonInstructorClassesModel : jsonInstructorModel.getClasses()){
                    InstructorClassModel instructorClassModel = new InstructorClassModel();
                    instructorClassModel.setClassCode(jsonInstructorClassesModel.getClass_code());
                    instructorClassModel.setRole(jsonInstructorClassesModel.getRole());
                }
                realm.copyToRealmOrUpdate(instructorModel);
            }
            realm.commitTransaction();
        }
    }

    public void saveRole (JSONRoleListModel jsonRoleListModel){
        for (JSONRoleModel jsonRoleModel : jsonRoleListModel.getItems()){
            realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            if (getRoleModelByCode(jsonRoleModel.getCode()) == null){
                RoleModel roleModel = new RoleModel();
                roleModel.setCode(jsonRoleModel.getCode());
                roleModel.setTitle(jsonRoleModel.getTitle());
                realm.copyToRealmOrUpdate(roleModel);
            }
            realm.commitTransaction();
        }
    }

    public String getRoleModel(String code){
        realm = Realm.getDefaultInstance();
        return realm.where(RoleModel.class).equalTo("code", code).findFirst().toString();
    }
}
