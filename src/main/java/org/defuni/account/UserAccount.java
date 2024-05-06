package org.defuni.account;

import java.util.Scanner;

import com.google.cloud.firestore.Firestore;

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
    private UserAccountType accType;

    public UserAccount login(String username, String password) {
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
        } else {
            setAddress(input);
        }

        // CẦN XÁC ĐỊNH XEM ĐÂY LÀ STUDENT HAY LEC ĐỂ CHỌN COLLECTION
        UserAccountType type = this.accType;
        String collection = (this.accType.equals(UserAccountType.STUDENT)) ? "students" : "lecturers";

        // Save info to DB
        Manager manager = Manager.getInstance();
        Firestore db = Manager.getDB();

        String name = this.userName;

        manager.updateDocument(db, collection, this.userName, "firstName", getFirstName());
        manager.updateDocument(db, collection, this.userName, "lastName", getLastName());
        manager.updateDocument(db, collection, this.userName, "email", getEmail());
        manager.updateDocument(db, collection, this.userName, "address", getAddress());

        clearScreen();
    }

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
    public void displaySchedule() {
        clearScreen();
//        System.out.println(String.format("Name: %s %s", getLastName(), getFirstName()));
//        System.out.println(String.format("Email: %s", getEmail()));
//        System.out.println(String.format("Address: %s", getAddress()));

        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // String dob = getDob().format(formatter);
        // System.out.println(String.format("Date of birth: " + dob));

        System.out.print("*Press Enter to exit");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        clearScreen();

        // Scanner must NOT close, process in LecturerPage needs it to run
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

    public void setAccType(UserAccountType type) {
        this.accType = type;
    }

    public UserAccountType getAccType() {
        return accType;
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}