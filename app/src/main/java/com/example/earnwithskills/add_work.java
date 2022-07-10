package com.example.earnwithskills;

import static java.lang.Math.abs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.earnwithskills.Models.Work;
import com.example.earnwithskills.databinding.ActivityAddWorkBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Random;
public class add_work extends AppCompatActivity {

    ActivityAddWorkBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String userID,id;
    EditText topic,skill,amount;
    String t,s,a;
    Work w;
    Random random=new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_work);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        userID= FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    public void submit(View view) {
        topic=(EditText) findViewById(R.id.txt_topic);
        skill=(EditText) findViewById(R.id.txt_skill);
        amount=(EditText) findViewById(R.id.txt_amount);

        t=topic.getText().toString();
        s=skill.getText().toString();
        a=amount.getText().toString();

        if(t.compareTo("")==0||s.compareTo("")==0||a.compareTo("")==0)
        {
            Toast.makeText(add_work.this,"All fields are mandatory",Toast.LENGTH_SHORT).show();
            return;
        }

        id=String.valueOf(abs(random.nextLong()));
        w=new Work(id,t,s,userID,a);

        database.getReference().child("Work").child(id).setValue(w);

        Intent i=new Intent(add_work.this,login_hire.class);
        startActivity(i);

    }
}