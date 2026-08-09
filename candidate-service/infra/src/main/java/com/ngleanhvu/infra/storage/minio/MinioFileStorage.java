package com.ngleanhvu.infra.storage.minio;

import com.ngleanhvu.infra.config.MinioProperties;
import com.ngleanhvu.infra.exception.MinioStorageException;
import com.ngleanhvu.shared.storage.FileStorage;
import io.minio.*;
import io.minio.http.Method;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MinioFileStorage implements FileStorage {

  private final MinioClient minioClient;
  private final MinioProperties properties;

  @Override
  public String upload(String objectName, InputStream inputStream, long size, String contentType) {

    try {
      ensureBucketExists();

      PutObjectArgs args =
          PutObjectArgs.builder().bucket(properties.bucket()).object(objectName).stream(
                  inputStream, size, -1)
              .contentType(contentType)
              .build();

      minioClient.putObject(args);

      return objectName;

    } catch (Exception e) {
      throw new MinioStorageException("Failed to upload file: " + objectName);
    }
  }

  @Override
  public void delete(String objectName) {

    try {

      RemoveObjectArgs args =
          RemoveObjectArgs.builder().bucket(properties.bucket()).object(objectName).build();

      minioClient.removeObject(args);

    } catch (Exception e) {
      throw new MinioStorageException("Failed to delete file: " + objectName);
    }
  }

  @Override
  public String getUrl(String objectName) {

    try {

      GetPresignedObjectUrlArgs args =
          GetPresignedObjectUrlArgs.builder()
              .method(Method.GET)
              .bucket(properties.bucket())
              .object(objectName)
              .expiry(15, TimeUnit.MINUTES)
              .build();

      return minioClient.getPresignedObjectUrl(args);

    } catch (Exception e) {
      throw new MinioStorageException("Failed to generate URL: " + objectName);
    }
  }

  private void ensureBucketExists() {

    try {

      boolean exists =
          minioClient.bucketExists(BucketExistsArgs.builder().bucket(properties.bucket()).build());

      if (!exists) {
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(properties.bucket()).build());
      }

    } catch (Exception e) {
      throw new MinioStorageException("Failed to initialize MinIO bucket");
    }
  }
}
