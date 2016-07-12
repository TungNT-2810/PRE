package com.zyuternity.erp.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.zyuternity.erp.R;
import com.zyuternity.erp.network.ServiceFactory;
import com.zyuternity.erp.network.json_model.JSONClassListModel;
import com.zyuternity.erp.network.json_model.JSONInstructorListModel;
import com.zyuternity.erp.network.json_model.JSONRoleListModel;
import com.zyuternity.erp.network.json_model.JSONRoleModel;
import com.zyuternity.erp.network.network_interface.GetClassesService;
import com.zyuternity.erp.network.network_interface.GetInstructorsService;
import com.zyuternity.erp.network.network_interface.GetRoleService;
import com.zyuternity.erp.network.network_interface.LoginService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    // Views
    private EditText edtName;
    private EditText edtPassword;
    private TextInputLayout tilName;
    private TextInputLayout tilPassword;
    private Button btnLogin;
    private RelativeLayout loginLayout;
    private Snackbar snackbar;

    private static final String TAG = LoginActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){

        //set View
        btnLogin = (Button) findViewById(R.id.btn_login);
        edtName = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        tilName = (TextInputLayout) findViewById(R.id.input_layout_email);
        tilPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        loginLayout = (RelativeLayout) findViewById(R.id.login_layout);

        snackbar = Snackbar.make(loginLayout, "Logging in ...", Snackbar.LENGTH_INDEFINITE);
        Snackbar.SnackbarLayout snack_view = (Snackbar.SnackbarLayout) snackbar.getView();
        snack_view.addView(new ProgressBar(this));


        //set Listener
        btnLogin.setOnClickListener(this);

        //NetWork

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                login();
                break;
            default:
                break;
        }
    }

    private void login() {
        snackbar.setText("Logging In ...").setDuration(Snackbar.LENGTH_INDEFINITE).show();
        LoginService loginService = ServiceFactory.getInstance().createService(LoginService.class);
        Call<Void> call = loginService.loginUser(edtName.getText().toString(), edtPassword.getText().toString(), "1");
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    LoginActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            snackbar.setText("Loading Data ...").setDuration(Snackbar.LENGTH_INDEFINITE).show();
                            getClasses();
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        snackbar.setText("Login Failed!").setDuration(Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void getClasses(){
        GetClassesService getClassesService = ServiceFactory.getInstance().createService(GetClassesService.class);
        Call<JSONClassListModel> call = getClassesService.classesModelCall();
        call.enqueue(new Callback<JSONClassListModel>() {
            @Override
            public void onResponse(Call<JSONClassListModel> call, Response<JSONClassListModel> response) {

                if (response.code() == 200){
                    JSONClassListModel JSONClassListModel = (JSONClassListModel) response.body();
//                    for (JSONClassModel classModel : JSONClassListModel.getItem()){
//                        Log.d(TAG, String.format("id = %s, code = %s, title = %s", classModel.getId(), classModel.getCode(), classModel.getTitle()));
//                    }
                    getInstructor();
                }
            }

            @Override
            public void onFailure(Call<JSONClassListModel> call, Throwable t) {
                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        snackbar.setText("Login Failed!").setDuration(Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void getInstructor(){
        GetInstructorsService getInstructorsService = ServiceFactory.getInstance().createService(GetInstructorsService.class);
        Call<JSONInstructorListModel> call = getInstructorsService.instructorsModelCall();
        call.enqueue(new Callback<JSONInstructorListModel>() {
            @Override
            public void onResponse(Call<JSONInstructorListModel> call, Response<JSONInstructorListModel> response) {
                if (response.code() == 200) {
                    JSONInstructorListModel JSONInstructorListModel = (JSONInstructorListModel) response.body();
//                    for (JSONInstructorModel instructorModel : JSONInstructorListModel.getItems()){
//                    Log.d(TAG, String.format("name = %s, team  = %s, code = %s", instructorModel.getName(), instructorModel.getTeam(), instructorModel.getCode()));
//                    Log.d(TAG, String.format("email = " + instructorModel.getContact().getEmail()));
//                    }
                    getRole();

                }
            }
            @Override
            public void onFailure(Call<JSONInstructorListModel> call, Throwable t) {
                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        snackbar.setText("Login Failed!").setDuration(Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void  getRole() {
        GetRoleService getRoleService = ServiceFactory.getInstance().createService(GetRoleService.class);
        Call<JSONRoleListModel> call = getRoleService.rolesModelCall();
        call.enqueue(new Callback<JSONRoleListModel>() {
            @Override
            public void onResponse(Call<JSONRoleListModel> call, Response<JSONRoleListModel> response) {
                JSONRoleListModel JSONRoleListModel = response.body();
                for (JSONRoleModel jsonRoleModel : JSONRoleListModel.getItems()) {
                    Log.d(TAG, String.format("code = %s, title = %s", jsonRoleModel.getCode(), jsonRoleModel.getTitle()));
                }
            }
            @Override
            public void onFailure(Call<JSONRoleListModel> call, Throwable t) {
                LoginActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        snackbar.setText("Login Failed!").setDuration(Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
