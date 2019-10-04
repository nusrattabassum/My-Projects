package com.nts.studentinfofirebase;

public class Student {

    private String name;
    private String sClass;
    private String section;
    private String roll;

    public Student() {
    }

    public Student(String name, String sClass, String section, String roll) {
        this.name = name;
        this.sClass = sClass;
        this.section = section;
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public String getsClass() {
        return sClass;
    }

    public String getSection() {
        return section;
    }

    public String getRoll() {
        return roll;
    }
}
