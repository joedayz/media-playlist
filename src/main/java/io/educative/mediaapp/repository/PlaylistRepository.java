package io.educative.mediaapp.repository;

import io.educative.mediaapp.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface PlaylistRepository extends JpaRepository<Playlist, BigInteger> {

}
