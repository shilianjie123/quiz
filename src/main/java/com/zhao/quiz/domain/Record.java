package com.zhao.quiz.domain;

import lombok.Data;

@Data
public class Record {
    private int recordId;
    private String recordName;
    private int studentId;
    private int paperId;
    //答案
    private String recordAnswer;
    //正确率
    private Double recordAcc;
    //分数
    private int recordScore;
    private Student student;
    private String studentName;
}
