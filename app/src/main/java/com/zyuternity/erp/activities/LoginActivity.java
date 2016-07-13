package com.zyuternity.erp.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.dd.processbutton.iml.ActionProcessButton;
import com.squareup.okhttp.internal.Platform;
import com.zyuternity.erp.R;
import com.zyuternity.erp.network.ServiceFactory;
import com.zyuternity.erp.network.network_interface.LoginService;
import com.zyuternity.erp.utils.ProgressGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ProgressGenerator.OnCompleteListener
, View.OnFocusChangeListener{

    // Views
    private EditText edtName;
    private EditText edtPassword;
    private TextInputLayout tilName;
    private TextInputLayout tilPassword;
    private ActionProcessButton btnLogin;
    private RelativeLayout loginLayout;
    private Snackbar snackbar;

    private ProgressGenerator progressGenerator;
    private static final String EXTRAS_ENDLESS_MODE = "EXTRAS_ENDLESS_MODE";
    //Database

    private static final String TAG = LoginActivity.class.toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){

        //set View
        btnLogin = (ActionProcessButton ) findViewById(R.id.btn_login);
        edtName = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);
        tilName = (TextInputLayout) findViewById(R.id.input_layout_email);
        tilPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        loginLayout = (RelativeLayout) findViewById(R.id.login_layout);

        //set Listener
        btnLogin.setOnClickListener(this);
        edtName.setOnFocusChangeListener(this);
        edtPassword.setOnFocusChangeListener(this);


        //NetWork

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                progressGenerator = new ProgressGenerator(this);
                btnLogin.setMode(ActionProcessButton.Mode.ENDLESS);
                progressGenerator.start(btnLogin);
                btnLogin.setClickable(false);
                edtName.setFocusable(false);
                edtPassword.setFocusable(false);
                edtName.setFocusableInTouchMode(false);
                edtPassword.setFocusableInTouchMode(false);
                login();
                break;
            default:
                break;
        }
    }

    private void login() {
        LoginService loginService = ServiceFactory.getInstance().createService(LoginService.class);
        Call<Void> call = loginService.loginUser(edtName.getText().toString(), edtPassword.getText().toString(), "1");
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 200){
                    gotoMainActivity();
                } else {

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    private void gotoMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(intent);
        this.finish();
    }

    @Override
    public void onComplete() {
        btnLogin.setClickable(true);
        edtName.setFocusable(true);
        edtPassword.setFocusable(true);
        edtName.setFocusableInTouchMode(true);
        edtPassword.setFocusableInTouchMode(true);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.edt_email:
            case R.id.edt_password:
                btnLogin.setText("LOGIN");
                btnLogin.setBackgroundColor(getResources().getColor(R.color.colorButton));
                break;
        }
    }
}
