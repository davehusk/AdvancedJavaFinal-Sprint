package com.gym.model;

public class Admin extends User {
    public Admin() {
        setRole("ADMIN");
    }
    
    public Admin(String username, String password, String email, 
               String phoneNumber, String address) {
        super(username, password, email, phoneNumber, address, "ADMIN");
    }
}