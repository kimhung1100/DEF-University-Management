package org.defuni.account;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.defuni.course.Course;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Manager {
    private static Manager instance;
    private static List<Course> courseList = new ArrayList<Course>();
    Firestore db;

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

    public Boolean updateDocument(String collection, String documentId, String field, String newValue) {
        DocumentReference docRef = db.collection(collection).document(documentId);
        // (async) Update one field
        ApiFuture<WriteResult> future = docRef.update(field, newValue);

        // WriteResult result = future.get();
        return true;
    }

    public Boolean updateDocument(String collection, String documentId, String field, List<String> newValue) {
        DocumentReference docRef = db.collection(collection).document(documentId);
        // (async) Update one field
        ApiFuture<WriteResult> future = docRef.update(field, newValue);

        // WriteResult result = future.get();
        return true;
    }

    public Map<String, Object> findDocument(String collection, String documentId) {
        // Reference to the document
        DocumentReference docRef = db.collection(collection).document(documentId);

        try {
            // Get the document snapshot
            DocumentSnapshot documentSnapshot = docRef.get().get();

            // Check if the document exists
            if (documentSnapshot.exists()) {
                // Convert the document snapshot to a Map and return
                return documentSnapshot.getData();
            } else {
                // Document does not exist
                System.out.println("Document does not exist.");
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            // Handle exceptions
            e.printStackTrace();
            return null;
        }
    }

    public UserAccount login(String username, String password) {
        // Dummy implementation, replace with actual authentication logic
        // Here you can implement your authentication logic, e.g., check against a
        // database
        db = connect();
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
            } else if (documentEducationManager.exists()
                    && documentEducationManager.getString("password").equals(password)) {
                return documentEducationManager.toObject(EducationManager.class);
            } else {
                return null;
            }

        } catch (Exception e) { // Catch specific expected exceptions
            // Handle exceptions gracefully, log the error and potentially communicate it
            // back to the user
            System.err.println("Error occurred during authentication: " + e.getMessage());
            return null; // Or take other appropriate actions
        }
    }

    public static Firestore connect() {
        Firestore db = null;
        String currentDir = System.getProperty("user.dir");
        // Using try-with-resources to automatically close the FileInputStream
        try (InputStream serviceAccount = new FileInputStream(
                currentDir + "/src/main/java/org/defuni/service_account.json")) {
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

    public static boolean addCourse(Course course) {
        courseList.add(course);
        return true;
    }

    public static Student convStudent(Map<String, Object> document) {
        Student stu = new Student();
        stu.setEmail((String) document.get("email"));
        stu.setUserName((String) document.get("userName"));
        stu.setPassword((String) document.get("password"));
        stu.setFirstName((String) document.get("firstName"));
        stu.setLastName((String) document.get("lastName"));
        stu.setAddress((String) document.get("address"));

        return stu;
    }

    public static Lecturer convLecturer(Map<String, Object> document) {
        Lecturer lec = new Lecturer();
        lec.setEmail((String) document.get("email"));
        lec.setUserName((String) document.get("userName"));
        lec.setPassword((String) document.get("password"));
        lec.setFirstName((String) document.get("firstName"));
        lec.setLastName((String) document.get("lastName"));
        lec.setAddress((String) document.get("address"));

        return lec;
    }

    public static Course convCourse(Map<String, Object> document) {
        Course co = new Course();

        co.setComponentGrades(null);

        return co;
    }
}
