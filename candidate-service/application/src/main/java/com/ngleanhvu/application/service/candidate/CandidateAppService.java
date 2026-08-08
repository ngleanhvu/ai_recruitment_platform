package com.ngleanhvu.application.service.candidate;

import com.ngleanhvu.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.application.dto.request.SkillRequest;
import com.ngleanhvu.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.domain.model.candidate.CandidateId;
import com.ngleanhvu.domain.model.candidate.Skill;

import java.util.List;

public interface CandidateAppService {
    void createCandidate(CreateCandidateRequest request);
    CandidateDetailResponse getCandidateDetail(CandidateId candidateId);
    void addSkillForCandidate(CandidateId candidateId, List<SkillRequest> request);
}
