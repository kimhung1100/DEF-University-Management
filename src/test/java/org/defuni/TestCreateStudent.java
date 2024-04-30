package org.defuni;
import org.defuni.account.Student;
import org.defuni.course.Course;
import org.defuni.course.ScheduledClass;
import org.defuni.repository.CourseRepositoryFirebase;
import org.defuni.repository.ScheduledClassRepositoryFirebase;
import org.defuni.repository.StudentRepositoryFirebase;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.List;

public class TestCreateStudent {
    @Test
    public void testCreateStudent() {
        Student student = new Student();
        student.setUserID(2011318);
        student.setUserName("2011318");
        student.setPassword("2011318");
        student.setAddress("218 Ly Thuong Kiet");
        student.setEmail("2103318@gmail.com");
        student.setFirstName("Bui");
        student.setLastName("Hung");
        StudentRepositoryFirebase studentRepositoryFirebase = new StudentRepositoryFirebase();
        studentRepositoryFirebase.saveStudent(student);
        Student studentRet = studentRepositoryFirebase.findStudentById("2011318");
        assert studentRet != null;

        Student student1 = new Student();
        student1.setUserID(2011319);
        student1.setUserName("2011319");
        student1.setPassword("2011319");
        student1.setAddress("218 Ly Thuong Kiet");
        student1.setEmail("2011319@gmail.com");
        student1.setFirstName("Nguyen Van");
        student1.setLastName("Ba");

        studentRepositoryFirebase.saveStudent(student1);
        Student studentRet1 = studentRepositoryFirebase.findStudentById("2011319");
        assert studentRet1 != null;

        System.out.println(studentRet.getUserName());
        System.out.println(studentRet1.getUserName());
        
    }
    
    @Test
    public void TestStudentRegisterCourse() {
        // Arrange
        StudentRepositoryFirebase studentRepositoryFirebase = new StudentRepositoryFirebase();
        Student student1 = studentRepositoryFirebase.findStudentById("2011318");
        Student student2 = studentRepositoryFirebase.findStudentById("2011319");

        CourseRepositoryFirebase courseRepositoryFirebase = new CourseRepositoryFirebase();
        Course course = courseRepositoryFirebase.findCourseById("CO1005");
        // Act

        course.registerCourse(student1.getUserName());
        course.registerCourse(student2.getUserName());

        // Assert
        courseRepositoryFirebase.saveCourse(course);
        Course courseRet = courseRepositoryFirebase.findCourseById("CO1005");
        assert courseRet.getStudentRegisters().size() == 2;

    }

    @Test
    public void testCreateScheduledClass() {
        CourseRepositoryFirebase courseRepositoryFirebase = new CourseRepositoryFirebase();
        Course course = courseRepositoryFirebase.findCourseById("CO1005");
        List<ScheduledClass> scheduledClassList = course.createScheduledClass(1);

        ScheduledClassRepositoryFirebase scheduledClassRepositoryFirebase = new ScheduledClassRepositoryFirebase();
        for (ScheduledClass scheduledClass : scheduledClassList) {
            scheduledClassRepositoryFirebase.createScheduledClass(scheduledClass);
        }

        courseRepositoryFirebase.saveCourse(course);

        for (ScheduledClass scheduledClass : scheduledClassList) {
            ScheduledClass scheduledClassRet = scheduledClassRepositoryFirebase.findScheduledClassByID(scheduledClass.getSemester() + scheduledClass.getCourseID() + scheduledClass.getClassID());
            assert scheduledClassRet != null;
        }



    }
}
