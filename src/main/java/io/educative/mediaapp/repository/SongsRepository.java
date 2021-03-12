package io.educative.mediaapp.repository;

import io.educative.mediaapp.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface SongsRepository extends JpaRepository<Song, BigInteger> {
}
