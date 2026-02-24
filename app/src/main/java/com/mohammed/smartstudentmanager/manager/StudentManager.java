package com.mohammed.smartstudentmanager.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mohammed.smartstudentmanager.model.Student;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StudentManager {

    private SharedPreferences prefs;
    private Gson gson;
    private String key;

    public StudentManager(Context context, String username) {
        prefs = context.getSharedPreferences("students_pref", Context.MODE_PRIVATE);
        gson = new Gson();
        key = "students_" + username;
    }

    public ArrayList<Student> getStudents() {
        String json = prefs.getString(key, null);
        if (json == null) return new ArrayList<>();
        Type type = new TypeToken<ArrayList<Student>>(){}.getType();
        return gson.fromJson(json, type);
    }

    public void saveStudents(ArrayList<Student> students) {
        prefs.edit().putString(key, gson.toJson(students)).apply();
    }
}