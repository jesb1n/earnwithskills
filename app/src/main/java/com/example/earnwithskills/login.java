package com.example.earnwithskills;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.earnwithskills.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;


public class login extends AppCompatActivity {
    ActivityLoginBinding binding;
    String type;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getString("key");
            //The key argument here must match that used in the other activity
        }
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(binding.txtMail.getText().toString().compareTo("")==0||binding.txtPassword.getText().toString().compareTo("")==0)
                {
                    Toast.makeText(login.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(binding.txtMail.getText().toString(),binding.txtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            if(FirebaseAuth.getInstance().getCurrentUser().isEmailVerified()) {
                                if(type.compareTo("student")==0) {
                                    Intent intent = new Intent(login.this, stud_det.class);

                                    startActivity(intent);
                                    //finish();
                                }
                                else
                                {
                                    Intent intent = new Intent(login.this, login_hire.class);

                                    startActivity(intent);
                                }
                            }
                            else
                            {
                                FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();
                                Toast.makeText(login.this, "Email not verified. Please check your mail for verification link", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(login.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

//        if(FirebaseAuth.getInstance().getCurrentUser()!=null&&FirebaseAuth.getInstance().getCurrentUser().isEmailVerified())
//        {
//            if(type.compareTo("student")==0) {
//                Intent intent = new Intent(login.this, stud_det.class);
//                startActivity(intent);
//                //finish();
//            }
//        }
    }

    public void ulogin(View view) {

    }

    public void register(View view) {

        if(type.compareTo("hirer")==0) {
            Intent i = new Intent(login.this, reg_hire.class);
            startActivity(i);
        }
        else
        {
            Intent i = new Intent(login.this, reg_student.class);
            startActivity(i);
        }



    }
}