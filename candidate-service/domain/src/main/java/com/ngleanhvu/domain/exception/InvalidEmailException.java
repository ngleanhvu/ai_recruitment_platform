package com.ngleanhvu.domain.exception;

import com.ngleanhvu.shared.exception.BaseException;

public class InvalidEmailException extends BaseException {
    public InvalidEmailException(String msg) {
        super(msg);
    }
}
