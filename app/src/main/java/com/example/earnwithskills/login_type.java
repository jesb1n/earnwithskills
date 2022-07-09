package com.example.earnwithskills;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.earnwithskills.databinding.ActivityLoginTypeBinding;

public class login_type extends AppCompatActivity {

    ActivityLoginTypeBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginTypeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        setContentView(R.layout.activity_login_type);

    }

    public void hirer(View view) {
        String x="hirer";
        Intent i = new Intent(login_type.this, login.class);
        i.putExtra("key",x);
        startActivity(i);
    }

    public void student(View view) {
        String x="student";
        Intent i = new Intent(login_type.this, login.class);
        i.putExtra("key",x);
        startActivity(i);
    }
}