package com.mohammed.smartstudentmanager.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mohammed.smartstudentmanager.R;
import com.mohammed.smartstudentmanager.databinding.ActivityAddStudentBinding;
import com.mohammed.smartstudentmanager.manager.StudentManager;
import com.mohammed.smartstudentmanager.model.Student;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity {

    private ActivityAddStudentBinding binding;
    private StudentManager manager;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddStudentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        username = getIntent().getStringExtra("username");

        if (username == null) {
            finish();
            return;
        }

        manager = new StudentManager(this, username);

        binding.btnSave.setOnClickListener(v -> {

            String name = binding.etName.getText().toString().trim();
            String id = binding.etId.getText().toString().trim();
            String dept = binding.etDepartment.getText().toString().trim();
            String email = binding.etEmail.getText().toString().trim();

            if (name.isEmpty()) {
                Toast.makeText(this, "Name and ID required", Toast.LENGTH_SHORT).show();
                binding.etName.setError("Required");

                return;
            }else if (id.isEmpty()) {
                Toast.makeText(this, "Name and ID required", Toast.LENGTH_SHORT).show();
                binding.etId.setError("Required");

                return;
            }else if (dept.isEmpty()) {
                binding.etDepartment.setError("Required");
                return;
            }else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.etEmail.setError("Invalid email format");
                return;
            }

            ArrayList<Student> students = manager.getStudents();
            students.add(new Student(name, id, dept, email));
            manager.saveStudents(students);

            Toast.makeText(this, "Student Added", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}