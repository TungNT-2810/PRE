package com.zyuternity.erp.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zyuternity.erp.R;
import com.zyuternity.erp.database.connection.DBContext;
import com.zyuternity.erp.database.model.InstructorClassModel;
import com.zyuternity.erp.database.model.InstructorModel;
import com.zyuternity.erp.managers.ScreenManager;
import com.zyuternity.erp.network.ServiceFactory;
import com.zyuternity.erp.network.json_model.JSONInstructorClassesModel;
import com.zyuternity.erp.network.json_model.JSONInstructorListModel;
import com.zyuternity.erp.network.json_model.JSONInstructorModel;
import com.zyuternity.erp.network.network_interface.GetInstructorsService;

import io.realm.RealmList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class InstructorListFragment extends BaseFragment {

    Button btnChange;
    private DBContext dbContext;
    public static final String TAG = InstructorListFragment.class.toString();


    public InstructorListFragment() {
        // Required empty public constructor
    }

    public static InstructorListFragment create(ScreenManager screenManager){
        InstructorListFragment instructorListFragment = new InstructorListFragment();
        return instructorListFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_instructor_list, container, false);
        initLayout(view);
        initDatabase();
        return view;
    }

    @Override
    public void onResume() {
        getInstructor();
        super.onResume();
    }

    private void initLayout(View view) {
        btnChange = (Button) view.findViewById(R.id.btn_change);
        btnChange.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                for(InstructorModel instructorModel : dbContext.getAllInstructorModels()) {
                    Log.d(TAG, instructorModel.getName() + ", " + instructorModel.getEmail());
                }
                getScreenManager().openFragment(new HistoryFragment(), true);
            }
        });
    }

    private void initDatabase(){
        dbContext = DBContext.getInst();
    }

    private void getInstructor(){
        GetInstructorsService getInstructorsService = ServiceFactory.getInstance().createService(GetInstructorsService.class);
        Call<JSONInstructorListModel> call = getInstructorsService.instructorsModelCall();
        call.enqueue(new Callback<JSONInstructorListModel>() {
            @Override
            public void onResponse(Call<JSONInstructorListModel> call, Response<JSONInstructorListModel> response) {
                if (response.code() == 200) {
                    JSONInstructorListModel jsonInstructorListModel = response.body();
                    for (JSONInstructorModel jsonInstructorModel : jsonInstructorListModel.getItems()){
                        RealmList<InstructorClassModel> instructorClassModelRealmList = new RealmList<InstructorClassModel>();
                        for (JSONInstructorClassesModel jsonInstructorClassesModel : jsonInstructorModel.getClasses()){
                            InstructorClassModel instructorClassModel = InstructorClassModel.create(jsonInstructorClassesModel.getRole()
                                    , jsonInstructorClassesModel.getClass_code());
                            instructorClassModelRealmList.add(instructorClassModel);
                        }
                        Log.d(TAG, "JSON.Email: " + jsonInstructorModel.getContact().getEmail() + " , JSON.Phone: "+jsonInstructorModel.getContact().getPhone());
                        dbContext.addOrUpdateInstructorModel(InstructorModel.create(jsonInstructorModel.getContact().getEmail()
                        ,jsonInstructorModel.getContact().getPhone(), jsonInstructorModel.getImage(), jsonInstructorModel.getName()
                        ,jsonInstructorModel.getTeam(), jsonInstructorModel.getCode(), instructorClassModelRealmList));
                    }
                }
            }
            @Override
            public void onFailure(Call<JSONInstructorListModel> call, Throwable t) {

            }
        });
    }

}
