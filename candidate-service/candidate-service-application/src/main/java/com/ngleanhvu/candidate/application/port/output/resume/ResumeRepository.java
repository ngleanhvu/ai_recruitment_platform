package com.ngleanhvu.candidate.application.port.output.resume;

import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.resume.Resume;
import com.ngleanhvu.candidate.domain.resume.ResumeId;
import java.util.List;
import java.util.Optional;

public interface ResumeRepository {
  void save(Resume resume);

  Optional<Resume> findById(ResumeId id);

  List<Resume> findByCandidateId(CandidateId candidateId);

  Optional<Resume> findLatestByCandidateId(CandidateId candidateId);

  Integer getNextVersion(CandidateId candidateId);
}
