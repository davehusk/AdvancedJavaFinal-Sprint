package com.gym.service;

import com.gym.dao.MembershipDAO;
import com.gym.model.Membership;
import java.sql.SQLException;
import java.util.List;

public class MembershipService {
    private final MembershipDAO membershipDAO;
    
    public MembershipService(MembershipDAO membershipDAO) {
        this.membershipDAO = membershipDAO;
    }
    
    public Membership createMembership(String type, String description, double cost, int userId) throws SQLException {
        Membership membership = new Membership();
        membership.setMembershipType(type);
        membership.setDescription(description);
        membership.setCost(cost);
        membership.setUserId(userId);
        return membershipDAO.createMembership(membership);
    }
    
    public List<Membership> getUserMemberships(int userId) throws SQLException {
        return membershipDAO.getMembershipsByUser(userId);
    }
    
    public List<Membership> getAllMemberships() throws SQLException {
        return membershipDAO.getAllMemberships();
    }
    
    public double calculateUserExpenses(int userId) throws SQLException {
        List<Membership> memberships = membershipDAO.getMembershipsByUser(userId);
        return memberships.stream().mapToDouble(Membership::getCost).sum();
    }
    
    public double getTotalRevenue() throws SQLException {
        return membershipDAO.getTotalRevenue();
    }
    
    public boolean deleteMembership(int membershipId) throws SQLException {
        return membershipDAO.deleteMembership(membershipId);
    }
}