package com.ngleanhvu.common.storage;

import java.io.InputStream;

public interface FileStorage {
    String upload(
            String objectName,
            InputStream inputStream,
            long size,
            String contentType
    );

    void delete(String objectName);

    String getUrl(String objectName);
}
