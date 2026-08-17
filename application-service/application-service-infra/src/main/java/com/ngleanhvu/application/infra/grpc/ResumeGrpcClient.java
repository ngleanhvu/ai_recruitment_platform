package com.ngleanhvu.application.infra.grpc;

import com.ngleanhvu.application.application.port.output.candidate.ResumeGateway;
import com.ngleanhvu.grpc.resume.v1.ExistsResumeByIdAndCandidateIdRequest;
import com.ngleanhvu.grpc.resume.v1.ExistsResumeByIdAndCandidateIdResponse;
import com.ngleanhvu.grpc.resume.v1.ResumeServiceGrpc;
import io.grpc.ManagedChannel;
import org.springframework.stereotype.Component;

@Component
public class ResumeGrpcClient implements ResumeGateway {

    private final ResumeServiceGrpc.ResumeServiceBlockingStub stub;

    public ResumeGrpcClient(ManagedChannel resumeManagedChannel) {
        this.stub = ResumeServiceGrpc.newBlockingStub(resumeManagedChannel);
    }

    @Override
    public boolean existsResumeByIdAndCandidateId(String resumeId, String candidateId) {
        ExistsResumeByIdAndCandidateIdRequest request = ExistsResumeByIdAndCandidateIdRequest.newBuilder()
                .setCandidateId(candidateId)
                .setResumeId(resumeId)
                .build();

        ExistsResumeByIdAndCandidateIdResponse response = stub.existsResumeByIdAndCandidateId(request);

        return response.getExists();
    }
}
