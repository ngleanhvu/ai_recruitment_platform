package com.ngleanhvu.candidate.application.service.candidate;

import com.ngleanhvu.candidate.application.port.input.candidate.ExistCandidateByIdUseCase;
import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import org.springframework.stereotype.Service;

@Service
public record ExistCandidateByIdService (
        CandidateRepository candidateRepository
) implements ExistCandidateByIdUseCase {

    @Override
    public boolean execute(CandidateId candidateId) {
        return candidateRepository.existById(candidateId);
    }
}
