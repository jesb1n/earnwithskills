package com.example.earnwithskills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.earnwithskills.Models.Student;
import com.example.earnwithskills.databinding.ActivityAddSkillBinding;
import com.example.earnwithskills.databinding.ActivityStudDetBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class add_skill extends AppCompatActivity {
    ActivityAddSkillBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String uid;
    EditText skill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddSkillBinding.inflate(getLayoutInflater());
        //setContentView(R.layout.activity_add_skill);
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        uid=auth.getCurrentUser().getUid();

        database.getReference().child("Student").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Student st=snapshot.getValue(Student.class);
                ArrayList<String> sk = st.getSkills();
                sk.remove("empty");
                binding.txt.setText(sk.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        




    }

    public void addSkill(View view) {

        uid=auth.getCurrentUser().getUid();
        skill=(EditText)findViewById(R.id.txt_skill);
        if(skill.getText().toString()!=null)
        {
            database.getReference().child("Student").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Student st=snapshot.getValue(Student.class);
                    ArrayList<String> sk = st.getSkills();
                    sk.add(skill.getText().toString());
                    database.getReference().child("Student").child(uid).child("skills").setValue(sk);
                    Intent intent=new Intent(add_skill.this,stud_det.class);
                    startActivity(intent);
                    //finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}