package org.defuni;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.defuni.account.*;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Utils {

    // Assuming a way to get a Firestore instance (replace with your implementation)
    private Firestore firestore = getFirestoreInstance(); // Implement this method



    // Replace this method with your implementation to get a Firestore instance
    private Firestore getFirestoreInstance() {
        // ... Your implementation to connect to a Firestore emulator or a test project
        Manager manager = Manager.getInstance();
        return manager.connect();
    }
}
