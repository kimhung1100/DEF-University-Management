package org.defuni.cli;

import org.defuni.account.Lecturer;

public class LecturerPage {
    Lecturer lecturer;


    public LecturerPage(Lecturer lecturer){
        this.lecturer = lecturer;
        System.out.println("Lecture page:");
        System.out.println("1. Create course");
        lecturer.courseCreate();


    }

}
