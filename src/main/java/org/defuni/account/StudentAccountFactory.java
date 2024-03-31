package org.defuni.account;

public class StudentAccountFactory extends UserAccountFactory{
    public Student createUser(UserAccountType type) {
        if (type == UserAccountType.STUDENT){
            return new Student();
        } else return null;
    }

}
