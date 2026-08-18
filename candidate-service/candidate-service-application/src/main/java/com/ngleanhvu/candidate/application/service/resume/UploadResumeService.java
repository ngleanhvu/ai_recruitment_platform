package com.ngleanhvu.candidate.application.service.resume;

import com.ngleanhvu.candidate.application.port.input.resume.UploadResumeUseCase;
import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.candidate.application.port.output.resume.ResumeRepository;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.resume.Resume;
import com.ngleanhvu.candidate.domain.resume.ResumeFile;
import com.ngleanhvu.candidate.domain.resume.ResumeId;
import com.ngleanhvu.candidate.domain.resume.enums.ResumeStatus;
import com.ngleanhvu.common.exception.FileStorageException;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.common.storage.FileExtensionUtil;
import com.ngleanhvu.common.storage.FileStorage;
import com.ngleanhvu.common.storage.MinioObjectKey;
import com.ngleanhvu.common.util.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public record UploadResumeService(
        ResumeRepository resumeRepository,
        CandidateRepository candidateRepository,
        FileStorage fileStorage
) implements UploadResumeUseCase {
    @Override
    public void execute(CandidateId candidateId, MultipartFile file) {
        boolean existsCandidate = candidateRepository().existById(candidateId);
        if (!existsCandidate)
            throw new ResourceNotFoundException("Candidate not found");

        ImageUtil.validateImage(file);

        String extension = FileExtensionUtil.getExtension(file);
        String fileName = FileExtensionUtil.getFilename(file);

        ResumeId resumeId = ResumeId.generate();

        String newResumeKey =
                MinioObjectKey.key("resumes", resumeId.value(), "resume", extension);

        try {
            fileStorage.upload(
                    newResumeKey, file.getInputStream(), file.getSize(), file.getContentType());

            int version = resumeRepository.getNextVersion(candidateId);


            ResumeFile resumeFile = new ResumeFile(
                    fileName,
                    newResumeKey
            );

            Resume resume = new Resume(
                    resumeId,
                    candidateId,
                    version,
                    resumeFile,
                    ResumeStatus.ACTIVE
            );

            resumeRepository.save(resume);

        } catch (IOException e) {
            fileStorage.delete(newResumeKey);
            throw new FileStorageException("Failed to upload candidate avatar");
        }
    }
}
