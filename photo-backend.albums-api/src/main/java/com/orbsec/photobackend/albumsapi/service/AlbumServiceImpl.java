package com.orbsec.photobackend.albumsapi.service;

import com.orbsec.photobackend.albumsapi.domain.models.AlbumEntity;
import com.orbsec.photobackend.albumsapi.repository.AlbumsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private AlbumsRepository albumsRepository;

    @Autowired
    public AlbumServiceImpl(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
        addAlbums("This is the ID of the Album");
    }

    @Override
    public Optional<AlbumEntity> getAlbumsById(String albumId) {
        return albumsRepository.findById(albumId);
    }

    private void addAlbums(String userId) {
        AlbumEntity albumEntity = new AlbumEntity();
        albumEntity.setUserId(userId);
        albumEntity.setAlbumId("album1Id");
        albumEntity.setDescription("album 1 description");
        albumEntity.setId("1L");
        albumEntity.setName("album 1 name");

        AlbumEntity albumEntity2 = new AlbumEntity();
        albumEntity2.setUserId(userId);
        albumEntity2.setAlbumId("album2Id");
        albumEntity2.setDescription("album 2 description");
        albumEntity2.setId("2L");
        albumEntity2.setName("album 2 name");

        albumsRepository.save(albumEntity);
        albumsRepository.save(albumEntity2);
    }

    @Override
    public List<AlbumEntity> getAllAlbums() {
        return (List<AlbumEntity>) albumsRepository.findAll();
    }
}
