package com.orbsec.photobackend.albumsapi.service;

import com.orbsec.photobackend.albumsapi.domain.models.AlbumEntity;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    Optional<AlbumEntity> getAlbumsById(String albumId);

    List<AlbumEntity> getAllAlbums();
}
