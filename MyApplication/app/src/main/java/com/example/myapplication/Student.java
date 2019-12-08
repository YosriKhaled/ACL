package com.example.myapplication;

public class Student {




    private String name;

    private String studentId ;

    private String password ;



    public Student(String name, String studentId, String password) {

        this.name = name;
        this.studentId = studentId;
        this.password = password;

    }



    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPassword() {
        return password;
    }


}

