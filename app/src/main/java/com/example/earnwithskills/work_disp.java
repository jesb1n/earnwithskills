package com.example.earnwithskills;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.earnwithskills.Models.Work;
import com.example.earnwithskills.databinding.ActivityWorkDispBinding;

public class work_disp extends AppCompatActivity {
    Work w;
    ActivityWorkDispBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_work_disp);
        binding=ActivityWorkDispBinding.inflate(getLayoutInflater());
        w=(Work)getIntent().getSerializableExtra("cur");
        binding.txtOrg.setText(w.getOrg());
        binding.txtJob.setText(w.getTopic());
        binding.txtSkill.setText(w.getSkill());
        binding.txtAmount.setText(w.getAmount());
    }

}