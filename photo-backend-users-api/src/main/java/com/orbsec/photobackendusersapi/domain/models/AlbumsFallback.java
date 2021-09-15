package com.orbsec.photobackendusersapi.domain.models;

import com.orbsec.photobackendusersapi.services.AlbumsServiceClient;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AlbumsFallback implements AlbumsServiceClient {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getStatus() {
        return "Albums Microservices is not available. This is the fallback client";
    }

    @Override
    public List<AlbumResponseDto> findAllAlbums() {
        logger.warn("This is the fallback method. Albums Microservice is not available");
        return new ArrayList<>();
    }

    @Override
    public AlbumResponseDto findAlbumById(String albumId) {
        logger.warn("This is the fallback method. Albums Microservice is not available");
        var fallbackResponse = new AlbumResponseDto();
        fallbackResponse.setDescription("This is a fallback object. Albums Microservice is not available");

        return fallbackResponse;
    }
}
