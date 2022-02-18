package com.example.onlinefruitvendor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private Button createAccontButton;
    private EditText InputName, InputPhoneNumber, InputPassword;
    private ProgressDialog loadinBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        createAccontButton = findViewById(R.id.register_btn);
        InputName = findViewById(R.id.register_username_input);
        InputPhoneNumber = findViewById(R.id.register_phone_number_input);
        InputPassword = findViewById(R.id.register_password_input);
        loadinBar = new ProgressDialog(this);

        createAccontButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            CreateAccount();
            }
        });
    }

    private void CreateAccount(){

        String name = InputName.getText().toString();
        String phone = InputPhoneNumber.getText().toString();
        String password = InputPassword.getText().toString();

        if (TextUtils.isEmpty(name)){

            Toast.makeText(RegisterActivity.this, "Please Enter Your Name!", Toast.LENGTH_SHORT).show();

        }else if(TextUtils.isEmpty(phone)){
            Toast.makeText(RegisterActivity.this, "Please Enter Your Phone Number!", Toast.LENGTH_SHORT).show();

        }else if (TextUtils.isEmpty(password)){
            Toast.makeText(RegisterActivity.this, "Please Enter Your Password!", Toast.LENGTH_SHORT).show();

        }else {

            loadinBar.setTitle("Create Account");
            loadinBar.setMessage("Please wait while we check Credentials");
            loadinBar.setCanceledOnTouchOutside(false);
            loadinBar.show();

            ValidatePhoneNumber(name, phone, password);

        }
    }

    private void ValidatePhoneNumber(final String name, final String phone, final String password){

        final DatabaseReference RootRef;

        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (!(snapshot.child("Users").child(phone).exists())){

                    HashMap<String, Object> userDataMap = new HashMap<>();

                    userDataMap.put("phone", phone);
                    userDataMap.put("name", name);
                    userDataMap.put("password", password);

                    RootRef.child("Users").child(phone).updateChildren(userDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()){

                                Toast.makeText(RegisterActivity.this, "Congratulation! " + name + " Account Created", Toast.LENGTH_SHORT).show();
                                loadinBar.dismiss();

                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }else {

                                loadinBar.dismiss();
                                Toast.makeText(RegisterActivity.this, "Network error. Check connection or try again later!", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }else {

                    Toast.makeText(RegisterActivity.this, "This " + phone + " already exists!", Toast.LENGTH_SHORT).show();
                    loadinBar.dismiss();
                    Toast.makeText(RegisterActivity.this, "Register with a new phone number or Sign In if already have an account", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
