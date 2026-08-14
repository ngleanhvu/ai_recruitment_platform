package com.ngleanhvu.job.infra.persistence.document.job;

import com.ngleanhvu.job.domain.model.job.JobRequirements;
import com.ngleanhvu.job.domain.model.job.SalaryRange;
import com.ngleanhvu.job.domain.model.job.WorkPlace;
import com.ngleanhvu.shared.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "jobs")
@Getter
@Setter
public class JobDocument extends BaseEntity {
    @Id
    private String id;
    private String recruiterId;
    private String title;
    private String description;
    private String employmentType;
    private WorkPlace workplace;
    private SalaryRange salary;
    private JobRequirements requirements;
    private List<String> benefits;
    private String status;
    private LocalDateTime  applicationDeadline;
    private LocalDateTime publishedAt;
}
