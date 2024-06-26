package org.defuni;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.api.core.ApiFutures;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.defuni.account.*;
import org.defuni.cli.*;
import org.defuni.course.Course;
import org.defuni.course.ScheduledClass;
import org.defuni.repository.CourseRepository;
import org.defuni.repository.CourseRepositoryFirebase;
import org.defuni.repository.ScheduledClassRepositoryFirebase;

public class Main {
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void sleep(int milisec) {
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test_init() {
        UserAccountFactory staffAccountFactory = new StaffAccountFactory();

        // to create studentAccount
        UserAccountFactory studentAccountFactory = new StudentAccountFactory();

        UserAccount user = staffAccountFactory.createUser(UserAccountType.LECTURER);
        user.setUserName("a");
        user.setPassword("a");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        
        //Cần  bổ sung chức năng thay đổi thông tin Class
        //hiển thị danh sách điểm của class HK2aL0
           /*  String schelleID = "HK2aL0";
            ScheduledClassRepositoryFirebase scheRepository = new ScheduledClassRepositoryFirebase();

            ScheduledClass sClasss = scheRepository.findScheduledClassByID(schelleID);
            sClasss.printList();*/

        LoginPage loginPage = new LoginPage();
        loginPage.gate();



    }
}