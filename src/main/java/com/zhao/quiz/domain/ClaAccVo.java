package com.zhao.quiz.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ColumnWidth(25)
@NoArgsConstructor
@AllArgsConstructor
public class ClaAccVo {
    @ExcelProperty(value = "考试名称")
    private String examName;
    @ExcelProperty(value = "班级")
    private String claName;
    @ExcelProperty(value = "参考人数")
    private int toscPer;
    @ExcelProperty(value = "及格人数")
    private int acscPer;
    @ExcelProperty(value = "及格率")
    private double acc;

}
