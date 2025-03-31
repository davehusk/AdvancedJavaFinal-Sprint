package com.gym.model;

public class Trainer extends User {
    public Trainer() {
        setRole("TRAINER");
    }
    
    public Trainer(String username, String password, String email, 
               String phoneNumber, String address) {
        super(username, password, email, phoneNumber, address, "TRAINER");
    }
}