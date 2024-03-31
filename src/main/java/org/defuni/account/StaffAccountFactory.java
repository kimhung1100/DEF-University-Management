package org.defuni.account;

import org.defuni.account.Student;
import org.defuni.account.UserAccount;
import org.defuni.account.UserAccountFactory;

public class StaffAccountFactory extends UserAccountFactory {
    UserAccount createUser(){
        return new Student();
    }
}
