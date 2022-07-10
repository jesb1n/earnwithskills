package com.example.earnwithskills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.earnwithskills.Models.Hirer;
import com.example.earnwithskills.Models.Student;
import com.example.earnwithskills.Models.Work;
import com.example.earnwithskills.Models.WorkAdapter;
import com.example.earnwithskills.databinding.ActivityLoginHireBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class login_hire extends AppCompatActivity {
    ActivityLoginHireBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    String userID;
    EditText name,mail;
    Hirer hr;
    ArrayList<Work> workList;
    WorkAdapter workAdapter;
    WorkAdapter.CardClickListener cardClickListener;
    Query query;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginHireBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        RecyclerView recyclerView= findViewById(R.id.recycle_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView=binding.recycleView;
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
            public void onClick(View view,int position) {
                Intent intent=new Intent(login_hire.this,work_disp.class);
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

                    if(w.getOrg().compareTo(userID)==0)
                    {
                        workList.add(w);
                    }
                }

                workAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    public void logout(View view) {
        auth.signOut();
        Intent i=new Intent(login_hire.this,login_type.class);
        startActivity(i);
    }

    public void addwork(View view) {
        Intent i=new Intent(login_hire.this,add_work.class);
        startActivity(i);
    }
}