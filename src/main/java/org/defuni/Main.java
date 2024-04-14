package org.defuni;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.api.core.ApiFutures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.defuni.account.*;
import org.defuni.cli.*;
import org.defuni.course.Course;
import org.defuni.course.ScheduledClass;
import org.defuni.repository.CourseRepository;
import org.defuni.repository.CourseRepositoryFirebase;
import org.defuni.repository.ScheduledClassRepository;

import javax.sound.midi.SysexMessage;

public class Main {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void test_init() {
        UserAccountFactory staffAccountFactory = new StaffAccountFactory();

        // to create studentAccount
        UserAccountFactory studentAccountFactory = new StudentAccountFactory();

        UserAccount user = staffAccountFactory.createUser(UserAccountType.LECTURER);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CourseRepositoryFirebase courseRepositoryFirebase = new CourseRepositoryFirebase();
        Course course = courseRepositoryFirebase.findCourseById("CO1005");
        System.out.print(course.toString());

        Student student = new Student();
        student.setUserName("registerCourse");

        Student student1 = new Student();
        student.setUserName("registerStudent1");

        course.registerCourse(student);
        course.registerCourse(student1);

        System.out.print(course.getStudentRegisters());
        List<ScheduledClass> classes = course.createScheduledClass(1);
        System.out.println(classes.toString());

    }
}