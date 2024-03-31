package org.defuni;

public class DEFUni {
    public static void main(String[] args) {

        System.out.println("DEF Uni");
        ScheduledClass scheduledClass = new ScheduledClass();

        Student student = new Student(scheduledClass);

        scheduledClass.setContent("Week 1: Do nothing");
        scheduledClass.setContent("Week 1: Do nothing\n Week 2: Read textbook.");

    }
}