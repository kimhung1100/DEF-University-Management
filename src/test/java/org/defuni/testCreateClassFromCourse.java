package org.defuni;

import org.defuni.course.Course;
import org.defuni.course.ScheduledClass;
import org.defuni.repository.CourseRepository;
import org.defuni.repository.CourseRepositoryFirebase;
import org.junit.Test;

import java.util.List;

public class testCreateClassFromCourse {
    @Test
    public void testCreateClassFromCourse() {
        CourseRepository courseRepository = new CourseRepositoryFirebase();
        Course course = courseRepository.findCourseById("CO1005");
        System.out.println(course.getStudentRegisters());
        List<ScheduledClass> scheduledClasses = course.createScheduledClass(1);
        System.out.println(scheduledClasses);

    }

}
