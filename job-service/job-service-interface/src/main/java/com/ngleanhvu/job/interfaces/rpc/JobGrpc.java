package com.ngleanhvu.job.interfaces.rpc;

import com.ngleanhvu.grpc.job.v1.ExistsJobByIdRequest;
import com.ngleanhvu.grpc.job.v1.ExistsJobByIdResponse;
import com.ngleanhvu.grpc.job.v1.JobServiceGrpc;
import com.ngleanhvu.job.application.port.output.JobRepository;
import com.ngleanhvu.job.domain.model.job.JobId;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class JobGrpc extends JobServiceGrpc.JobServiceImplBase {
    private final JobRepository jobRepository;

    @Override
    public void existsJobById(ExistsJobByIdRequest request, StreamObserver<ExistsJobByIdResponse> responseObserver) {
        JobId jobId = new JobId(request.getJobId());
        boolean exists = jobRepository.existsById(jobId);
        ExistsJobByIdResponse response = ExistsJobByIdResponse.newBuilder()
                .setExists(exists)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
