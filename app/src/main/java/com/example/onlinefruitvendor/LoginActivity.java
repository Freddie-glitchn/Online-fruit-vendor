package com.example.onlinefruitvendor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinefruitvendor.Model.Users;
import com.example.onlinefruitvendor.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {

    private EditText InputPhoneNumber, InputPassword;
    private Button LoginButton;
    private ProgressDialog loadingBar;
    private TextView AdminLink, NotAdminLink;
    private String parentDbName = "Users";
    private CheckBox checkBoxRememberMe;

//        public void Login(View view){
//
//            Log.i("Button Tapped", "Boink!");
//        LoginUser();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginButton = findViewById(R.id.login_btn);
        InputPassword = findViewById(R.id.login_password);
        InputPhoneNumber = findViewById(R.id.login_phone_number);
        AdminLink = findViewById(R.id.admin_panel_link);
        NotAdminLink = findViewById(R.id.notadmin_panel_link);

        loadingBar = new ProgressDialog(this);
        checkBoxRememberMe = findViewById(R.id.remember_me_checkbox);

        Paper.init(this);

        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginUser();
            }
        });



        AdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginButton.setText("Login Admin");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbName = "Admins";
            }
        });

        NotAdminLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                LoginButton.setText("Login");
                AdminLink.setVisibility(View.INVISIBLE);
                NotAdminLink.setVisibility(View.VISIBLE);
                parentDbName = "Users";
            }
        });
    }

//    public void Login(View view){
//
//        LoginUser();
//    }

    private void LoginUser(){

        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(phone)){

            Toast.makeText(this, "Please Enter Your Phone Number", Toast.LENGTH_LONG).show();

        }else if (TextUtils.isEmpty(password)){

            Toast.makeText(this, "Please Enter Your Password", Toast.LENGTH_LONG).show();

        }else {

            loadingBar.setTitle("Login Account");
            loadingBar.setMessage("Please wait while we validate your credentials");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(phone, password);
        }
    }

    private void AllowAccessToAccount(final  String phone, final String password){

        if (checkBoxRememberMe.isChecked()){

            Paper.book().write(Prevalent.UserPhoneKey, phone);
            Paper.book().write(Prevalent.UserPasswordKey, password);

        }

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(parentDbName).child(phone).exists()){

                    Users userData = snapshot.child(parentDbName).child(phone).getValue(Users.class);

                    if (userData.getPhone().equals(phone)){

                        if (userData.getPassword().equals(password)){

                            if (parentDbName.equals("Admins")){

                                Toast.makeText(LoginActivity.this, "Admin Login Successful", Toast.LENGTH_LONG).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, AdminCategory.class);
                                startActivity(intent);
                            }else if (parentDbName.equals("Users")){

                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                Prevalent.currentOnlineListener = userData;
                                startActivity(intent);

                            }
                        }else {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is Incorrect", Toast.LENGTH_LONG).show();

                        }
                    }
                }else {

                    Toast.makeText(LoginActivity.this, phone +" Account Does Not Exists. Please check phone Number or Sign Up for an Account!", Toast.LENGTH_LONG).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled( DatabaseError error) {

            }
        });
    }
}
