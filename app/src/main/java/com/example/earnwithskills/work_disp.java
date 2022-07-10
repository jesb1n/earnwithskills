package com.example.earnwithskills;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.earnwithskills.Models.Hirer;
import com.example.earnwithskills.Models.Student;
import com.example.earnwithskills.Models.Work;
import com.example.earnwithskills.databinding.ActivityWorkDispBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class work_disp extends AppCompatActivity {
    Work w;
    ActivityWorkDispBinding binding;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    Hirer hr;
    TextView org;
    String mail="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_work_disp);
        binding=ActivityWorkDispBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        w=(Work)getIntent().getSerializableExtra("cur");
        binding.txtOrg.setText("Org: "+w.getOrg());
        binding.txtJob.setText("Job: "+w.getTopic());
        binding.txtSkill.setText("Skill: "+w.getSkill());
        binding.txtAmount.setText("Amount: "+w.getAmount());

        org=binding.txtOrg;

        databaseReference=FirebaseDatabase.getInstance().getReference().child("Hirer").child(w.getOrg());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                hr=snapshot.getValue(Hirer.class);
                org.setText("Org: "+hr.getOrg_name());
                binding.txtMail.setText("Mail: "+hr.getEmail());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });

    }

    public void apply(View view) {
        TextView m=(TextView)findViewById(R.id.txt_mail);
        Intent emailIntent = new Intent(Intent.ACTION_VIEW);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Request for job");
        emailIntent.setData(Uri.parse("mailto:"+m.getText().toString() + "?subject=" + "Job request"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{m.getText().toString()});
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }
}