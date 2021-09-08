package com.orbsec.photobackend.albumsapi.repository;

import com.orbsec.photobackend.albumsapi.domain.models.AlbumEntity;
import org.springframework.data.repository.CrudRepository;

public interface AlbumsRepository extends CrudRepository<AlbumEntity, String> {

}
