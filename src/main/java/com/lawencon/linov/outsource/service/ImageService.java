package com.lawencon.linov.outsource.service;

import com.lawencon.linov.outsource.model.Image;

import java.util.Optional;

public interface ImageService {

    Image uploadImage(Image image);
    Optional<Image> getImageById(Long id);
}
