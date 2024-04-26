package org.defuni.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;

import org.defuni.account.Manager;
import org.defuni.course.Course;
import org.defuni.course.ScheduledClass;
import org.defuni.account.Student;
import org.defuni.account.UserAccount;
import org.defuni.account.Lecturer;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Firebase {
    public static void saveNewObject(Firestore db, String collection, String newDoc, Map<String, Object> value) {
        CollectionReference scheduledClassCollection = db.collection(collection);
        DocumentReference docRef = scheduledClassCollection.document(newDoc);
        ApiFuture<WriteResult> writeResult = docRef.set(value);
    }

    public static Map<String, Object> createExpectedDataMap(Course course) {
        Map<String, Object> data = new HashMap<>();
        data.put("courseID", course.getCourseID());
        data.put("courseTitle", course.getCourseTitle());
        // data.put("lecturerInCharge", course.getLecturer());
        // data.put("credits", course.getCredits());
        data.put("componentGrades", course.getComponentGrades());
        data.put("courseContent", course.getCourseContent());
        data.put("courseMaterials", course.getCourseMaterials());
        data.put("state", course.getState());
        data.put("description", course.getDescription());
        data.put("department", course.getDepartment());
        data.put("studentRegisters", course.getStudentRegisters());
        // data.put("password", course.getPassword());
        // ... other fields based on your Student class
        return data;
    }

    public static Map<String, Object> createExpectedDataMap(ScheduledClass scheduledClass) {
        Map<String, Object> data = new HashMap<>();
        data.put("classID", scheduledClass.getClassID());
        data.put("courseID", scheduledClass.getCourseID());
        data.put("courseTitle", scheduledClass.getCourseTitle());
        return data;
    }

    public static Map<String, Object> createExpectedDataMap(Lecturer lec) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", lec.getUserName());
        data.put("password", lec.getPassword());
        data.put("firstName", lec.getFirstName());
        data.put("lastName", lec.getLastName());
        data.put("email", lec.getEmail());
        data.put("address", lec.getAddress());
        return data;
    }

    public static Map<String, Object> createExpectedDataMap(Student stu) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", stu.getUserName());
        data.put("password", stu.getPassword());
        data.put("firstName", stu.getFirstName());
        data.put("lastName", stu.getLastName());
        data.put("email", stu.getEmail());
        data.put("address", stu.getAddress());
        data.put("notifications", stu.getNotifications());
        data.put("grades", stu.getGrades());
        return data;
    }

    public static Boolean hasName(Firestore db, String collectionName, String usernameValue) {
        // Create a reference to the specified collection
        CollectionReference collectionRef = db.collection(collectionName);

        try {
            // Query for documents where "username" field equals the specified value
            Query query = collectionRef.whereEqualTo("username", usernameValue);
            ApiFuture<QuerySnapshot> future = query.get();
            QuerySnapshot querySnapshot = future.get();

            // Check if any matching document is found
            return !querySnapshot.isEmpty();
        } catch (Exception e) {
            // Handle any errors
            e.printStackTrace();
        }

        // Error occurred or no matching document found
        return false;
    }

    public static boolean isValidLogin(Firestore db, String collection, String username, String password)
            throws InterruptedException, ExecutionException {
        DocumentReference docRef = db.collection(collection).document(username);

        ApiFuture<DocumentSnapshot> future = docRef.get();

        DocumentSnapshot document = future.get();

        if (!document.exists()) {
            System.out.println("No username as such");
            return false;
        } else {
            String actualPassword = document.getString("password");

            if (!password.equals(actualPassword)) {
                System.out.println("Wrong password");
                return false;
            } else {
                return true;
            }
        }

    }
}