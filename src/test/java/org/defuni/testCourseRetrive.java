package org.defuni;

import org.defuni.course.Course;
import org.defuni.repository.CourseRepository;
import org.defuni.repository.CourseRepositoryFirebase;
import org.junit.Test;

public class testCourseRetrive {
    @Test
    public void testCourseRetrive() {
        CourseRepository courseRepository = new CourseRepositoryFirebase();
        Course course = courseRepository.findCourseById("CO1005");
        System.out.println(course.getCourseTitle());
    }
}
