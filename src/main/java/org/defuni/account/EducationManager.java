package org.defuni.account;

import java.util.Map;

import org.defuni.course.Course;
import org.defuni.course.ScheduledClass;
import org.defuni.repository.Firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;

public class EducationManager extends UserAccount {
    public EducationManager() {
        super();
    }

    /*
     * a ơi a ơi, a cứu e thêm cái tạo class của Education Manager được k a?
     * Kiểu khi hết đợt ĐKMH, mình sẽ rút HS đăng ký từ Course rồi mình tạo class.
     * Class là L01, L02 á anh. Trong Class mình cần 2 cái. 1 là Observer để gửi
     * noti cho HS, 2 là 1 cái String lịch học rồi gán cho tất cả học sinh trong
     * class đó. Vd "CO2001 /t L01 /t Monday /t 9h-11h". E làm 1 phần bên
     * EducationManager.java rồi ạ
     */

    public void createCourse(String courseID) {
        Manager manager = Manager.getInstance();
        Firestore db = manager.connect();
        CollectionReference coursesCollection = db.collection("courses");

        Map<String, Object> data = Manager.findDocument(db, "course", courseID);

        // Create a new course and set its properties
        // Map<String, Object> course = new HashMap<>(); create expected map for Course
        Course cou = Manager.convCourse(data);
        List<String> stuReg = cou.getStudentRegisters();

        int student_per_class = 3;
        for (int i = 0; i < stuReg.size(); i += student_per_class) {
            ScheduledClass scheduledClass = new ScheduledClass();
            scheduledClass.setCourseID(cou.getCourseID());
            scheduledClass.setCourseTitle(cou.getCourseTitle());
            scheduledClass.setStudentList(stuReg.subList(i, i + student_per_class));
            scheduledClass.setSchoolYear("2022");
            scheduledClass.setTime("10:00-12:00");
            scheduledClass.setRoom("A101");
            scheduledClass.setContent("This is a test class");

            // Asynchronously write the new class to Firestore
            ApiFuture<WriteResult> result = docRef.set(scheduledClass);

            try {
                System.out.println("Class created at " + result.get().getUpdateTime());
            } catch (Exception e) {
                System.err.println("Error creating class: " + e.getMessage());
            }
        }

        // Asynchronously write the new course to Firestore
        ApiFuture<WriteResult> result = docRef.set(course);

        try {
            System.out.println("Course created at " + result.get().getUpdateTime());
        } catch (Exception e) {
            System.err.println("Error creating course: " + e.getMessage());
        }
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
