package com.example.earnwithskills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.earnwithskills.databinding.ActivityRegHireBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class reg_hire extends AppCompatActivity {
    ActivityRegHireBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegHireBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();


    }

    public void register(View view) {

        binding=ActivityRegHireBinding.inflate(getLayoutInflater());
        if(binding.txtOrgname.getText().toString().compareTo("")==0||binding.txtMail.getText().toString().compareTo("")==0||binding.txtPassword.getText().toString().compareTo("")==0)
        {
            Toast.makeText(reg_hire.this,"Invalid credentials",Toast.LENGTH_SHORT).show();
            return;
        }
        auth.createUserWithEmailAndPassword(binding.txtMail.getText().toString(),binding.txtPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Users user=new Users(binding.nameTxt.getText().toString(),binding.emailTxt.getText().toString(),binding.passwordTxt.getText().toString(),binding.phoneTxt.getText().toString(),binding.cityTxt.getText().toString());
                    String id=task.getResult().getUser().getUid();
                    database.getReference().child("Users").child(id).setValue(user);

                    FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();

                    Toast.makeText(Registration.this,"Registration successful. Please check your mail for verification link.",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Registration.this,Login.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(Registration.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}