package com.ngleanhvu.job.application.mapper;

import com.ngleanhvu.job.application.dto.request.AddressRequest;
import com.ngleanhvu.job.application.dto.request.CreateJobRequest;
import com.ngleanhvu.job.application.dto.request.WorkPlaceRequest;
import com.ngleanhvu.job.domain.model.job.Address;
import com.ngleanhvu.job.domain.model.job.Job;
import com.ngleanhvu.job.domain.model.job.WorkPlace;
import com.ngleanhvu.job.domain.model.job.enums.EmploymentType;
import com.ngleanhvu.job.domain.model.job.enums.WorkplaceType;
import com.ngleanhvu.shared.util.ValidationUtil;
import org.springframework.stereotype.Component;

@Component
public final class JobMapper {
    public Job toDomain(CreateJobRequest request) {
        if (ValidationUtil.isNull(request))
            return null;

        WorkPlace workPlace = this.toWorkPlace(request.workplace());

        return Job.create(
                request.recruiterId(),
                request.title(),
                request.description(),
                EmploymentType.from(request.employmentType()),
                workPlace
        );
    }

    private WorkPlace toWorkPlace(WorkPlaceRequest request) {
        return new WorkPlace(WorkplaceType.from(request.workplaceType()), this.toAddress(request.address()));
    }

    private Address toAddress(AddressRequest request) {
        return new Address(request.city(), request.country());
    }
}
