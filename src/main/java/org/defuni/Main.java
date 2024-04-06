package org.defuni;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.defuni.account.Manager;
import org.defuni.account.Student;
import com.google.api.core.ApiFutures;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Get an instance of Firestore using the Connection class
        Firestore firestore = Manager.connect();

        // Create a new student
        Student student = new Student("test_student", "test_email","password");
        DocumentReference docRef = firestore.collection("students").document(student.getUserName());
        // Add document data with id "" using a hashmap
        Map<String, Object> data = new HashMap<>();
        data.put("userName", student.getUserName());
        data.put("email", student.getEmail());
        data.put("password", student.getPassword());
        data.put("firstName", student.getFirstName());
        data.put("lastName", student.getLastName());
        data.put("address", student.getAddress());


        // asynchronously write data
        ApiFuture<WriteResult> result = docRef.set(data);

        System.out.println("Update time : " + result.get().getUpdateTime());
    }
}
