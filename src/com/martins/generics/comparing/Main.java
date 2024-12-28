package com.martins.generics.comparing;

public class Main {
public static void main(String[] args) {

    Student student1 = new Student("John", 20, 101, 80.0f);
    Student student2 = new Student("Jane", 22, 102, 85.0f);

    if (student1.compareTo(student2) < 0) {
        System.out.println(student1 + " has less marks than " + student2);
    } else {
        System.out.println(student1 + " has more marks than " + student2);
    }
 }
}
