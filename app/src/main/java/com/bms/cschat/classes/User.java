package com.bms.cschat.classes;

public class User {
    String name;
    String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    public User(){

    }

    //Getter's
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }

    //Setter's
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
