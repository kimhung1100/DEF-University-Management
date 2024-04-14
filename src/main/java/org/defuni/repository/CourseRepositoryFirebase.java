package org.defuni.repository;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.defuni.account.Manager;
import org.defuni.course.Course;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class CourseRepositoryFirebase implements CourseRepository{
    static String collectionName = "course";

    @Override
    public Course findCourseById(String courseID) {
        String courseCollection = "course";
        Manager manager = Manager.getInstance();
        Firestore db = manager.getDb();
        DocumentReference docRef = db.collection(courseCollection).document(courseID);

        try {
            // Get the document snapshot
            DocumentSnapshot documentSnapshot = docRef.get().get();

            // Check if the document exists
            if (documentSnapshot.exists()) {
                // Convert the document snapshot to a Map and return
                Course course = documentSnapshot.toObject(Course.class);
                return course;
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
    public void saveCourse(Course course) {
        Map<String, Object> expectedDataMap = Firebase.createExpectedDataMap(course);

        Firebase.saveNewObject(collectionName, course.getCourseID(), expectedDataMap);
    }

    @Override
    public Course getCourse() {
        return null;
    }

    @Override
    public void updateCourse() {

    }

    @Override
    public void deleteBook() {

    }
}
