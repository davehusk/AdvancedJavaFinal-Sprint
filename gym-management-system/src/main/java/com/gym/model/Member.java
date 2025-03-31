package com.gym.model;

public class Member extends User {
    public Member() {
        setRole("MEMBER");
    }
    
    public Member(String username, String password, String email, 
               String phoneNumber, String address) {
        super(username, password, email, phoneNumber, address, "MEMBER");
    }
}