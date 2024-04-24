package org.defuni.cli;

import org.defuni.account.*;
import org.defuni.repository.Firebase;

import com.google.cloud.firestore.Firestore;

import javax.sound.midi.SysexMessage;
import java.util.Scanner;
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
            System.out.println("You want to LOGIN or you want to SIGNUP for a new account?");

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
        // scanner.close(); //Dont close the input stream
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            clearScreen();
            System.out.println("DEF University Management System");
            System.out.println("Log in using your account on:");
            System.out.println("1. Student");
            System.out.println("2. Lecturer");
            System.out.println("3. Education Manager");
            System.out.println("0. EXIT");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (choice == 0)
                break;

            clearScreen();

            System.out.println("LOGIN PAGE");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            switch (choice) {
                case 0:
                    break;

                case 1:
                    Student student = new Student();
                    UserAccount ua = student.login(username, password);
                    if (student != null) {
                        StudentPage studentPage = new StudentPage(ua);
                        studentPage.run();

                        break;
                    } else {
                        System.out.println("Wrong username or password");
                    }

                case 2:
                    Lecturer lecturer = new Lecturer();
                    // if (lecturer.login(username, password) != null) { //hình như chưa đồng bộ đc
                    // bên ggfb?
                    // LecturerPage lecturerPage = new LecturerPage(lecturer);
                    // lecturerPage.run();
                    // }

                    LecturerPage lecturerPage = new LecturerPage(lecturer);
                    lecturerPage.run();
                    break;
                case 3:
                    EducationManager manager = new EducationManager();
                    manager.login(username, password);

                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
            }
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

/*
 * 
 * TO DO LIST:
 * tạo CLI tạo tài khoản.
 * tạo các hàm rút từ DB về, conv sang object
 */