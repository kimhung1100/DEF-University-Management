package org.defuni.cli;

import org.defuni.account.*;
import org.defuni.repository.Firebase;

import com.google.cloud.firestore.Firestore;

import javax.sound.midi.SysexMessage;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.InputMismatchException;

import java.util.Map;

public class LoginPage {
    public LoginPage() {

    }

    public void gate() { // Ask user want to login or sign in?
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearScreen();

            System.out.println("DEF University account gateway");
            System.out.println("You want to LOGIN or you want to SIGNUP for a new account? Or EXIT to quit?");

            String input = scanner.nextLine().toLowerCase();

            if (input.equals("login")) {
                login();
            }

            else if (input.equals("signup")) {
                signup();
            }

            else if (input.equals("exit")) {
                break;
            }

            else {
                System.out.println("The input should be LOGIN or SIGNUP");
                sleep(1000);
                continue;
            }
        }
        scanner.close();
    }

    public void signup() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        Firestore db = Manager.getDB();

        while (true) {
            clearScreen();
            System.out.println("You sign up as:");
            System.out.println("1. Student");
            System.out.println("2. Lecturer");

            int choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the invalid input
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
                continue;
            }

            if (choice == 1) { // Student sign up
                String username, password;

                while (true) {
                    System.out.println("Enter your user name");
                    username = scanner.nextLine();

                    System.out.println("Enter your password");
                    password = scanner.nextLine();

                    // Check if account's already created
                    if (Firebase.hasName(db, "students", username)) {
                        clearScreen();
                        System.out.println(String.format("Username %s has been used.", username));
                        continue;
                    }

                    System.out.println("Confirm info:       enter 'N' to change info, other keys to create account. ");
                    System.out.printf("Username: %s \n", username);
                    System.out.printf("Username: %s \n", password);

                    String input = scanner.nextLine();
                    if (lower(input).equals("n")) {
                        continue;
                    }
                    break;
                }

                StudentAccountFactory stuFac = new StudentAccountFactory();
                Student newStudent = stuFac.createUser(UserAccountType.STUDENT);

                newStudent.setUserName(username);
                newStudent.setPassword(password);

                Map<String, Object> data = Firebase.createExpectedDataMap(newStudent);
                Firebase.saveNewObject(db, "students", username, data);

                System.out.println("Created student");
                System.out.println("You can now login to your new account");
                sleep(3000);
                break;
            }

            else if (choice == 2) { // Lecturer sign up
                String username, password;

                while (true) {
                    System.out.println("Enter your user name");
                    username = scanner.nextLine();

                    System.out.println("Enter your password");
                    password = scanner.nextLine();

                    // Check if account's already created
                    if (Firebase.hasName(db, "lecturers", username)) {
                        clearScreen();
                        System.out.println(String.format("Username %s has been used.", username));
                        continue;
                    }

                    System.out.println("Confirm info:       enter 'N' to change info, other keys to create account. ");
                    System.out.printf("Username: %s \n", username);
                    System.out.printf("Password: %s \n", password);

                    String input = scanner.nextLine();
                    if (lower(input).equals("n")) {
                        continue;
                    }
                    break;
                }

                StaffAccountFactory lecFac = new StaffAccountFactory();
                Lecturer newLecturer = lecFac.createLecturer();

                newLecturer.setUserName(username);
                newLecturer.setPassword(password);

                Map<String, Object> data = Firebase.createExpectedDataMap(newLecturer);
                Firebase.saveNewObject(db, "lecturers", username, data);

                System.out.println("Created lecturer");
                System.out.println("You can now login to your new account");
                sleep(3000);
                break;
            }

            else {
                System.out.println("The input should be 1 or 2");
                sleep(1000);
                continue;
            }
        }
        // scanner.close(); //Don't close the input stream
    }

    public void login() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        Firestore db = Manager.getDB();

        while (true) {
            clearScreen();
            System.out.println("DEF University Management System");
            System.out.println("Log in using your account on:");
            System.out.println("1. Student");
            System.out.println("2. Lecturer");
            System.out.println("3. Education Manager");
            System.out.println("0. EXIT");

            String choice = scanner.nextLine();

            if (choice.equals("0"))
                return;
            else if (choice.equals("1")||choice.equals("2")||choice.equals("3")) {
                clearScreen();
                System.out.println("LOGIN PAGE");
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();
                if (choice.equals("1")) {

                    try {
                        boolean loginSuccess = Firebase.isValidLogin(db, "students", username, password);
                        if (loginSuccess) {
                            // Momoi là gì? Momoi là tên của 1 đứa hs. vậy thôi
//                            System.out.println("Grabbed Momoi!");

                            // Rút & convert student ToT
                            Map<String, Object> stu = Manager.findDocument(db, "students", username);
                            Student Momoi = Manager.convStudent(stu);
                            StudentPage studentPage = new StudentPage(Momoi);
                            studentPage.run();
                        } else {
                            System.out.println("Wrong username or password");
                            continue;
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        System.out.println("Error");
                        continue;
                    }
                    break;
                } else if (choice.equals("2")) {
                    try {
                        boolean loginSuccess = Firebase.isValidLogin(db, "lecturers", username, password);
                        if (loginSuccess) {
//                            System.out.println("Grabbed Sensei!");

                            // Rút & convert sensei <(")
                            Map<String, Object> data = Manager.findDocument(db, "lecturers", username);
                            Lecturer lec = Manager.convLecturer(data);
                            LecturerPage lecturerPage = new LecturerPage(lec);
                            lecturerPage.run();
                        } else {
                            System.out.println("Wrong username or password");
                            continue;
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        System.out.println("Error");
                        continue;
                    }
                    break;
                } else if (choice.equals("3")) {
                    try {
                        boolean loginSuccess = Firebase.isValidLogin(db, "educationManagers", username, password);
                        if (loginSuccess) {
//                            System.out.println("Grabbed Manager!");

                            // Rút & convert manager <(")
                            Map<String, Object> data = Manager.findDocument(db, "educationManagers", username);
                            EducationManager Emanager = Manager.convManager(data);
                            ManagerPage managerPage = new ManagerPage(Emanager);
                            managerPage.run();

                        } else {
                            System.out.println("Wrong username or password");
                            continue;
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                        System.out.println("Error");
                        continue;
                    }
                    break;
                }
            }
            else {
                System.out.println("Invalid choice, choose again!");
                sleep(1500);
                continue;

            }
            clearScreen();
        }

    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public String lower(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isUpperCase(c)) {
                result.append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public void sleep(int milisec) {
        try { // Sleep 1000ms before clear screen
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
