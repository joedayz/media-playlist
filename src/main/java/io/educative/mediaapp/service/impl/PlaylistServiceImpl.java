package io.educative.mediaapp.service.impl;

import io.educative.mediaapp.model.Playlist;
import io.educative.mediaapp.repository.PlaylistRepository;
import io.educative.mediaapp.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("playlistService")
public class PlaylistServiceImpl implements PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepo;



    @Override
    public Iterable<Playlist> getAllPlaylists() {
        return playlistRepo.findAll();
    }
}
