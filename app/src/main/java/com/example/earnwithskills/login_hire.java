package com.example.earnwithskills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.earnwithskills.Models.Hirer;
import com.example.earnwithskills.Models.Student;
import com.example.earnwithskills.databinding.ActivityLoginHireBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login_hire extends AppCompatActivity {
    ActivityLoginHireBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String userID;
    EditText name,mail;
    Hirer hr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginHireBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecyclerView recyclerView= findViewById(R.id.recycle_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        name=binding.txtOrg;
        mail=binding.txtEmail;

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        userID= FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Hirer").child(userID);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                hr=snapshot.getValue(Hirer.class);
                name.setText(hr.getOrg_name());
                mail.setText(hr.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });

    }

    public void logout(View view) {
        auth.signOut();
        Intent i=new Intent(login_hire.this,login_type.class);
        startActivity(i);
    }

    public void addwork(View view) {
        Intent i=onNewIntent(login_hire.this,);
    }
}