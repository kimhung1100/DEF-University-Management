package org.defuni.account;

import org.defuni.account.UserAccount;
import org.defuni.course.LecturerObserver;

import java.util.List;

public class Lecturer extends UserAccount implements LecturerObserver {
    private String department;
    private List<String> certificates;
    public void updateClass(){

    }

    public void updateGrade(){

    }
}
