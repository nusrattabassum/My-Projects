package com.nts.recyclerview2;

public class Student {

    private String name;
    private String id;
    private String gender;
    private String dept;
    private String email;
    private String mobileNumber;

    public Student(String name, String id, String gender, String dept, String email, String mobileNumber) {
        this.name = name;
        this.id = id;
        this.gender = gender;
        this.dept = dept;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getDept() {
        return dept;
    }

    public String getEmail() {
        return email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
}
