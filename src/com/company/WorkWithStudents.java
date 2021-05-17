package com.company;

import java.io.IOException;

public class WorkWithStudents {
    public Student[] createStudent(Student[] students) throws IOException {
        while (true) {
            String name = checkName();
            System.out.print("Введіть середній бал студента: ");
            int averageScore = DataInput.getInt();
            Student newStudent = new Student(name, averageScore);
            students = addInArray(students, newStudent);

            //продовжати чи ні
            System.out.print("Введіть 1 якщо бажаєте продовжити вводити студентів або інше число якщо бажаєте припинити: ");
            int ifWant = DataInput.getInt();
            if (ifWant != 1) {
                break;
            }
        }
        return students;
    }

    public Student[] sort(Student[] students) {
        int nameOrScore = checkHowSort("1) Сортувати по імені студента", "2) Відсортувати за середнім балом студента");
        int increaseOrDecrease = checkHowSort("1)Сортувати за зростанням", "2)Сортувати по спадаючій");
        if (nameOrScore == 1 && increaseOrDecrease == 1) {
            students = sortName(students);
        } else if (nameOrScore == 2 && increaseOrDecrease == 1) {
            students = sortAverageScore(students);
        } else if (nameOrScore == 1 && increaseOrDecrease == 2) {
            students = expand(sortName(students));
        } else if (nameOrScore == 2 && increaseOrDecrease == 2) {
            students = expand(sortAverageScore(students));
        }
        return students;
    }

    private Student[] sortAverageScore(Student[] students) {
        if (students.length < 2) {
            return students;
        }

        Student[] ints1 = new Student[students.length / 2];
        for (int i = 0; i < ints1.length; i++) {
            ints1[i] = students[i];
        }
        Student[] ints2 = new Student[students.length - students.length / 2];
        int a = 0;
        for (int i = students.length / 2; i < students.length; i++) {
            ints2[a] = students[i];
            a++;
        }
        ints1 = sortAverageScore(ints1);
        ints2 = sortAverageScore(ints2);

        return mergerScore(ints1, ints2);
    }

    private Student[] expand(Student[] students) {
        Student student;
        for (int i = 0; i < students.length / 2; i++) {
            student = students[i];
            students[i] = students[students.length - 1 - i];
            students[students.length - 1 - i] = student;
        }
        return students;
    }

    private Student[] sortName(Student[] students) {
        if (students.length < 2) {
            return students;
        }
        Student[] studentsCopy = new Student[students.length];
        for (int i = 0; i < students.length; i++) {
            studentsCopy[i] = students[i];
        }
        Student[] students1 = new Student[studentsCopy.length / 2];
        for (int i = 0; i < students1.length; i++) {
            students1[i] = studentsCopy[i];
        }
        Student[] students2 = new Student[studentsCopy.length - studentsCopy.length / 2];
        int a = 0;
        for (int i = studentsCopy.length / 2; i < studentsCopy.length; i++) {
            students2[a] = studentsCopy[i];
            a++;
        }
        students1 = sortName(students1);
        students2 = sortName(students2);

        return mergerName(students1, students2);
    }

    private Student[] mergerScore(Student[] ints1, Student[] ints2) {
        Student[] students = new Student[ints1.length + ints2.length];
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < students.length; i++) {
            if (i1 < ints1.length && i2 < ints2.length && ints1[i1].getAverageScore() <= ints2[i2].getAverageScore()) {
                students[i] = ints1[i1];
                i1++;
            } else if (i2 == ints2.length) {
                students[i] = ints1[i1];
                i1++;
            } else if (i1 == ints1.length) {
                students[i] = ints2[i2];
                i2++;
            } else {
                students[i] = ints2[i2];
                i2++;
            }
        }
        return students;
    }

    private Student[] mergerName(Student[] ints1, Student[] ints2) {
        Student[] students = new Student[ints1.length + ints2.length];
        int i1 = 0;
        int i2 = 0;
        for (int i = 0; i < students.length; i++) {
            if (i1 < ints1.length && i2 < ints2.length && ints1[i1].getName().charAt(0) <= ints2[i2].getName().charAt(0)) {
                students[i] = ints1[i1];
                i1++;
            } else if (i2 == ints2.length) {
                students[i] = ints1[i1];
                i1++;
            } else if (i1 == ints1.length) {
                students[i] = ints2[i2];
                i2++;
            } else {
                students[i] = ints2[i2];
                i2++;
            }
        }

        return students;
    }

    private int checkHowSort(String s1, String s2) {
        System.out.println(s1);
        System.out.println(s2);
        System.out.print("Виберіть варіант сортування масиву студентів: ");
        int way = DataInput.getInt();
        while (way > 2 || way < 1) {
            System.out.println("Введіть 1 або 2!");
            way = DataInput.getInt();
        }
        return way;
    }

    private String checkName() throws IOException {
        System.out.print("Введіть ім'я студента: ");
        String name = DataInput.getString();
        for (int i = 0; i < name.length(); i++) {
            if (!((name.charAt(i) >= 65 && name.charAt(i) <= 90) || (name.charAt(i) >= 97 && name.charAt(i) <= 122)) || name.charAt(0) < 65 || name.charAt(0) > 90) {
                System.out.print("Введіть ім'я студента використовуючи тільки літери англійського алфавіту:перша літера повинна бути великою ");
                name = DataInput.getString();
                i = -1;
            }
        }
        return name;
    }

    private Student[] addInArray(Student[] students, Student student) {
        Student[] studentsHelper = students;
        students = new Student[students.length + 1];
        students[students.length - 1] = student;
        for (int i = 0; i < studentsHelper.length; i++) {
            students[i] = studentsHelper[i];
        }
        return students;
    }

    public void sout(Student[] students) {
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].toString());
        }
    }
}