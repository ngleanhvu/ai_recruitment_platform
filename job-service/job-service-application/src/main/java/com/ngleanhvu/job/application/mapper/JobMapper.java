package com.ngleanhvu.job.application.mapper;

import com.ngleanhvu.job.application.dto.request.*;
import com.ngleanhvu.job.domain.model.job.*;
import com.ngleanhvu.job.domain.model.job.enums.*;
import com.ngleanhvu.shared.util.ValidationUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

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

    public WorkPlace toWorkPlace(WorkPlaceRequest request) {
        return new WorkPlace(WorkplaceType.from(request.workplaceType()), this.toAddress(request.address()));
    }

    public Address toAddress(AddressRequest request) {
        return new Address(request.city(), request.country());
    }

    public Benefit toBenefit(String value) {
        return new Benefit(value);
    }

    public SalaryRange toSalaryRange(SalaryRangeRequest request) {
        return new SalaryRange(
                new BigDecimal(request.min()),
                new BigDecimal(request.max()),
                Currency.from(request.currency())
        );
    }

    public JobRequirements toJobRequirements(JobRequirementRequest request) {
        List<SkillRequirement> skills = request.skills()
                .stream().map(this::toSkillRequirement)
                .toList();

        List<LanguageRequirement> languages = request.languages()
                .stream().map(this::toLanguageRequirement)
                .toList();

        ExperienceRequirement experience = this.toExperienceRequirement(request.experience());
        EducationRequirement education = this.toEducationRequirement(request.education());

        return new JobRequirements(skills,
                experience,
                education,
                languages);
    }

    private EducationRequirement toEducationRequirement(EducationRequirementRequest request) {
        return new EducationRequirement(EducationLevel.from(request.level()), request.required());
    }

    private LanguageRequirement toLanguageRequirement(LanguageRequirementRequest request) {
        return new LanguageRequirement(request.language(), LanguageLevel.from(request.level()));
    }

    private SkillRequirement toSkillRequirement(SkillRequirementRequest request) {
        return new SkillRequirement(request.name(), request.required(), request.minimumYears());
    }

    private ExperienceRequirement toExperienceRequirement(ExperienceRequirementRequest request) {
        return new ExperienceRequirement(request.minimumYears(), request.maximumYears());
    }
}
