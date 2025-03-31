package com.gym.dao;

import com.gym.model.Membership;
import java.sql.SQLException;
import java.util.List;

public interface MembershipDAO {
    Membership createMembership(Membership membership) throws SQLException;
    Membership getMembershipById(int membershipId) throws SQLException;
    List<Membership> getMembershipsByUser(int userId) throws SQLException;
    List<Membership> getAllMemberships() throws SQLException;
    boolean updateMembership(Membership membership) throws SQLException;
    boolean deleteMembership(int membershipId) throws SQLException;
    double getTotalRevenue() throws SQLException;
}