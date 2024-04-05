package org.defuni.account;

import java.time.LocalDate;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public abstract class UserAccount {
    @Id
    private int userID;
    private String userName;
    private String email;

    private String firstName;
    private String lastName;
    private String password;

    private String address;
    private LocalDate dob;

    public UserAccount(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public UserAccount() {

    }


    public boolean login(String username, String password) {
        Manager loginManager = Manager.getInstance();
        return loginManager.login(username, password);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public int getUserID() {
        return userID;
    }

    private void updateProfile(){

    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }



}
