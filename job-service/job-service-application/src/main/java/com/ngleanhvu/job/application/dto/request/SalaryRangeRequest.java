package com.ngleanhvu.job.application.dto.request;

public record SalaryRangeRequest(
        float min, float max, String currency
) {
}
