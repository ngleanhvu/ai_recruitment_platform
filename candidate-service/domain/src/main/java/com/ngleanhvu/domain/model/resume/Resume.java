package com.ngleanhvu.domain.model.resume;

import com.ngleanhvu.shared.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Resume extends BaseEntity {
    private String id;
    private String candidateId;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private int version;
    private String status;
    private LocalDateTime uploadAt;
}
