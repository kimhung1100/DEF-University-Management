package org.defuni.repository;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;

import com.google.cloud.firestore.Firestore;
import org.defuni.account.Manager;
import org.defuni.course.Course;
import org.defuni.course.ScheduledClass;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ScheduledClassRepositoryFirebase implements ScheduledClassRepository {
    public static String collectionName = "scheduledClass";



    @Override
    public void createScheduledClass(ScheduledClass scheduledClass) {
        Map<String, Object> expectedDataMap = Firebase.createExpectedDataMap(scheduledClass);
        String scheduledClassId = scheduledClass.getSemester() + scheduledClass.getCourseID() + scheduledClass.getClassID();

        Manager manager = Manager.getInstance();
        Firestore db = manager.retriveDB();
        Firebase.saveNewObject(db, collectionName, scheduledClassId, expectedDataMap);
    }

    @Override
    public ScheduledClass findScheduledClassByID(String scheduledClassID) {

        Manager manager = Manager.getInstance();
        Firestore db = manager.retriveDB();
        DocumentReference docRef = db.collection(collectionName).document(scheduledClassID);

        try {
            // Get the document snapshot
            DocumentSnapshot documentSnapshot = docRef.get().get();

            // Check if the document exists
            if (documentSnapshot.exists()) {
                // Convert the document snapshot to a Map and return
                ScheduledClass scheduledClass = documentSnapshot.toObject(ScheduledClass.class);
                return scheduledClass;
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

    @Override
    public List<ScheduledClass> getAllScheduledClasses() {
        return List.of();
    }

    @Override
    public void deleteScheduledClass(int id) {

    }
}
