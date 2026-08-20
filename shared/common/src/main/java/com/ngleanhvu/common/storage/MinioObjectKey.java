package com.ngleanhvu.common.storage;

import java.util.UUID;

public final class MinioObjectKey {

    private MinioObjectKey() {
    }

    public static String key(
            String id,
            String type,
            String extension
    ) {
        return "%s/%s/%s.%s".formatted(
                id,
                type,
                UUID.randomUUID(),
                extension
        );
    }
}