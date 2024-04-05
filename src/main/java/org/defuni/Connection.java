package org.defuni;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;

public class Connection {

    public Firestore connect() {
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

}
// Use a service account