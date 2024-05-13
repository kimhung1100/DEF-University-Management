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

public class UpdateScheduledClass {
    public UpdateScheduledClass(Lecturer lecturer) {
        clearScreen();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Scheduled Class Updating:");
            System.out.println("Enter Class ID:");
            String schelleID = scanner.nextLine();

            //String schelleID = "HK2aL0";
            ScheduledClassRepositoryFirebase scheRepository = new ScheduledClassRepositoryFirebase();


            ScheduledClass sClasss = scheRepository.findScheduledClassByID(schelleID);
            //System.out.println(sClasss.toString());
            System.out.println("\n______________________________________________________\n");
            System.out.println("Update choice:");
            System.out.println("1. Update class ID");
            System.out.println("2. Update class title");
            System.out.println("3. Update class content");
            System.out.println("10. Exit: ");
            System.out.println("\n______________________________________________________\n");
            System.out.println("Enter choice:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Thêm dòng này để tránh lỗi Enter?


            switch (choice) {
                case 1:
                    System.out.println("Enter new class ID:");
                    String newClassID = scanner.nextLine();
                    sClasss.setClassID(newClassID);
                    break;
                
                case 7:
                    break;
                default:
                    System.out.println("Invalid choice");
            }
            scheRepository.saveScheduledClass(sClasss);
            System.out.println("Class Update Successful!");

        }
    }
}
