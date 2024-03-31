package org.defuni;

import org.defuni.account.Student;
import org.defuni.course.ScheduledClass;

public class DEFUni {
    public static void main(String[] args) {

        System.out.println("DEF Uni");
        ScheduledClass scheduledClass = new ScheduledClass();

        Student student = new Student();
        student.addClass(scheduledClass);

        scheduledClass.setContent("Week 1: Do nothing");
        scheduledClass.setContent("Week 1: Do nothing\nWeek 2: Read textbook.");

    }
}