package org.defuni.repository;

import org.defuni.course.ScheduledClass;

import java.util.List;

public interface ScheduledClassRepository {
    public void createScheduledClass(ScheduledClass scheduledClass);
    public void saveScheduledClass(ScheduledClass scheduledClass);
    public ScheduledClass findScheduledClassByID(String id);
    public List<ScheduledClass> getAllScheduledClasses();
    public void deleteScheduledClass(int id);

}
