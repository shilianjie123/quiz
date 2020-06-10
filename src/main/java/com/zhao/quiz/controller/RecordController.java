package com.zhao.quiz.controller;

import com.zhao.quiz.domain.*;
import com.zhao.quiz.service.ClasseService;
import com.zhao.quiz.service.PaperService;
import com.zhao.quiz.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    RecordService recordService;
    @Autowired
    PaperService paperService;
    @Autowired
    ClasseService classeService;

    //获取所有记录
    @RequestMapping("/getAllRecord")
    public String getAllRecord(Model model){
        List<Record> records=recordService.queryAll();
        for(Record record : records){
            record.setStudentName(record.getStudent().getStudentName());
        }
        model.addAttribute("records",records);
        return "record/RecordList";
    }
    //删除记录
    @RequestMapping("/deleteRecore/{id}")
    public String deleteRecore(@PathVariable ("id") Integer id){
        recordService.deleteById(id);
        return "redirect:/record/getAllRecord";
    }
    //根据记录id获取试卷详情
    @RequestMapping("/toShowExamHist/{id}")
    public String toShowExamHist(@PathVariable ("id")Integer id,Model model){
        //通过记录id查找试卷和答题记录
        Integer papid=recordService.queryByRecordId(id);
        String answers=recordService.queryAnsByRecordId(id);
        //原始试卷
        List<QuestionPaper> questionPapers = paperService.paperQueryALlQuestionByIdOrderByType(papid);
        //提交过的答案
        List<String> ans = Arrays.asList(answers.split(","));
        model.addAttribute("questionPapers",questionPapers);
        model.addAttribute("ans",ans);
        //2020-1-26-20:40
        return "record/showExamHist";
    }

    //显示所有班级记录
    @RequestMapping("/showClaAcc")
    public String showClaAcc(Model model){
        List<ClaAcc> claAccRes = recordService.showClaAcc();
        model.addAttribute("claAccRes",claAccRes);
        return "record/claAcc";
    }
}
