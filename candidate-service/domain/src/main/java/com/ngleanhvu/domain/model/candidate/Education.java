package com.ngleanhvu.domain.model.candidate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Education {
    private String school;
    private String major;
    private String degree;
    private double gpa;
    private int startYear;
    private int endYear;
}
