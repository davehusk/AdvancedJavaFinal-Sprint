package com.gym.model;

import java.sql.Date;

public class Membership {
    private int membershipId;
    private String membershipType;
    private String description;
    private double cost;
    private int userId;
    private Date purchaseDate;
    
    // Getters and setters
    public int getMembershipId() { return membershipId; }
    public void setMembershipId(int membershipId) { this.membershipId = membershipId; }
    public String getMembershipType() { return membershipType; }
    public void setMembershipType(String membershipType) { this.membershipType = membershipType; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public Date getPurchaseDate() { return purchaseDate; }
    public void setPurchaseDate(Date purchaseDate) { this.purchaseDate = purchaseDate; }
    
    @Override
    public String toString() {
        return "Membership ID: " + membershipId + "\n" +
               "Type: " + membershipType + "\n" +
               "Description: " + description + "\n" +
               "Cost: $" + cost + "\n" +
               "Purchase Date: " + purchaseDate;
    }
}