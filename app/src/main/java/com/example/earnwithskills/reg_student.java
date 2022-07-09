package com.example.earnwithskills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.earnwithskills.Models.Hirer;
import com.example.earnwithskills.Models.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class reg_student extends AppCompatActivity {
    private FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_student);
        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
    }


    public void register(View view) {
        EditText name = (EditText) findViewById(R.id.txt_name);
        EditText college = (EditText) findViewById(R.id.txt_college);
        EditText degree = (EditText) findViewById(R.id.txt_degree);
        EditText year = (EditText) findViewById(R.id.txt_year);
        EditText mail = (EditText) findViewById(R.id.txt_mail);
        EditText password = (EditText) findViewById(R.id.txt_password);

        if(name.getText().toString().compareTo("")==0||college.getText().toString().compareTo("")==0||degree.getText().toString().compareTo("")==0||year.getText().toString().compareTo("")==0||mail.getText().toString().compareTo("")==0||password.getText().toString().compareTo("")==0)
        {
            Toast.makeText(reg_student.this,"Cannot have empty fields",Toast.LENGTH_SHORT).show();
            //System.out.println("Hello"+binding.txtOrgname.getText().toString()+binding.txtMail.getText().toString()+binding.txtPassword.getText().toString());
            return;
        }
        if(!mail.getText().toString().endsWith(".edu.in"))
        {
            Toast.makeText(reg_student.this,"Enter a valid mail id",Toast.LENGTH_SHORT).show();
        }
        auth.createUserWithEmailAndPassword(mail.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(task.isSuccessful())
                {
                    Student st=new Student(name.getText().toString(),college.getText().toString(),degree.getText().toString(),year.getText().toString(),mail.getText().toString(),password.getText().toString());
                    String id=task.getResult().getUser().getUid();
                    database.getReference().child("Student").child(id).setValue(st);

                    FirebaseAuth.getInstance().getCurrentUser().sendEmailVerification();

                    Toast.makeText(reg_student.this,"Registration successful. Please check your mail for verification link.",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(reg_student.this,login.class);
                    intent.putExtra("key","student");
                    startActivity(intent);
                    //finish();
                }
                else
                {
                    Toast.makeText(reg_student.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}