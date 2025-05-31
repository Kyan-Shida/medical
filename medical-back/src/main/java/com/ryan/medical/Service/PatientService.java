package com.ryan.medical.Service;

import com.ryan.medical.Mapper.PatientMapper;
import com.ryan.medical.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService implements PatientInterface{

    @Autowired
    private PatientMapper patientMapper;

    public void savePatient(Patient patient) {
         patientMapper.insertPatient(patient);
    }
}

