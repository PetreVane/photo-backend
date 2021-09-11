package com.orbsec.photobackend.albumsapi.domain.models;

import lombok.Data;

@Data
public class AlbumResponseModel {
    private int id;
    private String albumId;
    private String userId;
    private String name;
    private String description;
}
