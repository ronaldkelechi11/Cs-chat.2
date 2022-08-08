package com.bms.cschat.classes;

public class User {
    String email;
    String nickname;

    public User(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
    public User(){

    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



}
