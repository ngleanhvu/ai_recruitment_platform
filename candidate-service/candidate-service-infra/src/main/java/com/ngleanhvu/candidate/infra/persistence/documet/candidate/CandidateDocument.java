package com.ngleanhvu.candidate.infra.persistence.documet.candidate;

import com.ngleanhvu.candidate.domain.model.candidate.*;
import java.util.List;

import com.ngleanhvu.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "candidates")
public class CandidateDocument extends BaseEntity {
  @Id private String id;
  private String userId;
  private String email;
  private String status;
  private Profile profile;
  private Address address;
  private String summary;
  private List<Skill> skills;
  private List<Experience> experiences;
  private List<Education> educations;
  private List<SocialLink> socialLinks;
}
