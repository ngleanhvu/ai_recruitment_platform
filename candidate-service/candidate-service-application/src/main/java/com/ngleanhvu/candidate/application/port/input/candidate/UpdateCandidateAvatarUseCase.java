package com.ngleanhvu.candidate.application.port.input.candidate;

import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import org.springframework.web.multipart.MultipartFile;

public interface UpdateCandidateAvatarUseCase {
    void execute(CandidateId candidateId, MultipartFile file);
}
