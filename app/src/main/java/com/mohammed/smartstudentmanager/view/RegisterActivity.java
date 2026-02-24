package com.mohammed.smartstudentmanager.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mohammed.smartstudentmanager.R;
import com.mohammed.smartstudentmanager.databinding.ActivityRegisterBinding;
import com.mohammed.smartstudentmanager.manager.UserManager;
import com.mohammed.smartstudentmanager.model.User;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userManager = new UserManager(this);

        binding.btnRegister.setOnClickListener(v -> {

            String username = binding.etUsername.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields required", Toast.LENGTH_SHORT).show();
                return;
            }

            userManager.registerUser(new User(username, password));
            Toast.makeText(this, "Registered Successfully", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}