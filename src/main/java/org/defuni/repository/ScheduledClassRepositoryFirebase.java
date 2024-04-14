package org.defuni.repository;

import org.defuni.course.ScheduledClass;

import java.util.List;

public class ScheduledClassRepositoryFirebase implements ScheduledClassRepository {


    @Override
    public void createScheduledClass(ScheduledClass scheduledClass) {

    }

    @Override
    public ScheduledClass getScheduledClass(int id) {
        return null;
    }

    @Override
    public List<ScheduledClass> getAllScheduledClasses() {
        return List.of();
    }

    @Override
    public void deleteScheduledClass(int id) {

    }
}
