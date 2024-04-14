package org.defuni.repository;

import org.defuni.course.ScheduledClass;

import java.util.List;

public interface ScheduledClassRepository {
    public void createScheduledClass(ScheduledClass scheduledClass);
    public ScheduledClass getScheduledClass(int id);
    public List<ScheduledClass> getAllScheduledClasses();
    public void deleteScheduledClass(int id);

}
