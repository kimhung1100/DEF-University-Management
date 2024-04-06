package org.defuni.account;

import com.google.auth.oauth2.GoogleCredentials;
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

    public boolean login(String username, String password) {
        // Dummy implementation, replace with actual authentication logic
        // Here you can implement your authentication logic, e.g., check against a database
        if ("admin".equals(username) && "password".equals(password)) {
            return true;
        } else {
            return false;
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
