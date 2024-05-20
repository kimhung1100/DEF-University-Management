package org.defuni.cli;

import java.util.Scanner;

import org.defuni.account.*;
import org.defuni.course.*;
import org.defuni.repository.*;

import java.util.*;

//public class UpdateStudentGradePage {
//    UpdateStudentGradePage() {
//        this.run();
//    }
//
//    private void clearScreen() {
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
//    }
//
//    public void sleep(int milisec) {
//        try { // Sleep 1000ms before clear screen
//            Thread.sleep(milisec);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void run() {
//        System.out.println("Update Student Grade Page");
//        Manager manager = Manager.getInstance();
//        Scanner scanner = new Scanner(System.in);
//        ScheduledClassRepositoryFirebase scheRepository = new ScheduledClassRepositoryFirebase();
//
//        while (true) {
//            clearScreen();
//            System.out.println("Available Classes:");
//            Manager.displayDocs("scheduledClass");
//
//            String classID = scanner.nextLine();
//            ScheduledClass scheduledClass = scheRepository.findScheduledClassByID(classID);
//
//            if (scheduledClass == null) {
//                System.out.println("Class not found.");
//                sleep(1000);
//
//                continue;
//            } else {
//                List<String> studentList = manager.getFieldList(manager.retriveDB(), "scheduledClass", classID,
//                        "studentList");
//                for (String student : studentList) {
//                    System.out.print(student + "\t");
//                }
//                System.out.println(); // Print a newline at the end
//
//                System.out.println("Enter student ID:");
//                String studentID = scanner.nextLine();
//
//                if (!studentList.contains(studentID)) {
//                    System.out.println("Student not found in class.");
//                    sleep(1000);
//                    continue;
//                }
//
//                else {
//                    Map<String, Object> mapStudent = manager.findDocument("students", studentID);
//                    Student student = manager.convStudent(mapStudent);
//
//                    System.out.println("Enter new grade:");
//                    String newGrade = scanner.nextLine();
//                    manager.updateDocument(Manager.getDB(), classID, "studentList", studentID, newGrade);
//                    System.out.println("Grade updated successfully.");
//                    sleep(1000);
//                }
//
//            }
//
//        }
//
//    }
//}
