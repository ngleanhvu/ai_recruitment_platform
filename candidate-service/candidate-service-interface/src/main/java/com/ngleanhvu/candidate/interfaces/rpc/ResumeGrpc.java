package com.ngleanhvu.candidate.interfaces.rpc;

import com.ngleanhvu.candidate.application.port.output.resume.ResumeRepository;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.candidate.domain.resume.ResumeId;
import com.ngleanhvu.grpc.resume.v1.ExistsResumeByIdAndCandidateIdRequest;
import com.ngleanhvu.grpc.resume.v1.ExistsResumeByIdAndCandidateIdResponse;
import com.ngleanhvu.grpc.resume.v1.ResumeServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class ResumeGrpc extends ResumeServiceGrpc.ResumeServiceImplBase {
    private final ResumeRepository resumeRepository;

    @Override
    public void existsResumeByIdAndCandidateId(ExistsResumeByIdAndCandidateIdRequest request, StreamObserver<ExistsResumeByIdAndCandidateIdResponse> responseObserver) {
        ResumeId resumeId = new ResumeId(request.getResumeId());
        CandidateId candidateId = new CandidateId(request.getCandidateId());

        boolean existsResume = resumeRepository.existsByIdAndCandidateId(resumeId, candidateId);

        ExistsResumeByIdAndCandidateIdResponse response = ExistsResumeByIdAndCandidateIdResponse.newBuilder()
                .setExists(existsResume)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
