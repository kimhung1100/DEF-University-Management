package org.defuni.course;

import java.util.ArrayList;
import java.util.List;

public interface Subject {
    private List<StudentObserver> observers;

    public Course() {
        observers = new ArrayList<>();
    }
    public void registerObserver(StudentObserver o);
        observers.add(o);
    public void removeObserver(StudentObserver o);
        observers.remove(o);
    public void notifyObserver();
        for (StudentObserver observer : observers) {
            observer.update();
        }
}

