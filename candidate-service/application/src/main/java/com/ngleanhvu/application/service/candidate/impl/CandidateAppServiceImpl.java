package com.ngleanhvu.application.service.candidate.impl;

import com.ngleanhvu.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.application.mapper.CandidateMapper;
import com.ngleanhvu.application.service.candidate.CandidateAppService;
import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateAppServiceImpl implements CandidateAppService {

    private final CandidateMapper candidateMapper;
    private final CandidateRepository candidateDomainRepository;

    @Override
    public void createCandidate(CreateCandidateRequest request) {
        Candidate candidate = candidateMapper.toDomain(request);
        candidateDomainRepository.createCandidate(candidate);
    }

    @Override
    public CandidateDetailResponse getCandidateDetail(String candidateId) {
        Candidate candidate = candidateDomainRepository.getCandidateDetail(candidateId);
        return candidateMapper.toDetail(candidate);
    }
}
