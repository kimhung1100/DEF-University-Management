package org.defuni.account;

import org.defuni.account.Student;
import org.defuni.account.UserAccount;
import org.defuni.account.UserAccountFactory;

public class StaffAccountFactory extends UserAccountFactory {
    public UserAccount createUser(UserAccountType type){
        if (type == UserAccountType.LECTURER) {
            return new Lecturer();
        } else if (type == UserAccountType.EDUCATIONALMANAGER){
            return new EducationManager();
        } else return null;
    }
}
