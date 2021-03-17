package io.educative.mediaapp.exception;

import java.math.BigInteger;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(BigInteger id) {
        super(String.format("song with id '%s' not found", id));
    }
}
