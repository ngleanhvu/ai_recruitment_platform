package com.ngleanhvu.application.infra.grpc;

import com.ngleanhvu.application.port.output.candidate.CandidateGateway;
import com.ngleanhvu.grpc.candidate.v1.CandidateServiceGrpc;
import com.ngleanhvu.grpc.candidate.v1.ExistsCandidateByIdRequest;
import com.ngleanhvu.grpc.candidate.v1.ExistsCandidateByIdResponse;
import io.grpc.ManagedChannel;
import org.springframework.stereotype.Component;

@Component
public class CandidateGrpcClient implements CandidateGateway {

    private final CandidateServiceGrpc.CandidateServiceBlockingStub stub;

    public CandidateGrpcClient(
            ManagedChannel candidateManagedChannel
    ) {
        this.stub =
                CandidateServiceGrpc
                        .newBlockingStub(candidateManagedChannel);
    }

    @Override
    public boolean existsCandidateById(String candidateId) {
        ExistsCandidateByIdRequest request =
                ExistsCandidateByIdRequest.newBuilder()
                        .setCandidateId(candidateId)
                        .build();

        ExistsCandidateByIdResponse response =
                stub.existsCandidateById(request);

        return response.getExists();
    }
}
