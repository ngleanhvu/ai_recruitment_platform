package com.ngleanhvu.common.storage;

import java.util.UUID;

public final class MinioObjectKey {

    private MinioObjectKey() {
    }

    public static String key(
            String source,
            String id,
            String type,
            String extension
    ) {
        return "%s/%s/%s/%s.%s".formatted(
                source,
                id,
                type,
                UUID.randomUUID(),
                extension
        );
    }
}