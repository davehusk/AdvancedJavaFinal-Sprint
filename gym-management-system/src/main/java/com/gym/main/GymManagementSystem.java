package com.gym.main;

import com.gym.model.User;
import com.gym.service.UserService;
import com.gym.service.MembershipService;
import com.gym.service.WorkoutClassService;
import java.util.Scanner;

public class GymManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static User currentUser;
    private static final UserService userService = new UserService(new UserDAOImpl());
    private static final MembershipService membershipService = new MembershipService(new MembershipDAOImpl());
    private static final WorkoutClassService workoutClassService = new WorkoutClassService(new WorkoutClassDAOImpl());

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Gym Management System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void registerUser() {
        // Implementation placeholder
    }

    private static void loginUser() {
        // Implementation placeholder
    }

    private static void showAdminMenu() {
        // Implementation placeholder
    }

    private static void showTrainerMenu() {
        // Implementation placeholder
    }

    private static void showMemberMenu() {
        // Implementation placeholder
    }
}