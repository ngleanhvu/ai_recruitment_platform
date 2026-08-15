package com.ngleanhvu.candidate.infra.persistence.documet.resume;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "resumes")
public class ResumeDocument {
  @Id private String id;
  private String candidateId;
  private Integer version;
  private String fileName;
  private String fileKey;
  private String status;
}
