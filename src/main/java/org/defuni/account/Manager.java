package org.defuni.account;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.defuni.course.Course;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Manager {
    private static Manager instance;
    private static List<Course> courseList = new ArrayList<Course>();

    private Manager() {
        // Prevent instantiation via reflection
        if (instance != null) {
            throw new IllegalStateException("Cannot instantiate singleton class using reflection");
        }
    }
    public static synchronized Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    public UserAccount login(String username, String password) {
        // Dummy implementation, replace with actual authentication logic
        // Here you can implement your authentication logic, e.g., check against a database
        Firestore db = connect();
        DocumentReference docRef = db.collection("students").document(username);
        DocumentReference docRef2 = db.collection("lecturers").document(username);
        DocumentReference docRef3 = db.collection("educationManagers").document(username);
        ApiFuture<DocumentSnapshot> futureStudent = docRef.get();
        ApiFuture<DocumentSnapshot> futureLecturer = docRef2.get();
        ApiFuture<DocumentSnapshot> futureEducationManager = docRef3.get();
        try {
            // Retrieve document snapshots for all three collections
            DocumentSnapshot documentStudent = futureStudent.get();
            DocumentSnapshot documentLecturer = futureLecturer.get();
            DocumentSnapshot documentEducationManager = futureEducationManager.get();

            // Check for password match in any of the documents
            if (documentStudent.exists() && documentStudent.getData().get("password").equals(password)) {
                return documentStudent.toObject(Student.class);
            } else if (documentLecturer.exists() && documentLecturer.getString("password").equals(password)) {
                return documentLecturer.toObject(Lecturer.class);
            } else if (documentEducationManager.exists() && documentEducationManager.getString("password").equals(password)) {
                return documentEducationManager.toObject(EducationManager.class);
            } else {
                return null;
            }

        } catch (Exception e) { // Catch specific expected exceptions
            // Handle exceptions gracefully, log the error and potentially communicate it back to the user
            System.err.println("Error occurred during authentication: " + e.getMessage());
            return null; // Or take other appropriate actions
        }


    }

    public static Firestore connect() {
        Firestore db = null;
        try {
            String currentDir = System.getProperty("user.dir");
            InputStream serviceAccount = new FileInputStream(currentDir+"/src/main/java/org/defuni/service_account.json");
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(credentials)
                    .build();
            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return db;
    }

    public static boolean addCourse(Course course){
        courseList.add(course);
        return true;
    }




}
