package com.mohammed.smartstudentmanager.model;

import java.io.Serializable;

public class Student implements Serializable {

    private String name;
    private String id;
    private String department;
    private String email;

    public Student(String name, String id, String department, String email) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.email = email;
    }

    public String getName() { return name; }
    public String getId() { return id; }
    public String getDepartment() { return department; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setDepartment(String department) { this.department = department; }
    public void setEmail(String email) { this.email = email; }
}