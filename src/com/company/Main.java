package com.company;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        WorkWithStudents workWithStudents = new WorkWithStudents();
        Student[] students = workWithStudents.createStudent(new Student[0]);
        students = workWithStudents.sort(students);
        workWithStudents.sout(students);
    }
}
