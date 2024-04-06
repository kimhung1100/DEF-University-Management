package org.defuni.account;

import java.util.Scanner;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class UserAccount {
    private int userID;
    private String userName;
    private String email;

    private String firstName;
    private String lastName;
    private String password;

    private String address;
    private LocalDate dob;

    public boolean login(String username, String password) {
        Manager loginManager = Manager.getInstance();
        return loginManager.login(username, password);
    }

    public UserAccount(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public UserAccount() {

    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public String getAddress() {
        return address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getLastName() {
        return lastName;
    }

    public int getUserID() {
        return userID;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Will need to connect to DB
    public void updateProfile() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        System.out.println(String.format("Updating info. If you dont want to change any field, just Enter"));
        System.out.println(String.format("Your ID: %s", getUserID()));

        System.out.println(String.format("Last name: %s", getLastName()));
        String input = scanner.nextLine();
        if (input == "") {
        } else
            setLastName(input);

        System.out.println(String.format("First name: %s", getFirstName()));
        input = scanner.nextLine();
        if (input == "") {
        } else
            setFirstName(input);

        System.out.println(String.format("Email: %s", getEmail()));
        input = scanner.nextLine();
        if (input == "") {
        } else
            setEmail(input);

        System.out.println(String.format("Address: %s", getAddress()));
        input = scanner.nextLine();
        if (input == "") {
        } else
            setAddress(input);

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // System.out.println(String.format("Date of birth dd-mm-yyyy: %s",
        // getDob().format(formatter))));
        // input = scanner.nextLine();
        // if (input == "") {
        // } else {
        // setDob(LocalDate.parse(input, formatter));
        // }

        // scanner.close(); //THIS MUST NOT CLOSE
        clearScreen();
    }

    // Will need to connect to DB
    public void displayInfo() {
        clearScreen();
        System.out.println(String.format("Name: %s %s", getLastName(), getFirstName()));
        System.out.println(String.format("Email: %s", getEmail()));
        System.out.println(String.format("Address: %s", getAddress()));

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // String dob = getDob().format(formatter);
        // System.out.println(String.format("Date of birth: " + dob));

        System.out.print("*Press Enter to exit");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        clearScreen();

        // Scanner must NOT close, process in LecturerPage needs it to run
    }

}