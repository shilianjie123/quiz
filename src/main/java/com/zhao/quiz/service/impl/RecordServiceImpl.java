package com.zhao.quiz.service.impl;

import com.zhao.quiz.domain.*;
import com.zhao.quiz.mapper.RecordMapper;
import com.zhao.quiz.service.ClasseService;
import com.zhao.quiz.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private RecordService recordService;
    @Autowired
    private ClasseService classeService;
    HashMap<String, List<ClaAcc>> map = new HashMap<>();

    public void addRecord(Record record) {
        recordMapper.addRecord(record);
    }

    @Override
    public List<Record> queryAll() {
        return recordMapper.queryAll();
    }

    @Override
    public void deleteById(Integer id) {
        recordMapper.deleteById(id);
    }

    @Override
    public List<Record> queryAllExamById(Integer id) {
        return recordMapper.queryAllExamById(id);
    }

    @Override
    public Integer queryByRecordId(Integer id) {
        return recordMapper.queryByRecordId(id);
    }

    @Override
    public String queryAnsByRecordId(Integer id) {
        return recordMapper.queryAnsByRecordId(id);
    }

    @Override
    public List<Record> queryRankScoreRecord() {
        return recordMapper.queryRankScoreRecord();
    }

    @Override
    public List<Record> queryRankAccRecord() {
        return recordMapper.queryRankAccRecord();
    }

    @Override
    public List<Record> queryAllExam() {
        return recordMapper.queryAllExam();
    }

    @Override
    public List<Classe> queryAllClass(String exaName) {
        return recordMapper.queryAllClass(exaName);
    }

    @Override
    public int queryAllScore(RecordExam recordExam) {
        return recordMapper.queryAllScore(recordExam);
    }

    @Override
    public int queryAccScore(RecordExam recordExam) {
        return recordMapper.queryAccScore(recordExam);
    }

    @Override
    public void AddToScore(Toscore toscore) {
        recordMapper.AddToScore(toscore);
    }

    @Override
    public int queryBooleanToscore(Integer paperId) {
        return recordMapper.queryBooleanToscore(paperId);
    }

    @Override
    public int queryToscore(int paperId) {
        return recordMapper.queryToscore(paperId);
    }

    @Override
    public List<ClaAcc> showClaAcc() {

        //查询所有测试名称
        List<Record> records = recordService.queryAllExam();
        List<ClaAcc> claAccRes = new ArrayList<>();
        //按照测试名称查询所有班级
        for (Record rec : records) {
            //通过记录对应考试paperid查找总分***
            int paperid = rec.getPaperId();
            int toscore = recordService.queryToscore(paperid);
            //记录考试名
            String exaName = rec.getRecordName();
            List<Classe> clas = recordService.queryAllClass(exaName);
            //初始化所有人和及格人数
            int allScore = 0;
            int accScore = 0;
            for (Classe cla : clas) {
                int claId = cla.getClasseId();
                //班级信息
                Classe claName = classeService.queryClaNameById(claId);
                RecordExam recordExam = new RecordExam();
                recordExam.setClaId(claId);
                recordExam.setExaName(exaName);
                double setToscore = toscore * 0.6;
                recordExam.setToscore(setToscore);
                //对应每一个班级,查询考试人数和及格人数
                allScore = recordService.queryAllScore(recordExam);
                //及格人数默认>60***
                accScore = recordService.queryAccScore(recordExam);
                double accre = (double) accScore / allScore;
                //四舍五入保留2位
                double acc = (double) Math.round(accre * 100) / 100;

                ClaAcc claAcc = new ClaAcc();
                claAcc.setExamName(exaName);
                claAcc.setClaName(claName.getClasseName());
                claAcc.setToscPer(allScore);
                claAcc.setAcscPer(accScore);
                claAcc.setAcc(acc);
                //每个对象添加到list
                claAccRes.add(claAcc);
            }
        }
        map.put("claAccRes", claAccRes);
        return claAccRes;
    }

    @Override
    public Map<String, List<ClaAcc>> getMap() {
        Map<String, List<ClaAcc>> mapV2 = new HashMap<>();
        mapV2.putAll(map);
        return mapV2;
    }
}
