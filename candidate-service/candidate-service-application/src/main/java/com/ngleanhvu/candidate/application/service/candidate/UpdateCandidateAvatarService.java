package com.ngleanhvu.candidate.application.service.candidate;

import com.ngleanhvu.candidate.application.port.input.candidate.UpdateCandidateAvatarUseCase;
import com.ngleanhvu.candidate.application.port.output.candidate.CandidateRepository;
import com.ngleanhvu.candidate.domain.candidate.Candidate;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.common.constant.BucketConstant;
import com.ngleanhvu.common.exception.FileStorageException;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.common.storage.FileExtensionUtil;
import com.ngleanhvu.common.storage.FileStorage;
import com.ngleanhvu.common.storage.MinioObjectKey;
import com.ngleanhvu.common.util.ImageUtil;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public record UpdateCandidateAvatarService(
    CandidateRepository candidateRepository, FileStorage fileStorage)
    implements UpdateCandidateAvatarUseCase {
  public void execute(CandidateId candidateId, MultipartFile file) {

    Candidate candidate =
        candidateRepository
            .findById(candidateId)
            .orElseThrow(() -> new ResourceNotFoundException("Candidate not found"));

    ImageUtil.validateImage(file);

    String extension = FileExtensionUtil.getExtension(file);

    String newAvatarKey =
        MinioObjectKey.key(candidateId.value(), BucketConstant.CANDIDATES_AVATAR, extension);

    String oldAvatarKey = candidate.getProfile().avatarKey();

    try {
      fileStorage.upload(
          newAvatarKey, file.getInputStream(), file.getSize(), file.getContentType());

      candidate.updateAvatar(newAvatarKey);
      candidateRepository.save(candidate);
      if (oldAvatarKey != null) {
        fileStorage.delete(oldAvatarKey);
      }

    } catch (IOException e) {
      fileStorage.delete(newAvatarKey);
      throw new FileStorageException("Failed to upload candidate avatar");
    }
  }
}
