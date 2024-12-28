package com.martins.generics.comparing;

public class Student implements Comparable<Student> {
    private String name;
    private int age;
    private int rollNumber;
    private float marks;

    public Student(String name, int age, int rollNumber, float marks) {
        this.name = name;
        this.age = age;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }

    @Override
    public int compareTo(Student student) {
        return (int) (this.marks - student.marks);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
