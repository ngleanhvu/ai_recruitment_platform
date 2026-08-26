package com.ngleanhvu.common.response;

import java.util.List;

public record PaginationResponse<T>(
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last,
        boolean hasNext,
        boolean hasPrevious
) {
    public static <T> PaginationResponse<T> of(
            List<T> content,
            int page,
            int size,
            long totalElements
    ) {
        int totalPages = size > 0 ? (int) Math.ceil((double) totalElements / size) : 0;
        boolean hasPrevious = page > 0;
        boolean hasNext = page + 1 < totalPages;

        return new PaginationResponse<>(
                content == null ? List.of() : List.copyOf(content),
                page,
                size,
                totalElements,
                totalPages,
                !hasPrevious,
                !hasNext,
                hasNext,
                hasPrevious
        );
    }
}
