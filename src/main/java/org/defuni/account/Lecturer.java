package org.defuni.account;

import org.defuni.course.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lecturer extends UserAccount implements LecturerObserver {
    private String department;
    private List<String> certificates;

    //cần cập nhật classesIncharged của giáo viên khi xếp lớp dể khi xét điểm có thể tìm thấy lớp mà Giảng Viên đứng
    private List<ScheduledClass> classesIncharged;

    public static int ID = 1;

    public Lecturer() {
        setAccType(UserAccountType.LECTURER);

        this.department = "default";
        this.certificates = new ArrayList<String>();
        this.classesIncharged = new ArrayList<>();
    }

    public void updateClass() { // Need target

    }

    public void updateGrade() { // Need target, update class, not student

    }

    public void updateContent(ScheduledClass scheduledClass, String Content) { // Need target, update class, not student
        // if (scheduledClass has this as main teacher)
        scheduledClass.setContent(Content);
    }

    public boolean inspectStudentScore() { // Need target
        return true;
    }

    public boolean courseCreate() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter course ID:");
        String courseID = scanner.nextLine();

        System.out.println("Enter course title:");
        String courseTitle = scanner.nextLine();

        // Assuming you have appropriate constructors for Department and CourseContent
        // classes
        Course course = new Course();
        course.setCourseID(courseID);
        course.setCourseTitle(courseTitle);
        course.setLecturerInCharge(this);

        // Add the created course to the list of courses
        Manager.addCourse(course);

        System.out.println(String.format("Course %s created successfully!", course.getCourseTitle()));

        scanner.nextLine();
        clearScreen();

        // scanner.close(); // Dont close the scanner!
        return true;
    }

    public void addClass(ScheduledClass sch) {
        this.classesIncharged.add(sch);
    }

    /* ~~~~~~~~~~~~~~~~~~~~
    public void setScore(String sch, int MSSV, double[] data) {
        ScheduledClass choseClass = checkClass(sch);
        if (choseClass == null) {
            System.out.print("Not Found");
            return;
        }
        choseClass.setScore(MSSV, data);
    }

    public ScheduledClass checkClass(String sch) {
        for (ScheduledClass findSch : classesIncharged) {
            if (findSch.getClassID().equalsIgnoreCase(sch)) {
                return findSch;
            }
        }
        return null;
    }

    public void printListScore(String sch) {
        ScheduledClass choseClass = checkClass(sch);
        if (choseClass == null) {
            System.out.print("Not Found\n");
            return;
        }
        choseClass.printListScore();
    }

    public void sortListScore(String sch) {
        ScheduledClass choseClass = checkClass(sch);
        if (choseClass == null) {
            System.out.print("Not Found\n");
            return;
        }
        choseClass.sortListScore();
    }

    public SinglyLinkedList getListScore(String sch) {
        ScheduledClass choseClass = checkClass(sch);
        if (choseClass == null) {
            System.out.print("Not Found\n");
            return null;
        }
        return choseClass.getListScore();
    }

    public double[] getScoreStudent(String sch, int MSSV) {
        ScheduledClass choseClass = checkClass(sch);
        if (choseClass == null) {
            System.out.print("Not Found\n");
            return null;
        }
        return choseClass.getScoreStudent(MSSV);
    }

    public void printScoreStudent(String sch, int MSSV) {
        ScheduledClass choseClass = checkClass(sch);
        if (choseClass == null) {
            System.out.print("Not Found\n");
            return;
        }
        choseClass.printScoreStudent(MSSV);
    }

    public void updateScore(String sch, int MSSV, double[] data) {
        ScheduledClass choseClass = checkClass(sch);
        if (choseClass == null) {
            System.out.print("Not Found\n");
            return;
        }
        choseClass.updateScore(MSSV, data);
    }

    // ~~~~~~~~~~~~~~~~~~~~*/ 

    //~~~~~~~~~listScore~~~~~~~~~~~





    //~~~~~~~~~listScore~~~~~~~~~~~

     //kiểm tra GV có đứng lớp này không
     public ScheduledClass checkClass(String sch) {

        //classesIncharged cần được cập nhật đầy đủ
        for (ScheduledClass findSch : classesIncharged) {
            if (findSch.getClassID().equalsIgnoreCase(sch)) {
                return findSch;
            }
        }
        return null;
    }

    //thêm điểm sv
    public void addStudenScore(String c_class, String MSSV, double[] data) {
        ScheduledClass choseClass = checkClass(c_class);
        if (choseClass == null) {
            System.out.println("Not Your Class");
            return;
        }
        choseClass.addScore(MSSV,data);
    }

    //in điểm cả lớp
    public void printList(String c_class){
        ScheduledClass choseClass = checkClass(c_class);
        if (choseClass == null) {
            System.out.println("Not Your Class");
            return;
        }
        choseClass.printList();
    }

    //cập nhật điểm sinh viên, sai trả false
    public boolean updateStudentScore(String c_class, String MSSV, double[] newData){
        ScheduledClass choseClass = checkClass(c_class);
        if (choseClass == null) {
            System.out.println("Not Your Class");
            return false;
        }

        return choseClass.updateScore(MSSV, newData);
    }

    //in điểm của một sinh viên bất kỳ, có trong lớp
    public void printScoreStudent(String c_class, String MSSV){
        ScheduledClass choseClass = checkClass(c_class);
        if (choseClass == null) {
            System.out.println("Not Your Class");
            return;
        }

        choseClass.printScoreStudent(MSSV);
        return ;
    }

    //trả về list score của một sinh viên bất kỳ, giảng viên cần thiết?
    /*public List<Double> getScoreStudent(String c_class, String MSSV){
        ScheduledClass choseClass = checkClass(c_class);
        return choseClass.getScoreStudent(MSSV);
    }*/
   


}
