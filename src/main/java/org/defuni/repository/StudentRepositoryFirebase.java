package org.defuni.repository;

import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import org.defuni.account.Manager;
import org.defuni.account.Student;


import java.util.Map;
import java.util.concurrent.ExecutionException;

public class StudentRepositoryFirebase implements StudentRepository{
    static String collectionName = "students";

    public Student findStudentById(String studentID) {
        Manager manager = Manager.getInstance();
        Firestore db = manager.retriveDB();
        DocumentReference docRef = db.collection(collectionName).document(studentID);

        try {
            // Get the document snapshot
            DocumentSnapshot documentSnapshot = docRef.get().get();

            // Check if the document exists
            if (documentSnapshot.exists()) {
                // Convert the document snapshot to a Map and return
                Student student = documentSnapshot.toObject(Student.class);
                return student;
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
    public void saveStudent(Student student) {
        Map<String, Object> expectedDataMap = Firebase.createExpectedDataMap(student);
        Manager manager = Manager.getInstance();
        Firestore db = manager.retriveDB();

        Firebase.saveNewObject(db, collectionName, Integer.toString(student.getID()), expectedDataMap);
    }

    @Override
    public Student getStudents() {
        return null;
    }

    @Override
    public void updateStudent() {

    }

    @Override
    public void deleteStudent() {

    }
}
