package org.defuni;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

import org.defuni.account.StaffAccountFactory;
import org.defuni.account.Student;
import org.defuni.account.StudentAccountFactory;
import org.defuni.account.UserAccountFactory;

import com.google.api.core.ApiFutures;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.defuni.account.*;
import org.defuni.cli.*;

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
        // Get an instance of Firestore using the Connection class
        Connection connection = new Connection();
        Firestore firestore = connection.connect();

        // Create a new student
        Student student = new Student("test_student", "test_email", "password");
        DocumentReference docRef = firestore.collection("students").document(student.getUserName());
        // Add document data with id "" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("userName", student.getUserName());
        data.put("email", student.getEmail());
        data.put("password", student.getPassword());

        // asynchronously write data2
        ApiFuture<WriteResult> result = docRef.set(data);

        System.out.println("Update time : " + result.get().getUpdateTime());

        // Khai's test region. you can comment the codes if not need
        test_init();
        LoginPage loginPage = new LoginPage();
    }
}