package com.ngleanhvu.application.infra.grpc;

import com.ngleanhvu.application.application.port.output.job.JobGateway;
import com.ngleanhvu.grpc.job.v1.ExistsJobByIdRequest;
import com.ngleanhvu.grpc.job.v1.ExistsJobByIdResponse;
import com.ngleanhvu.grpc.job.v1.JobServiceGrpc;
import io.grpc.ManagedChannel;
import org.springframework.stereotype.Component;

@Component
public class JobGrpcClient implements JobGateway {

    private final JobServiceGrpc.JobServiceBlockingStub stub;

    public JobGrpcClient(ManagedChannel jobManagedChannel) {
        this.stub = JobServiceGrpc.newBlockingStub(jobManagedChannel);
    }

    @Override
    public boolean existsJobById(String jobId) {
        ExistsJobByIdRequest request = ExistsJobByIdRequest.newBuilder()
                .setJobId(jobId)
                .build();
        ExistsJobByIdResponse response = stub.existsJobById(request);
        return response.getExists();
    }
}
