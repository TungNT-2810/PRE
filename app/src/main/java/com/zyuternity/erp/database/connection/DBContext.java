package com.zyuternity.erp.database.connection;

import android.util.Log;

import com.zyuternity.erp.database.model.ClassModel;
import com.zyuternity.erp.database.model.InstructorClassModel;
import com.zyuternity.erp.database.model.InstructorModel;
import com.zyuternity.erp.database.model.RoleModel;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;


/* Ref: https://realm.io/docs/java/latest/ */

/**
 * Created by ZYuTernity on 7/12/2016.
 */
public class DBContext {

    private static final String TAG = DBContext.class.toString();
    private Realm realm;

    private DBContext(){
        realm = Realm.getDefaultInstance();
    }

    private static DBContext inst;

    public static DBContext getInst(){
        if (inst == null){
            inst = new DBContext();
        }
        return inst;
    }

    public ClassModel addOrUpdateClass(ClassModel classModel) {
        ClassModel foundClassModel = realm
                .where(ClassModel.class)
                .equalTo("code", classModel.getCode())
                .findFirst();
        ClassModel returnClassModel;
        realm.beginTransaction();
        if(foundClassModel == null) {
            /* add */
            returnClassModel = realm.copyToRealm(classModel);

        } else {
            /* update */
            foundClassModel.setTitle(classModel.getTitle());
            returnClassModel = foundClassModel;
        }
        realm.commitTransaction();
        return returnClassModel;
    }

    public ClassModel getClassModelByCode(String code){
        realm.getDefaultInstance();
        return realm.where(ClassModel.class).equalTo("code", code).findFirst();

    }

    public RoleModel addOrUpdateRoleModel(RoleModel roleModel) {
        /*  Check whether the role already exist in DB */
        RoleModel foundRoleModel = realm
                .where(RoleModel.class)
                .equalTo("code", roleModel.getCode())
                .findFirst();
        RoleModel returnRoleModel;
        realm.beginTransaction();
        if (foundRoleModel == null){
            /* Roie not afound, add new*/
            returnRoleModel = realm.copyToRealm(roleModel);
        } else {
            /* Role found,update role information  */
            foundRoleModel.setTitle(roleModel.getTitle());
            returnRoleModel = foundRoleModel;
        }
        realm.commitTransaction();
        return returnRoleModel;
    }

    public InstructorModel addOrUpdateInstructorModel (InstructorModel instructorModel) {
        InstructorModel foundInstructorModel = realm.where(InstructorModel.class)
                .equalTo("code", instructorModel.getCode())
                .findFirst();
        InstructorModel returnInstructorModel;
        realm.beginTransaction();
        if (foundInstructorModel == null) {
        } else {
            foundInstructorModel.deleteFromRealm();

            Log.d(TAG, "Instructor updated");
        }
        returnInstructorModel = realm.copyToRealm(instructorModel);
        realm.commitTransaction();
        return returnInstructorModel;
    }

    public List<InstructorModel> getAllInstructorModels() {
        return realm.where(InstructorModel.class).findAll();
    }
}
