package com.ryan.medical.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private String name;
    private String gender;
    private Integer age;
    private String department;
    private LocalDateTime visitTime;
    private String phone;
    private String address;
    private String complaints;
    private String currentHistory;
    private String pastHistory;
    private String allergyHistory;
    private String diagnosis;

    // Getters and setters
}

