package org.defuni.course;

import java.util.Arrays;

class Node {
    // MSSV, danh sách điểm
    int MSSV;
    double[] data;
    Node next;

    public Node(int MSSV, double[] data) {
        // làm tròn đến 2 chũ số thập phân

        this.MSSV = MSSV;
        this.data = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            this.data[i] = Math.round(data[i] * 100.0) / 100.0;
        }

        this.next = null;
    }
}

public class SinglyLinkedList {
    private Node head;

    //add, tự xếp thứ tự từ bé đến lớn theo MSSV
    public void add(int MSSV, double[] data) {

        if(this.findMSSV(MSSV)){
            System.out.print("Use updateScore(MSSV)\n");
            return;
        } 


        Node newNode = new Node(MSSV, data);
        if (head == null || MSSV < head.MSSV  ) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null && current.next.MSSV < MSSV) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
    }

    //sort
    public void sortListByMSSV() {
        if (head == null || head.next == null) {
            return; 
        }
        boolean wasChanged;
        do {
            Node current = head;
            Node previous = null;
            Node next = head.next;
            wasChanged = false;

            while (next != null) {
                if (current.MSSV > next.MSSV) {
                    wasChanged = true;
                    if (previous != null) {
                        Node sig = next.next;

                        previous.next = next;
                        next.next = current;
                        current.next = sig;
                    } else {
                        Node sig = next.next;

                        head = next;
                        next.next = current;
                        current.next = sig;
                    }
                    previous = next;
                    next = current.next;
                } else {
                    previous = current;
                    current = next;
                    next = next.next;
                }
            }
        } while (wasChanged);
    }


    //in MSSV + điểm
    public void printList() {
        Node current = head;
        while (current != null) {

            String[] formattedFractions = Arrays.stream(current.data)
            .mapToObj(f -> String.format("%.2f", f))
            .toArray(String[]::new);

            System.out.print("MSSV: " + current.MSSV + ", Score: " + Arrays.toString(formattedFractions) + "\n");
            current = current.next;
        }
    }
    
    //tìm 
    public boolean findMSSV(int MSSV) {
        Node current = head;
        while (current != null) {
            if (current.MSSV == MSSV) {
                return true; // Tìm thấy MSSV
            }
            current = current.next;
        }
        return false; // Không tìm thấy MSSV
    }

    // cập nhật MSSV có sẵn
    public boolean updateScore(int MSSV, double[] newData) {
        Node current = head;
        while (current != null) {
            if (current.MSSV == MSSV) {
                current.data = newData; 
                for (int i = 0; i < newData.length; i++) {
                    // Làm tròn đến hai chữ số sau dấu phẩy
                    current.data[i] = Math.round(newData[i] * 100.0) / 100.0;
                }
                return true; 
            }
            current = current.next;
        }
        return false; 
    }

    public void printStudent(int MSSV) {
        Node current = head;
        while (current != null) {
            if (current.MSSV == MSSV) {
                String[] formattedFractions = Arrays.stream(current.data)
                .mapToObj(f -> String.format("%.2f", f))
                .toArray(String[]::new);
    
                System.out.print("MSSV: " + current.MSSV + ", Score: " + Arrays.toString(formattedFractions) + "\n");                return;
            }
            current = current.next;
        }
        System.out.println("Not found: " + MSSV);
    }

    //tim và trả về điểm của sinh viên
    public double[] Score(int MSSV){
        Node current = head;
        while (current != null) {
            if (current.MSSV == MSSV) {
                return current.data;
            }
            current = current.next;
        }
        return null; 
    }

}





