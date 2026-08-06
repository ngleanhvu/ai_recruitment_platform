package com.ngleanhvu.infra.persistence.documet.resume;

import com.ngleanhvu.shared.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "resumes")
public class ResumeDocument extends BaseEntity {
    @Id
    private String id;
    private String candidateId;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private int version;
    private String status;
    private LocalDateTime uploadAt;
}
