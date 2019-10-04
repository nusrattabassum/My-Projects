package com.nts.r_viewitem2;

public class User {

    private String name;
    private String phoneNO;
    private String email;

    public User(String name, String phoneNO, String email) {
        this.name = name;
        this.phoneNO = phoneNO;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNO() {
        return phoneNO;
    }

    public String getEmail() {
        return email;
    }
}
