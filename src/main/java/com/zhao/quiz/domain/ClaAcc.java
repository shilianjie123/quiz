package com.zhao.quiz.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: swung
 * @Date: 2020/2/1 13:22
 * @Description:及格率展示
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClaAcc {
    private int clasaccId;
    private String examName;
    private String claName;
    private int toscPer;
    private int acscPer;
    private double acc;

}
