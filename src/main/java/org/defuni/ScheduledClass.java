package org.defuni;

import java.util.ArrayList;
import java.util.List;

public class ScheduledClass implements Subject{
    private List<StudentObserver> studentObservers;

    public String classID;
//    public Course course;
    private String room;
    private String hall;
    private String time;
    private String content;
    public ScheduledClass() {
        studentObservers = new ArrayList<StudentObserver>();
        classID = "";
        room = "";
        hall = "";
        time = "";
        content = "";
    }

    public void registerObserver(StudentObserver student){
        studentObservers.add(student);
    }
    public void removeObserver(StudentObserver student){
        studentObservers.remove(student);
    }

    public void notifyObserver() {
        for (StudentObserver student : studentObservers){
            student.update(this);
        }
    }

    public void contentChanged() {
        notifyObserver();
    }
    public void setContent(String courseContent){
        this.content = courseContent;
        contentChanged();
    }
    public String getContent(){
        return this.content;
    }
}
