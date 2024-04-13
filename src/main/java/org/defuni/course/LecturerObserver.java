package org.defuni.course;

public interface LecturerObserver {
    public void updateClass(); 
    public void updateGrade();
    public void updateContent(ScheduledClass scheduledClass, String Content);
}

