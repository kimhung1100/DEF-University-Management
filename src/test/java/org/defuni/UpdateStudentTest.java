package org.defuni;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.defuni.account.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class UpdateStudentTest {
    private Firestore db = getFirestoreInstance(); // Implement this method

    private Firestore getFirestoreInstance() {
        // ... Your implementation to connect to a Firestore emulator or a test project
        Manager manager = Manager.getInstance();
        return manager.connect();
    }
    @Test
public void testUpdateStudent() throws ExecutionException, InterruptedException {
        DocumentReference docRef = db.collection("students").document("testUser");
        // (async) Update one field
        ApiFuture<WriteResult> future = docRef.update("password", "newPassword");

        WriteResult result = future.get();
        Assert.assertTrue(result.getUpdateTime() != null);
    }


}
