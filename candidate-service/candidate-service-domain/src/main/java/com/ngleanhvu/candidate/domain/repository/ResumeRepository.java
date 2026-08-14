package com.ngleanhvu.candidate.domain.repository;

import com.ngleanhvu.candidate.domain.model.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.model.resume.Resume;
import com.ngleanhvu.candidate.domain.model.resume.ResumeId;

import java.util.List;
import java.util.Optional;

public interface ResumeRepository {
    void save(Resume resume);
    Optional<Resume> findById(ResumeId id);
    List<Resume> findByCandidateId(CandidateId candidateId);
    Optional<Resume> findLatestByCandidateId(CandidateId candidateId);
    Integer getNextVersion(CandidateId candidateId);
}
