package com.ngleanhvu.common.util;

import com.ngleanhvu.common.exception.ValidationException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public final class ImageUtil {
    private static final Set<String> IMAGE_EXTENSION = Set.of(
            "image/jpeg",
            "image/png",
            "image/webp"
    );

    public static void validateImage(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new ValidationException(
                    "Avatar file is required"
            );
        }

        if (!IMAGE_EXTENSION.contains(file.getContentType())) {
            throw new ValidationException(
                    "Only JPEG, PNG and WebP images are allowed"
            );
        }

        long maxSize = 5 * 1024 * 1024;

        if (file.getSize() > maxSize) {
            throw new ValidationException(
                    "Avatar size must not exceed 5MB"
            );
        }
    }
}
