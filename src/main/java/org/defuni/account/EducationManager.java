package org.defuni.account;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

public class EducationManager extends UserAccount {
    public EducationManager() {
        super();
    }

    public void createCourse(){

    }

    public UserAccount createUser(UserAccountType student) {
        return new Student();
    }
    public void deleteStudent(String StudentID) {
        Manager manager = Manager.getInstance();
        Firestore db = manager.connect();
        CollectionReference studentsCollection = db.collection("students");
        DocumentReference docRef = studentsCollection.document(StudentID);

        ApiFuture<WriteResult> deleteFuture = docRef.delete();

        try {
            WriteResult result = deleteFuture.get();
            System.out.println("Student with ID " + StudentID + " deleted at " + result.getUpdateTime());
        } catch (Exception e) {
            System.err.println("Error deleting student with ID " + StudentID + ": " + e.getMessage());
        }
    }

}
