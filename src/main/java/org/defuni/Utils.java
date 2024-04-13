package org.defuni;

import com.google.cloud.firestore.Firestore;
import org.defuni.account.*;

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
