package org.defuni;

import org.defuni.account.*;
import org.defuni.cli.LoginPage;
import org.defuni.course.ScheduledClass;

import java.util.Scanner;


public class DEFUni {
    public static void dumpData(){
        UserAccountFactory staffAccountFactory = new StaffAccountFactory();

        // to create studentAccount
        UserAccountFactory studentAccountFactory = new StudentAccountFactory();

        UserAccount user = staffAccountFactory.createUser(UserAccountType.LECTURER);
        user.setUserName("admin");
        user.setPassword("password");
    }
    public static void main(String[] args) {
        dumpData();
        LoginPage loginPage = new LoginPage();


    }
}