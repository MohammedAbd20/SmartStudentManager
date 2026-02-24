package com.mohammed.smartstudentmanager.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mohammed.smartstudentmanager.R;
import com.mohammed.smartstudentmanager.databinding.ActivityLoginBinding;
import com.mohammed.smartstudentmanager.manager.UserManager;


public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    UserManager userManager;
    private static final String LOGIN_PREF = "login_pref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userManager = new UserManager(this);

        binding.btnLogin.setOnClickListener(v -> {
            String u = binding.etUsername.getText().toString().trim();
            String p = binding.etPassword.getText().toString().trim();


            if (u.isEmpty() || p.isEmpty()) {
                Toast.makeText(this, "Fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userManager.login(u, p)) {
                Intent intent = new Intent(this, DashboardActivity.class);
                intent.putExtra("username", u);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Invalid login", Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnRegister.setOnClickListener(v ->
                startActivity(new Intent(this, RegisterActivity.class)));
    }
    private void goToDashboard(String username){
        Intent intent = new Intent(this, DashboardActivity.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }

}