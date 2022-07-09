package com.example.earnwithskills;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.earnwithskills.databinding.ActivityLoginBinding;


public class login extends AppCompatActivity {
    ActivityLoginBinding binding;
    String type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getString("key");
            //The key argument here must match that used in the other activity
        }
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void ulogin(View view) {

    }

    public void register(View view) {

        if(type.compareTo("hirer")==0) {
            Intent i = new Intent(login.this, reg_hire.class);
            startActivity(i);
        }


    }
}