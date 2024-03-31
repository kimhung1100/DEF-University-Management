package org.defuni;

import java.util.ArrayList;
import java.util.List;

public class Student implements StudentObserver{
    List<ScheduledClass> courseClass;

    public Student(ScheduledClass sclass){
        courseClass = new ArrayList<ScheduledClass>();
        courseClass.add(sclass);
        sclass.registerObserver(this);
    }
    public void displayGrade() {

    }
    public void update(ScheduledClass scheduledClass) {
        System.out.println("Current class " + scheduledClass.classID +" updated: \n" + scheduledClass.getContent());
    }
}
