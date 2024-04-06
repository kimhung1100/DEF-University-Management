package org.defuni.account;

import java.util.Scanner;

import java.time.LocalDate;

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

    private void updateProfile() {
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

        System.out.println(String.format("Date of birth: %s", getDob()));
        input = scanner.nextLine();
        if (input == "") {
        } else
            setFirstName(input);

        scanner.close();
    }
}
