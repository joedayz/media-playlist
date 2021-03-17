package io.educative.mediaapp.controller.data;

import io.educative.mediaapp.model.Playlist;
import io.educative.mediaapp.model.Song;
import io.educative.mediaapp.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Optional;

@RestController
@RequestMapping("/playlist")
public class PlaylistRestController {

    public PlaylistService service;

    @Qualifier("playlistService")
    @Autowired
    public void setService(PlaylistService service){
        this.service = service;
    }

    @GetMapping("/")
    public String root(){
        return "application is running";
    }
    @GetMapping("/all")
    public Iterable<Playlist> getAllPlaylists(){
        return service.getAllPlaylists();
    }

    @GetMapping("/{id}")
    public Playlist getPlaylistById(final @PathVariable("id") BigInteger playlistId){
        return service.getPlaylistById(playlistId);
    }

    @PostMapping("/{name}")
    public Optional<Playlist> createPlaylist(final @PathVariable String name){
        return service.createPlaylist(name);
    }


    @DeleteMapping("/{id}")
    public void deletePlaylist(final @PathVariable("id")  BigInteger playlistId){
        service.deletePlaylist(playlistId);
    }
    @GetMapping("/{id}/songs")
    public Iterable<Song> getSongsInPlaylist(final @PathVariable("id") BigInteger playlistId){
        return service.getSongs(playlistId);
    }
    @PostMapping("/{id}/add")
    public Song addSongToPlaylist(final @PathVariable("id") BigInteger playlistId,
                                  final @RequestBody Song song){
        return service.addSong(playlistId, song);
    }

    @GetMapping("/songs")
    public Iterable<Song> getAllSongs() {
        return service.getSongs(null);
    }


    @DeleteMapping("/{id}/songs/{song_id}")
    public void deleteFromPlaylist(final @PathVariable("id") BigInteger playlistId,
                                   final @PathVariable("song_id") BigInteger songId) {
        service.deleteSong(playlistId, songId);
    }


    @PutMapping("/songs/{id}/move")
    public boolean moveSongToDifferentPlaylist(@PathVariable("id") BigInteger songId,
                                               @RequestParam("to_playlist") BigInteger toPlaylistId) {
        return service.moveSong(songId, toPlaylistId);
    }

}
