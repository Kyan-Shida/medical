package com.ryan.medical.Controller;

import com.ryan.medical.Service.PatientService;
import com.ryan.medical.pojo.Patient;
import com.ryan.medical.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@Slf4j
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/api/upload")
    public Result uploadPatient(@RequestBody Patient patient) {
        log.info("patient是：{}",patient);
        patientService.savePatient(patient);
        String answer="这是我的病历："+"姓名："+patient.getName()+"性别:"+patient.getGender()+"年龄:"+patient.getAge()+"主诉:"+patient.getDepartment()+"现病史:"+patient.getCurrentHistory()+"既往史:"+patient.getPastHistory()+"过敏史:"+patient.getAllergyHistory()+"诊断:"+patient.getDiagnosis()+"你帮我分析一下";
        return Result.success(answer);
    }
}

