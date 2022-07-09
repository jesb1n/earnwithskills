package com.example.earnwithskills;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.earnwithskills.databinding.ActivityLoginBinding;
import com.example.earnwithskills.databinding.ActivityLoginTypeBinding;

public class login extends AppCompatActivity {
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void ulogin(View view) {

    }
}