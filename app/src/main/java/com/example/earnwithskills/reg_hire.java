package com.example.earnwithskills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.earnwithskills.Models.Hirer;
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
        //setContentView(binding.getRoot());
        setContentView(R.layout.activity_reg_hire);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();


    }

    public void register(View view) {

        EditText orgname = (EditText) findViewById(R.id.txt_orgname);
        EditText mail = (EditText) findViewById(R.id.txt_mail);
        EditText password = (EditText) findViewById(R.id.txt_password);
        if(orgname.getText().toString().compareTo("")==0||mail.getText().toString().compareTo("")==0||password.getText().toString().compareTo("")==0)
        {
            Toast.makeText(reg_hire.this,"Cannot have empty fields",Toast.LENGTH_SHORT).show();
            //System.out.println("Hello"+binding.txtOrgname.getText().toString()+binding.txtMail.getText().toString()+binding.txtPassword.getText().toString());
            return;
        }
        auth.createUserWithEmailAndPassword(mail.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Hirer hr=new Hirer(orgname.getText().toString(),mail.getText().toString(),password.getText().toString());
                    String id=task.getResult().getUser().getUid();
                    database.getReference().child("Hirer").child(id).setValue(hr);

                    FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();

                    Toast.makeText(reg_hire.this,"Registration successful. Please check your mail for verification link.",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(reg_hire.this,login.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(reg_hire.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}