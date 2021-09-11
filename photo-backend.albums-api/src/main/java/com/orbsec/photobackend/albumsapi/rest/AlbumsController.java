package com.orbsec.photobackend.albumsapi.rest;

import com.google.common.reflect.TypeToken;
import com.orbsec.photobackend.albumsapi.domain.models.AlbumEntity;
import com.orbsec.photobackend.albumsapi.domain.models.AlbumResponseModel;
import com.orbsec.photobackend.albumsapi.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private AlbumService albumService;
    private Environment environment;

    @Autowired
    public AlbumsController(AlbumService albumService, Environment environment) {
        this.albumService = albumService;
        this.environment = environment;
    }

    @GetMapping("/status")
    public String getStatus() {
        var secretToken = environment.getProperty("token.secret");
        var portNumber = environment.getProperty("local.server.port");
        return "Albums microservice is up and running on port " + portNumber + " and secret token " + secretToken;
    }

    @GetMapping(path ="/{albumId}", produces = {"application/json", "application/xml"} )
    public List<AlbumEntity> getAlbumById(@PathVariable String albumId) {
        List<AlbumEntity> albums = new ArrayList<>();
        var album = albumService.getAlbumsById(albumId);
        album.ifPresent(albums::add);
        Type listType = new TypeToken<List<AlbumResponseModel>>(){}.getType();
        new ModelMapper().map(albums, listType);
        logger.info("Returning " + albums.size() + " albums");

        return albums;
    }

    @GetMapping(produces = {"application/json", "application/xml"})
    public List<AlbumEntity> getAllAlbums() {
        return albumService.getAllAlbums();
    }
}
