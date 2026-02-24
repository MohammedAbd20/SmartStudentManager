package com.mohammed.smartstudentmanager.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatDelegate;

import com.mohammed.smartstudentmanager.R;
import com.mohammed.smartstudentmanager.databinding.ActivityDashboardBinding;

public class DashboardActivity extends AppCompatActivity {

    private ActivityDashboardBinding binding;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        username = getIntent().getStringExtra("username");

        binding.btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddStudentActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        binding.btnList.setOnClickListener(v -> {
            Intent intent = new Intent(this, StudentListActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });

        binding.btnAbout.setOnClickListener(v ->
                startActivity(new Intent(this, AboutActivity.class)));

        binding.btnStats.setOnClickListener(v -> {
            Intent intent = new Intent(this, StatisticsActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        });


    }

}