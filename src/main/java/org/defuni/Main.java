package org.defuni;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.api.core.ApiFutures;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.defuni.account.*;
import org.defuni.cli.*;
import org.defuni.course.ScheduledClass;

public class Main {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void test_init() {
        UserAccountFactory staffAccountFactory = new StaffAccountFactory();

        // to create studentAccount
        UserAccountFactory studentAccountFactory = new StudentAccountFactory();

        UserAccount user = staffAccountFactory.createUser(UserAccountType.LECTURER);
        user.setUserName("a");
        user.setPassword("a");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Lecturer lecturer = new Lecturer();
        ScheduledClass scClass = new ScheduledClass("CO1004");
        Student student = new Student();
        scClass.registerObserver(lecturer);
        scClass.registerObserver(student);
        lecturer.updateContent(scClass, "new content");

    }
}