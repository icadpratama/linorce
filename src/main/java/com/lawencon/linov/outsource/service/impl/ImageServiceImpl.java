package com.lawencon.linov.outsource.service.impl;

import com.lawencon.linov.outsource.model.Image;
import com.lawencon.linov.outsource.repository.ImageRepository;
import com.lawencon.linov.outsource.service.ImageService;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public void uploadImage(Image image) {
        imageRepository.save(image);
    }
}
