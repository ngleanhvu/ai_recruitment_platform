package com.ngleanhvu.candidate.interfaces.rpc;

import com.ngleanhvu.candidate.application.port.input.candidate.ExistCandidateByIdUseCase;
import com.ngleanhvu.candidate.domain.candidate.CandidateId;
import com.ngleanhvu.grpc.candidate.v1.CandidateServiceGrpc;
import com.ngleanhvu.grpc.candidate.v1.ExistsCandidateByIdRequest;
import com.ngleanhvu.grpc.candidate.v1.ExistsCandidateByIdResponse;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class CandidateGrpc extends CandidateServiceGrpc.CandidateServiceImplBase {
    private final ExistCandidateByIdUseCase existCandidateByIdUseCase;

    @Override
    public void existsCandidateById(ExistsCandidateByIdRequest request, StreamObserver<ExistsCandidateByIdResponse> responseObserver) {
        CandidateId candidateId = new CandidateId(request.getCandidateId());
        boolean exists = existCandidateByIdUseCase.execute(candidateId);
        ExistsCandidateByIdResponse response = ExistsCandidateByIdResponse.newBuilder()
                .setExists(exists)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
