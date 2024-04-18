package org.defuni;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.api.core.ApiFutures;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.defuni.account.*;
import org.defuni.cli.*;
import org.defuni.course.Course;
import org.defuni.course.ScheduledClass;
import org.defuni.repository.CourseRepository;
import org.defuni.repository.CourseRepositoryFirebase;
import org.defuni.repository.ScheduledClassRepository;

import javax.sound.midi.SysexMessage;

public class scoreList {
    
    public void scoreist(String[] args) throws ExecutionException, InterruptedException {
        Lecturer lecturer = new Lecturer();
        ScheduledClass scClass = new ScheduledClass("CO5259");

        //đăng ký lớp cho giáo viên, và giáo viên thêm lớp vào mảng
        scClass.registerObserver(lecturer);
        lecturer.addClass(scClass);

        lecturer.setScore("CO5259", 2201221, new double[]{5,6,7});
        lecturer.setScore("CO5259", 2201221, new double[]{5,6,8});
        lecturer.printListScore("CO5259");
        
    }
}
