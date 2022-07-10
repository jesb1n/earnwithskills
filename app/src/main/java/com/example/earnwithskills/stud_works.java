package com.example.earnwithskills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.earnwithskills.Models.Work;
import com.example.earnwithskills.Models.WorkAdapter;
import com.example.earnwithskills.databinding.ActivityLoginHireBinding;
import com.example.earnwithskills.databinding.ActivityStudWorksBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class stud_works extends AppCompatActivity {
    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String userID;
    ArrayList<Work> workList;
    WorkAdapter workAdapter;
    WorkAdapter.CardClickListener cardClickListener;
    Query query;
    RecyclerView recyclerView;
    ActivityStudWorksBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_stud_works);
        binding= ActivityStudWorksBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        userID= FirebaseAuth.getInstance().getCurrentUser().getUid();

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView=binding.recView;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        setOnClickListener();
        workList=new ArrayList<>();

        workAdapter=new WorkAdapter(workList,cardClickListener);
        query =FirebaseDatabase.getInstance().getReference("Work");
        query.addListenerForSingleValueEvent(valueEventListener);
        recyclerView.setAdapter(workAdapter);

    }

    private void setOnClickListener() {
        cardClickListener=new WorkAdapter.CardClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent=new Intent(stud_works.this,work_disp.class);
                //intent.addFlags(intent.FLAG_ACTIVITY_NO_ANIMATION);
                //intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("cur", (Serializable) workList.get(position));
                startActivity(intent);
                //overridePendingTransition(0,0);
            }
        };
    }

    ValueEventListener valueEventListener=new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            workList.clear();
            if(snapshot.exists())
            {
                for(DataSnapshot dataSnapshot:snapshot.getChildren())
                {
                    Work w=dataSnapshot.getValue(Work.class);


                        workList.add(w);

                }

                workAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}