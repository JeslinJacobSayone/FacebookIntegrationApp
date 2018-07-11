package com.example.sayone.facebookintegrationapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    LoginButton mLogin;

    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        callbackManager= CallbackManager.Factory.create();
        mLogin=findViewById(R.id.login_button);




        mLogin.setReadPermissions("email","public_profile");
        mLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                Profile profile= Profile.getCurrentProfile();
                TextView fname_txt,lname_txt,email_txt;
                fname_txt=findViewById(R.id.fnametxt);
                lname_txt=findViewById(R.id.lnametxt);
                email_txt=findViewById(R.id.emailtxt);

                Log.d(" data ",profile.getFirstName());
                Log.d(" data ",profile.getLastName());



                fname_txt.setText(profile.getFirstName());
                lname_txt.setText(profile.getLastName());

            }

            @Override
            public void onCancel() {
                Log.d(" data ","ga");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(" data ","hh");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
