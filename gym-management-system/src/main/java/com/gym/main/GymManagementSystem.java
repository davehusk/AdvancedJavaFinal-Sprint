package com.gym.main;

import com.gym.dao.UserDAOImpl;
import com.gym.dao.MembershipDAOImpl;
import com.gym.dao.WorkoutClassDAOImpl;
import com.gym.model.*;
import com.gym.service.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class GymManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static User currentUser;
    private static final UserService userService = new UserService(new UserDAOImpl());
    private static final MembershipService membershipService = new MembershipService(new MembershipDAOImpl());
    private static final WorkoutClassService workoutClassService = new WorkoutClassService(new WorkoutClassDAOImpl());
    
    public static void main(String[] args) {
        System.out.println("=== Gym Management System ===");
        
        while (true) {
            if (currentUser == null) {
                showMainMenu();
            } else {
                switch (currentUser.getRole()) {
                    case "ADMIN":
                        showAdminMenu();
                        break;
                    case "TRAINER":
                        showTrainerMenu();
                        break;
                    case "MEMBER":
                        showMemberMenu();
                        break;
                }
            }
        }
    }
    
    private static void showMainMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        System.out.print("Choose option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        switch (choice) {
            case 1:
                loginUser();
                break;
            case 2:
                registerUser();
                break;
            case 3:
                System.exit(0);
            default:
                System.out.println("Invalid option!");
        }
    }
    
    private static void loginUser() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        
        try {
            currentUser = userService.loginUser(username, password);
            if (currentUser == null) {
                System.out.println("Invalid username or password!");
            } else {
                System.out.println("Login successful! Welcome, " + currentUser.getUsername());
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }
    
    private static void registerUser() {
        System.out.println("\nUser Registration:");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Role (ADMIN/TRAINER/MEMBER): ");
        String role = scanner.nextLine().toUpperCase();
        
        try {
            User user = userService.registerUser(username, password, email, phone, address, role);
            System.out.println("Registration successful! You can now login.");
        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
        }
    }
    
    private static void showAdminMenu() {
        System.out.println("\nAdmin Menu:");
        System.out.println("1. View All Users");
        System.out.println("2. Delete User");
        System.out.println("3. View Revenue Report");
        System.out.println("4. Logout");
        System.out.print("Choose option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        try {
            switch (choice) {
                case 1:
                    viewAllUsers();
                    break;
                case 2:
                    deleteUser();
                    break;
                case 3:
                    viewRevenueReport();
                    break;
                case 4:
                    currentUser = null;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void viewAllUsers() throws SQLException {
        List<User> users = userService.getAllUsers();
        System.out.println("\nAll Users:");
        for (User user : users) {
            System.out.println(user);
            System.out.println("-------------------");
        }
    }
    
    private static void deleteUser() throws SQLException {
        System.out.print("Enter User ID to delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        
        if (userService.deleteUser(userId)) {
            System.out.println("User deleted successfully!");
        } else {
            System.out.println("Failed to delete user or user not found.");
        }
    }
    
    private static void viewRevenueReport() throws SQLException {
        double totalRevenue = membershipService.getTotalRevenue();
        System.out.printf("\nTotal Revenue: $%.2f\n", totalRevenue);
    }
    
    private static void showTrainerMenu() {
        System.out.println("\nTrainer Menu:");
        System.out.println("1. View My Classes");
        System.out.println("2. Add New Class");
        System.out.println("3. Update Class");
        System.out.println("4. Delete Class");
        System.out.println("5. Purchase Membership");
        System.out.println("6. Logout");
        System.out.print("Choose option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        try {
            switch (choice) {
                case 1:
                    viewTrainerClasses();
                    break;
                case 2:
                    addNewClass();
                    break;
                case 3:
                    updateClass();
                    break;
                case 4:
                    deleteClass();
                    break;
                case 5:
                    purchaseMembership();
                    break;
                case 6:
                    currentUser = null;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        } catch (SQLException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void viewTrainerClasses() throws SQLException {
        List<WorkoutClass> classes = workoutClassService.getTrainerClasses(currentUser.getUserId());
        System.out.println("\nYour Classes:");
        for (WorkoutClass wc : classes) {
            System.out.println(wc);
            System.out.println("-------------------");
        }
    }
    
    private static void addNewClass() throws SQLException, ParseException {
        System.out.print("Class Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String desc = scanner.nextLine();
        System.out.print("Schedule (yyyy-MM-dd HH:mm): ");
        String dateStr = scanner.nextLine();
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = format.parse(dateStr);
        Timestamp timestamp = new Timestamp(date.getTime());
        
        WorkoutClass workoutClass = workoutClassService.createClass(
            name, desc, timestamp, currentUser.getUserId());
        
        System.out.println("Class created successfully! ID: " + workoutClass.getClassId());
    }
    
    private static void updateClass() throws SQLException, ParseException {
        System.out.print("Enter Class ID to update: ");
        int classId = scanner.nextInt();
        scanner.nextLine();
        
        WorkoutClass existing = workoutClassService.getClassById(classId);
        if (existing == null || existing.getTrainerId() != currentUser.getUserId()) {
            System.out.println("Class not found or you don't have permission to edit it.");
            return;
        }
        
        System.out.print("New Class Name (leave blank to keep current): ");
        String name = scanner.nextLine();
        System.out.print("New Description (leave blank to keep current): ");
        String desc = scanner.nextLine();
        System.out.print("New Schedule (yyyy-MM-dd HH:mm or leave blank): ");
        String dateStr = scanner.nextLine();
        
        if (!name.isEmpty()) existing.setClassName(name);
        if (!desc.isEmpty()) existing.setDescription(desc);
        if (!dateStr.isEmpty()) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date = format.parse(dateStr);
            existing.setSchedule(new Timestamp(date.getTime()));
        }
        
        if (workoutClassService.updateClass(existing)) {
            System.out.println("Class updated successfully!");
        } else {
            System.out.println("Failed to update class.");
        }
    }
    
    private static void deleteClass() throws SQLException {
        System.out.print("Enter Class ID to delete: ");
        int classId = scanner.nextInt();
        scanner.nextLine();
        
        WorkoutClass existing = workoutClassService.getClassById(classId);
        if (existing == null || existing.getTrainerId() != currentUser.getUserId()) {
            System.out.println("Class not found or you don't have permission to delete it.");
            return;
        }
        
        if (workoutClassService.deleteClass(classId)) {
            System.out.println("Class deleted successfully!");
        } else {
            System.out.println("Failed to delete class.");
        }
    }
    
    private static void purchaseMembership() throws SQLException {
        System.out.println("\nAvailable Membership Types:");
        System.out.println("1. Basic ($30/month)");
        System.out.println("2. Premium ($50/month)");
        System.out.println("3. VIP ($100/month)");
        System.out.print("Choose type: ");
        
        int typeChoice = scanner.nextInt();
        scanner.nextLine();
        
        String type;
        String desc;
        double cost;
        
        switch (typeChoice) {
            case 1:
                type = "Basic";
                desc = "Basic gym access";
                cost = 30.00;
                break;
            case 2:
                type = "Premium";
                desc = "Gym access + classes";
                cost = 50.00;
                break;
            case 3:
                type = "VIP";
                desc = "All access + personal trainer";
                cost = 100.00;
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        
        Membership membership = membershipService.createMembership(type, desc, cost, currentUser.getUserId());
        System.out.printf("Membership purchased successfully! ID: %d, Cost: $%.2f\n", 
                         membership.getMembershipId(), membership.getCost());
    }
    
    private static void showMemberMenu() {
        System.out.println("\nMember Menu:");
        System.out.println("1. View Available Classes");
        System.out.println("2. View My Memberships");
        System.out.println("3. Purchase Membership");
        System.out.println("4. Logout");
        System.out.print("Choose option: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        
        try {
            switch (choice) {
                case 1:
                    viewAvailableClasses();
                    break;
                case 2:
                    viewMemberMemberships();
                    break;
                case 3:
                    purchaseMembership();
                    break;
                case 4:
                    currentUser = null;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void viewAvailableClasses() throws SQLException {
        List<WorkoutClass> classes = workoutClassService.getAllClasses();
        System.out.println("\nAvailable Classes:");
        for (WorkoutClass wc : classes) {
            System.out.println(wc);
            System.out.println("-------------------");
        }
    }
    
    private static void viewMemberMemberships() throws SQLException {
        List<Membership> memberships = membershipService.getUserMemberships(currentUser.getUserId());
        double total = membershipService.calculateUserExpenses(currentUser.getUserId());
        
        System.out.println("\nYour Memberships:");
        for (Membership m : memberships) {
            System.out.println(m);
            System.out.println("-------------------");
        }
        System.out.printf("Total Expenses: $%.2f\n", total);
    }
}