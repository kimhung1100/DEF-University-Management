package org.defuni.repository;

import org.defuni.account.Student;


public interface StudentRepository {
    public Student findStudentById(String studentID);
    public void saveStudent(Student student);

    public Student getStudents();

    public void updateStudent();

    public void deleteStudent();
}
