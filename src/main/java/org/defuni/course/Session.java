package org.defuni.course;

import org.defuni.infrastructure.Room;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Session {
    public Session(CourseContent content, LocalDateTime time, Room room){
        this.content = content;
        this.time = time;
        this.room = room;
    }
    private CourseContent content;
    private LocalDateTime time;
    private Room room;


}
