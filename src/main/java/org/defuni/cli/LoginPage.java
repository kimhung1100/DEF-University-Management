package org.defuni.cli;

import org.defuni.account.EducationManager;
import org.defuni.account.Lecturer;
import org.defuni.account.Student;
import org.defuni.account.UserAccount;

import javax.sound.midi.SysexMessage;
import java.util.Scanner;
import java.util.InputMismatchException;

public class LoginPage {
    public LoginPage() {

    }

    public void gate() { // Ask user want to login or sign in?
        while (true) {
            Scanner scanner = new Scanner(System.in);
            clearScreen();

            System.out.println("DEF University account gateway");
            System.out.println("You want to LOGIN or you want to SIGNUP for a new account?");

            String input = scanner.nextLine();
            input = lower(input);

            if (input == "login") {
                login();
            }

            else if (input == "signup") {
                signup();
            }

            else {
                System.out.println("The input should be LOGIN or SIGNUP");
                sleep(1000);
                continue;
            }

            scanner.close();
        }

    }

    public void signup() {

        while (true) {
            System.out.println("You sign up as:");
            System.out.println("1. Student");
            System.out.println("2. Lecturer");

            Scanner scanner = new Scanner(System.in);
            int choice = 0;
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
            } finally {
                scanner.close();
            }

            if (choice == 1) {

            }

            else if (choice == 2) {

            }

            else {
                System.out.println("The input should be 1 or 2");
                sleep(1000);
                continue;
            }
        }
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