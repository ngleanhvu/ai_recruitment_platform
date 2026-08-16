package com.ngleanhvu.application.infra.persistence.document.application;

import com.ngleanhvu.domain.model.application.MatchingResult;
import com.ngleanhvu.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "applications")
public final class ApplicationDocument extends BaseEntity {
    @Id
    private String id;
    private String candidateId;
    private String jobId;
    private String resumeId;
    private String status;
    private String coverLetter;
    private String source;
    private MatchingResult matching;
    private LocalDateTime uploadAt;
}
