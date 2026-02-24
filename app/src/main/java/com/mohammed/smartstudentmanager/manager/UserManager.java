package com.mohammed.smartstudentmanager.manager;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mohammed.smartstudentmanager.model.User;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserManager {

    private static final String PREF_NAME = "users_pref";
    private static final String USERS_KEY = "users";

    private SharedPreferences prefs;
    private Gson gson;

    public UserManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        gson = new Gson();
    }

    public void registerUser(User user) {
        ArrayList<User> users = getUsers();
        users.add(user);
        saveUsers(users);
    }

    public boolean login(String username, String password) {
        for (User user : getUsers()) {
            if (user.getUsername().equals(username) &&
                    user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    private void saveUsers(ArrayList<User> users) {
        String json = gson.toJson(users);
        prefs.edit().putString(USERS_KEY, json).apply();
    }

    public ArrayList<User> getUsers() {
        String json = prefs.getString(USERS_KEY, null);
        if (json == null) return new ArrayList<>();
        Type type = new TypeToken<ArrayList<User>>(){}.getType();
        return gson.fromJson(json, type);
    }
}