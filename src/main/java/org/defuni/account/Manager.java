package org.defuni.account;

import org.defuni.course.Course;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private static Manager instance;
    private static List<Course> courseList = new ArrayList<Course>();

    private Manager() {
        // Prevent instantiation via reflection
        if (instance != null) {
            throw new IllegalStateException("Cannot instantiate singleton class using reflection");
        }
    }
    public static synchronized Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    public boolean login(String username, String password) {
        // Dummy implementation, replace with actual authentication logic
        // Here you can implement your authentication logic, e.g., check against a database
        if ("admin".equals(username) && "password".equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean addCourse(Course course){
        courseList.add(course);
        return true;
    }




}
