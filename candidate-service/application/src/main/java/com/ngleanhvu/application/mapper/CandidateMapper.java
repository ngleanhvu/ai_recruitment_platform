package com.ngleanhvu.application.mapper;

import com.ngleanhvu.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.model.candidate.Profile;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {
    public Candidate toDomain(CreateCandidateRequest request) {
        Candidate candidate = new Candidate();
        candidate.setSummary(request.summary());

        Profile profile = new Profile();
        profile.setFirstName(request.firstName());
        profile.setLastName(request.lastName());
        profile.setPhone(request.phone());
        profile.setEmail(request.email());
        candidate.setProfile(profile);

        return candidate;
    }
}
