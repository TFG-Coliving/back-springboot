package com.example.backspringboot.service;

import com.example.backspringboot.model.ImageData;
import com.example.backspringboot.repository.ImageDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class ImageDataService {

    @Autowired
    private ImageDataRepository repository;
    private final String FOLDER_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" +
            File.separator + "resources" + File.separator + "static" + File.separator + "images" + File.separator;

    public void uploadImage(MultipartFile file) throws IOException {
        StringBuilder filename = new StringBuilder(Objects.requireNonNull(file.getOriginalFilename()).replaceAll(" ", "_"));
        String filePath = FOLDER_PATH + filename;
        File tempFile = new File(filePath);
        while (tempFile.exists()) {
            filename.insert(0, "1");
            filePath = FOLDER_PATH + filename;
            tempFile = new File(filePath);
        }


        ImageData imageData = ImageData.builder()
                .name(filename.toString())
                .type(file.getContentType())
                .path(filePath)
                .build();

        repository.save(imageData);

        file.transferTo(new File(filePath));
    }

    public void deleteImage(Long id) {
        Optional<ImageData> optionalImageData = repository.findById(id);
        if(optionalImageData.isEmpty()) {
            return;
        }
        String path = optionalImageData.get().getPath();
        File image = new File(path);
        image.delete();
        repository.deleteById(id);
    }
}
