package org.defuni.repository;

import org.defuni.course.Course;

public interface CourseRepository {
    public Course findCourseById(String courseID);
    public void saveCourse(Course course);

    public Course getCourse();

    public void updateCourse();

    public void deleteBook();
}
