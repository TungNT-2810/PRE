package com.zyuternity.erp.activity;

import android.app.DownloadManager;
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
import com.zyuternity.erp.constant.APIUrls;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity implements View.OnClickListener {

    // Views
    private EditText edtName;
    private EditText edtPassword;
    private TextInputLayout tilName;
    private TextInputLayout tilPassword;
    private Button btnLogin;
    private RelativeLayout loginLayout;
    private Snackbar snackbar;

    // Network
    private OkHttpClient httpClient;

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
        httpClient = new OkHttpClient();
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
        snackbar.setText("Logging in ...").setDuration(snackbar.LENGTH_INDEFINITE).show();
        RequestBody requestBody = new FormBody.Builder()
                .addEncoded("login", edtName.getText().toString())
                .addEncoded("pass", edtPassword.getText().toString())
                .addEncoded("dbId", "1")
                .build();

        Request request = new Request.Builder()
                .url(APIUrls.LOGIN)
                .post(requestBody)
                .build();

        httpClient.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if (response.code() == 200){
                    Log.d("ABCD", "OK");
                }
            }
        });

    }
}
