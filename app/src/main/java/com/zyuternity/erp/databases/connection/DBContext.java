package com.zyuternity.erp.databases.connection;

import android.util.Log;

import com.zyuternity.erp.databases.model.ClassModel;
import com.zyuternity.erp.databases.model.InstructorModel;
import com.zyuternity.erp.databases.model.RoleModel;

import io.realm.Realm;


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
           Log.d(TAG, "Class Added");

        } else {
            /* update */
            foundClassModel.deleteFromRealm();
            Log.d(TAG, "Class Updated");
        }
        returnClassModel = realm.copyToRealm(classModel);
        realm.commitTransaction();
        return returnClassModel;
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
            Log.d(TAG, "Role added");
        } else {
            /* Role found,update role information  */
            foundRoleModel.deleteFromRealm();
            Log.d(TAG, "Role updated");

        }
        returnRoleModel = realm.copyToRealm(roleModel);
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
            Log.d(TAG, "Instructor added");
        } else {
            foundInstructorModel.deleteFromRealm();

            Log.d(TAG, "Instructor updated");
        }
        returnInstructorModel = realm.copyToRealm(instructorModel);
        realm.commitTransaction();
        return returnInstructorModel;
    }

}
