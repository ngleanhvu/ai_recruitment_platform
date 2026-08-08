package com.ngleanhvu.application.service.candidate.impl;

import com.ngleanhvu.application.dto.request.CreateCandidateRequest;
import com.ngleanhvu.application.dto.request.SkillRequest;
import com.ngleanhvu.application.dto.response.CandidateDetailResponse;
import com.ngleanhvu.application.mapper.CandidateMapper;
import com.ngleanhvu.application.service.candidate.CandidateAppService;
import com.ngleanhvu.domain.model.candidate.Candidate;
import com.ngleanhvu.domain.model.candidate.CandidateId;
import com.ngleanhvu.domain.model.candidate.Skill;
import com.ngleanhvu.domain.repository.CandidateRepository;
import com.ngleanhvu.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CandidateAppServiceImpl implements CandidateAppService {

    private final CandidateMapper candidateMapper;
    private final CandidateRepository candidateDomainRepository;

    @Override
    public void createCandidate(CreateCandidateRequest request) {
        Candidate candidate = candidateMapper.toDomain(request);
        candidateDomainRepository.save(candidate);
    }

    @Override
    @Transactional(readOnly = true)
    public CandidateDetailResponse getCandidateDetail(CandidateId candidateId) {
        Candidate candidate = candidateDomainRepository
                .findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        return candidateMapper.toDetail(candidate);
    }

    @Override
    public void addSkillForCandidate(CandidateId candidateId, List<SkillRequest> request) {
        Candidate candidate = candidateDomainRepository
                .findById(candidateId)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

        List<Skill> skills = request.stream()
                        .map(candidateMapper::toSkill)
                        .toList();

        candidate.addSkills(skills);

        candidateDomainRepository.save(candidate);
    }
}
