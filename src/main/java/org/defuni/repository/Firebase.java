package org.defuni.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import org.defuni.account.Manager;
import org.defuni.course.Course;
import org.defuni.course.ScheduledClass;

import java.util.HashMap;
import java.util.Map;

public class Firebase {
    public static void saveNewObject(String collection, String key, Map<String, Object> value){
        Manager manager = Manager.getInstance();

        Firestore firestore = manager.connect();
        CollectionReference scheduledClassCollection = firestore.collection(collection);
        DocumentReference docRef = scheduledClassCollection.document(key);
        ApiFuture<WriteResult> writeResult = docRef.set(value);
    }
    public static Map<String, Object> createExpectedDataMap(Course course) {
        Map<String, Object> data = new HashMap<>();
        data.put("courseID", course.getCourseID());
        data.put("courseTitle", course.getCourseTitle());
//        data.put("lecturerInCharge", course.getLecturer());
        data.put("credits", course.getCredits());
        data.put("componentGrades", course.getComponentGrades());
        data.put("courseContent", course.getCourseContent());
        data.put("courseMaterials", course.getCourseMaterials());
        data.put("state", course.getState());
        data.put("description", course.getDescription());
        data.put("department", course.getDepartment());
        data.put("studentRegisters", course.getStudentRegisters());
        // data.put("password", course.getPassword());
        // ... other fields based on your Student class
        return data;
    }
    public static Map<String, Object> createExpectedDataMap(ScheduledClass scheduledClass){
        Map<String, Object> data = new HashMap<>();
        data.put("classID", scheduledClass.getClassID());
        data.put("courseID", scheduledClass.getCourseID());
        data.put("courseTitle", scheduledClass.getCourseTitle());
        return data;
    }

}
