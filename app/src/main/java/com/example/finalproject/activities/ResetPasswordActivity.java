package com.example.finalproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.finalproject.databinding.ActivityResetPasswordBinding;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPasswordActivity extends AppCompatActivity {
    private ActivityResetPasswordBinding binding;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();


        binding = ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
    }

    private void setListeners(){

        binding.buttonReset.setOnClickListener(v -> {
            if (binding.inputEmail.getText().toString().equals("")) {
                showToast("Email is required!");
            } else {
                firebaseAuth.sendPasswordResetEmail(binding.inputEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            showToast("Please check your Email");
                            startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                            finish();
                        } else {
                            String error = task.getException().getMessage();
                            showToast(error);
                        }
                    }
                });
            }
        });


        binding.imageBack.setOnClickListener(v -> onBackPressed());
    }

    private void showToast(String message) {
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }
}