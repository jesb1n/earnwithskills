package com.example.earnwithskills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.earnwithskills.Models.Student;
import com.example.earnwithskills.databinding.ActivityRegHireBinding;
import com.example.earnwithskills.databinding.ActivityStudDetBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class stud_det extends AppCompatActivity {

    ActivityStudDetBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String userID;
    Student st;
    TextView name,college,degree,year,mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_stud_det);
        binding= ActivityStudDetBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        name=binding.txtName;
        college=binding.txtCollege;
        degree= binding.txtDegree;
        year=binding.txtYear;
        mail=binding.txtMail;

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        userID= FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Student").child(userID);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                st=snapshot.getValue(Student.class);
                name.setText(name.getText().toString()+st.getName());
                college.setText(college.getText().toString()+st.getCollege());
                degree.setText(degree.getText().toString()+st.getDegree());
                year.setText(year.getText().toString()+st.getYear());
                mail.setText(mail.getText().toString()+st.getMail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });



    }

    public void addSkill(View view) {
        Intent i=new Intent(stud_det.this,add_skill.class);
        startActivity(i);
    }

    public void logout(View view) {
        auth.signOut();
        Intent i=new Intent(stud_det.this,login_type.class);
        startActivity(i);
    }

    public void viewWorks(View view) {
        Intent i=new Intent(stud_det.this,stud_works.class);
        startActivity(i);
    }
}