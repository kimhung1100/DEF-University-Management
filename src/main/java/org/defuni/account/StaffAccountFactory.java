package org.defuni.account;

import org.defuni.account.Student;
import org.defuni.account.UserAccount;
import org.defuni.account.UserAccountFactory;

public class StaffAccountFactory extends UserAccountFactory {
    public UserAccount createUser(UserAccountType type) {
        if (type == UserAccountType.LECTURER) {
            Lecturer newLecturer = new Lecturer();
            newLecturer.setFirstName("fname");
            newLecturer.setLastName("lname");
            newLecturer.setAddress("Address");
            newLecturer.setEmail("abc@xyz");
            return newLecturer;
        } else if (type == UserAccountType.EDUCATIONALMANAGER) {
            return new EducationManager();
        } else
            return null;
    }
}
