package com.ngleanhvu.domain.model.candidate;

import com.ngleanhvu.shared.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class Candidate extends BaseEntity {
    private String id;
    private String userId;
    private Profile profile;
    private Address address;
    private String summary;
    private List<Skill> skills;
    private List<Experience> experiences;
    private List<Education> educations;
    private List<SocialLink> socialLinks;
}
