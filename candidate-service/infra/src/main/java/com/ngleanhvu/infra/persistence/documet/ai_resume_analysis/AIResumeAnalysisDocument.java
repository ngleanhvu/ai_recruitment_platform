package com.ngleanhvu.infra.persistence.documet.ai_resume_analysis;

import com.ngleanhvu.domain.model.ai_resume_analysis.ExtractedSkill;
import com.ngleanhvu.shared.entity.BaseEntity;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "ai_resume_analyses")
public class AIResumeAnalysisDocument extends BaseEntity {
  private String id;
  private String resumeId;
  private String candidateId;
  private String model;
  private String summary;
  private List<ExtractedSkill> extractedSkills;
  private int experienceYears;
  private double score;
}
