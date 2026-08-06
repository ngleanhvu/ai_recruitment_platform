package com.ngleanhvu.domain.model.candidate;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Experience {
    private String company;
    private String position;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isCurrent;
}
