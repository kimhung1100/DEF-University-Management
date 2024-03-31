package org.defuni.account;

import java.time.LocalDate;

public abstract class UserAccount {
    private int userID;
    private String email;

    private String firstName;
    private String lastName;
    private String password;

    private String address;
    private LocalDate dob;

    private void login(){

    }

    private void updateProfile(){

    }

    public String getFirstName() {
        return this.firstName;
    }





}
