package com.ngleanhvu.common.storage;

import com.ngleanhvu.common.exception.ValidationException;
import com.ngleanhvu.common.util.ValidationUtil;
import org.springframework.web.multipart.MultipartFile;

public final class FileExtensionUtil {

    private FileExtensionUtil() {
    }

    public static String getExtension(MultipartFile file) {

        String filename = file.getOriginalFilename();

        if (ValidationUtil.isEmpty(filename)) {
            throw new ValidationException(
                    "File name is required"
            );
        }

        int index = filename.lastIndexOf('.');

        if (index < 0) {
            throw new ValidationException(
                    "File extension is required"
            );
        }

        return filename
                .substring(index + 1)
                .toLowerCase();
    }

    public static String getFilename(MultipartFile file) {

        String filename = file.getOriginalFilename();

        if (ValidationUtil.isEmpty(filename)) {
            throw new ValidationException(
                    "File name is required"
            );
        }

        int index = filename.lastIndexOf('.');

        if (index < 0) {
            return filename;
        }

        return filename.substring(0, index);
    }
}
