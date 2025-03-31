package com.gym.model;

public abstract class User {
    protected int userId;
    protected String username;
    protected String password;
    protected String email;
    protected String phoneNumber;
    protected String address;
    protected String role;
    
    public User() {}
    
    public User(String username, String password, String email, 
               String phoneNumber, String address, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
    }
    
    // Getters and setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    
    @Override
    public String toString() {
        return "User ID: " + userId + "\n" +
               "Username: " + username + "\n" +
               "Email: " + email + "\n" +
               "Phone: " + phoneNumber + "\n" +
               "Address: " + address + "\n" +
               "Role: " + role;
    }
}