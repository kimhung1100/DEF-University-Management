package org.defuni.account;

public class EducationManager extends UserAccount {
    public EducationManager() {
        super();
    }

    public void createCourse(){

    }

    public void createUser(UserAccountType student) {
        createUser(UserAccountType.STUDENT);
    }
}
