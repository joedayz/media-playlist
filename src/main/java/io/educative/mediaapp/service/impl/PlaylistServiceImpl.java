package io.educative.mediaapp.service.impl;

import io.educative.mediaapp.exception.PlaylistNotFoundException;
import io.educative.mediaapp.exception.SongNotFoundException;
import io.educative.mediaapp.model.Playlist;
import io.educative.mediaapp.model.Song;
import io.educative.mediaapp.repository.PlaylistRepository;
import io.educative.mediaapp.repository.SongsRepository;
import io.educative.mediaapp.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

@Service("playlistService")
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepo;

    @Autowired
    private SongsRepository songRepo;

    @Override
    public Iterable<Playlist> getAllPlaylists() {
        return playlistRepo.findAll();
    }

    @Override
    public Playlist getPlaylistById(BigInteger playlistId) {
        return getPlaylist(playlistId);
    }

    @Override
    public Optional<Playlist> createPlaylist(String name) {
        Playlist playlist = new Playlist();
        playlist.setName(name);
        playlist.setCreatedOn(new Date());
        return Optional.of(playlistRepo.save(playlist));
    }

    @Override
    public void deletePlaylist(BigInteger playlistId) {
        Playlist playlist = getPlaylist(playlistId);
        playlist.setId(playlistId);
        playlistRepo.delete(playlist);
    }

    @Override
    public Iterable<Song> getSongs(BigInteger playlistId) {
        if(playlistId==null){
            return songRepo.findAll();
        }
        Playlist playlist = getPlaylist(playlistId);
        return playlistRepo.getSongs(playlist.getId());
    }

    @Override
    public Song addSong(BigInteger playlistId, Song song) {
        Playlist playlist = getPlaylist(playlistId);
        song.setPlaylistId(playlist.getId());
        song.setCreatedOn(new Date());
        return songRepo.save(song);
    }

    @Override
    public void deleteSong(BigInteger playlistId, BigInteger songId) {
        Song song = getSong(songId);
        songRepo.delete(playlistId, song.getId());
    }

    @Override
    public boolean moveSong(BigInteger songId, BigInteger toPlaylistId) {
        Song song = getSong(songId);
        Playlist playlist = getPlaylist(toPlaylistId);
        return 1 == songRepo.updatePlaylist(song.getId(), playlist.getId());
    }

    private Playlist getPlaylist(final BigInteger playlistId){
        return playlistRepo.findById(playlistId)
                .orElseThrow(()-> new PlaylistNotFoundException(playlistId));
    }

    private Song getSong(final BigInteger songId) {
        return songRepo.findById(songId).orElseThrow(() -> new SongNotFoundException(songId));
    }
}
