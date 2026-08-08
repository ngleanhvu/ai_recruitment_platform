package com.ngleanhvu.domain.model.ai_resume_analysis;

import com.ngleanhvu.shared.entity.BaseEntity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AIResumeAnalysis extends BaseEntity {
  private String id;
  private String resumeId;
  private String candidateId;
  private String model;
  private String summary;
  private List<ExtractedSkill> extractedSkills;
  private int experienceYears;
  private double score;
}
