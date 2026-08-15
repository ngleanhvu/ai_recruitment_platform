package com.ngleanhvu.candidate.infra.exception;

import com.ngleanhvu.common.exception.BaseException;

public class MinioStorageException extends BaseException {
  public MinioStorageException(String msg) {
    super(msg);
  }
}
