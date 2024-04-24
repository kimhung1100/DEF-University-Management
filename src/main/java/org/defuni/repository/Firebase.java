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
        data.put("credits", course.getCredits());
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

    public static Map<String, Object> createExpectedDataMap(UserAccount user) {
        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUserName());
        data.put("password", user.getPassword());
        data.put("firstName", user.getFirstName());
        data.put("lastName", user.getLastName());
        data.put("email", user.getEmail());
        data.put("address", user.getAddress());
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

}
