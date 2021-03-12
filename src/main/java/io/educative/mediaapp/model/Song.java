package io.educative.mediaapp.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Data
@Entity
@Table(name = "song")
@NamedNativeQuery(name = "songsByPlaylistId",
        query = "select id, name, playlist_id, cover_url, created_on from song s " +
                "where s.playlist_id = ?", resultClass = Song.class)
public class Song {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private BigInteger id;

    @Column(name = "playlist_id")
    @JsonProperty("playlist_id")
    private BigInteger playlistId;

    private String name;

    @Column(name = "cover_url")
    @JsonProperty("cover_url")
    private String coverUrl;

    @Column(name = "created_on")
    @JsonProperty("created_on")
    private Date createdOn;
}
