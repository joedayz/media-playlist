package io.educative.mediaapp.exception;

import java.math.BigInteger;

public class PlaylistNotFoundException extends RuntimeException {
    public PlaylistNotFoundException(final BigInteger id) {
        super(String.format("playlist with id '%s' not found", id));
    }
}
