package org.defuni.course;

public interface Subject {
    public void registerObserver(StudentObserver o);
    public void removeObserver(StudentObserver o);
    public void notifyObserver();
}

