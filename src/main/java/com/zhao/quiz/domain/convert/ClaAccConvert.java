package com.zhao.quiz.domain.convert;

import com.zhao.quiz.domain.ClaAcc;
import com.zhao.quiz.domain.ClaAccVo;

import java.util.List;
import java.util.stream.Collectors;

public class ClaAccConvert {
    public ClaAccVo getClaAccVoFromClaAcc(ClaAcc claAcc){
        ClaAccVo claAccVo = new ClaAccVo();
        claAccVo.setAcc(claAcc.getAcc());
        claAccVo.setAcscPer(claAcc.getAcscPer());
        claAccVo.setClaName(claAcc.getClaName());
        claAccVo.setExamName(claAcc.getExamName());
        claAccVo.setToscPer(claAcc.getToscPer());
        return claAccVo;
    }
    public List<ClaAccVo> createClaAccVoFromClaAccs(List<ClaAcc> claAccs) {
        return claAccs.stream().map(this::getClaAccVoFromClaAcc).collect(Collectors.toList());
    }
}
