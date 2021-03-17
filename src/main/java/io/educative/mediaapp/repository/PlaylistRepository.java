package io.educative.mediaapp.repository;

import io.educative.mediaapp.model.Playlist;
import io.educative.mediaapp.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.Collection;

public interface PlaylistRepository extends JpaRepository<Playlist, BigInteger> {

    @Query("select s from Song s where s.playlistId = ?1")
    public Collection<Song> getSongs(BigInteger playlistId);
}
