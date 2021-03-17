package io.educative.mediaapp.repository;

import io.educative.mediaapp.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;

public interface SongsRepository extends JpaRepository<Song, BigInteger> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("delete from Song s where s.playlistId = ?1 and s.id = ?2")
    public void delete(BigInteger playlistId, BigInteger songId);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update Song s set s.playlistId = ?2 where s.id = ?1")
    public int updatePlaylist(BigInteger id, BigInteger id1);
}
