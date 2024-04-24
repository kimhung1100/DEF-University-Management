package org.defuni.account;

public class StudentAccountFactory extends UserAccountFactory {
    public Student createUser(UserAccountType type) {
        if (type == UserAccountType.STUDENT) {
            Student newStudent = new Student();
            newStudent.setFirstName("fname");
            newStudent.setLastName("lname");
            newStudent.setAddress("Address");
            newStudent.setEmail("abc@xyz");
            return newStudent;
        } else
            return null;
    }

}
