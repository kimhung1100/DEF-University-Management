package org.defuni.course;

public interface Publisher {
    public void addObserver(StudentObserver observer);

    public void removeObserver(StudentObserver observer);

    public void notifyObservers(String message);
}
