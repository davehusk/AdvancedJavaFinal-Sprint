package com.gym.dao;

import com.gym.model.Membership;
import com.gym.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MembershipDAOImpl implements MembershipDAO {
    @Override
    public Membership createMembership(Membership membership) throws SQLException {
        String sql = "INSERT INTO memberships (membership_type, description, cost, user_id) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            pstmt.setString(1, membership.getMembershipType());
            pstmt.setString(2, membership.getDescription());
            pstmt.setDouble(3, membership.getCost());
            pstmt.setInt(4, membership.getUserId());
            
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        membership.setMembershipId(rs.getInt(1));
                        membership.setPurchaseDate(rs.getDate("purchase_date"));
                    }
                }
            }
            return membership;
        }
    }

    @Override
    public Membership getMembershipById(int membershipId) throws SQLException {
        String sql = "SELECT * FROM memberships WHERE membership_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, membershipId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return mapResultSetToMembership(rs);
            }
            return null;
        }
    }

    @Override
    public List<Membership> getMembershipsByUser(int userId) throws SQLException {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships WHERE user_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                memberships.add(mapResultSetToMembership(rs));
            }
        }
        return memberships;
    }

    @Override
    public List<Membership> getAllMemberships() throws SQLException {
        List<Membership> memberships = new ArrayList<>();
        String sql = "SELECT * FROM memberships";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                memberships.add(mapResultSetToMembership(rs));
            }
        }
        return memberships;
    }

    @Override
    public boolean updateMembership(Membership membership) throws SQLException {
        String sql = "UPDATE memberships SET membership_type = ?, description = ?, cost = ? WHERE membership_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setString(1, membership.getMembershipType());
            pstmt.setString(2, membership.getDescription());
            pstmt.setDouble(3, membership.getCost());
            pstmt.setInt(4, membership.getMembershipId());
            
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteMembership(int membershipId) throws SQLException {
        String sql = "DELETE FROM memberships WHERE membership_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, membershipId);
            return pstmt.executeUpdate() > 0;
        }
    }

    @Override
    public double getTotalRevenue() throws SQLException {
        String sql = "SELECT SUM(cost) AS total FROM memberships";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            if (rs.next()) {
                return rs.getDouble("total");
            }
            return 0;
        }
    }

    private Membership mapResultSetToMembership(ResultSet rs) throws SQLException {
        Membership membership = new Membership();
        membership.setMembershipId(rs.getInt("membership_id"));
        membership.setMembershipType(rs.getString("membership_type"));
        membership.setDescription(rs.getString("description"));
        membership.setCost(rs.getDouble("cost"));
        membership.setUserId(rs.getInt("user_id"));
        membership.setPurchaseDate(rs.getDate("purchase_date"));
        return membership;
    }
}