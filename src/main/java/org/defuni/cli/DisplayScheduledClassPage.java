
package org.defuni.cli;

import org.defuni.account.Lecturer;
import org.defuni.course.Course;
import org.defuni.course.ScheduledClass;
import org.defuni.repository.CourseRepositoryFirebase;
import org.defuni.repository.ScheduledClassRepositoryFirebase;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.defuni.Main.clearScreen;

public class DisplayScheduledClassPage {
    public DisplayScheduledClassPage(Lecturer lecturer) {
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("ScheduledClass :");
            System.out.println("Enter Class ID:");
            String courseID = scanner.nextLine();

            System.out.println("Enter Class ID:");
            String schelleID = scanner.nextLine();

            //String schelleID = "HK2aL0";
            ScheduledClassRepositoryFirebase scheRepository = new ScheduledClassRepositoryFirebase();
            ScheduledClass sClasss = scheRepository.findScheduledClassByID(schelleID);

            //cần một hàm in thêm thông tin lớp
            System.out.println("\n______________________________________________________\n");
            System.out.println("Choice:");
            System.out.println("2. Print ScoreList: ");
            System.out.println("7. Exit: ");
            System.out.println("\n______________________________________________________\n");
            System.out.println("Enter choice:");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    break;
                case 2:
                    sClasss.printList();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Invalid choice");
            }

        }   
    }
}

