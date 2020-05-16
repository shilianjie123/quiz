package com.zhao.quiz.domain;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int studentId;
    private String studentName;
    private String studentAccount;
    private String studentGender;
    private String studentEmail;
    private String studentPwd;
    private int classeId;
    private String registerNumber;
    private Classe classe;
}
