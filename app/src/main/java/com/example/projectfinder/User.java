package com.example.projectfinder;

import java.util.ArrayList;

public class User {
    String name;
    String email;
    ArrayList<String> userInterests;
    public User(String name, String email){
        this.name = name;
        this.email = email;
        userInterests = new ChooseClassifications()
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
