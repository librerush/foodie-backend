package com.example.foodie.service;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;

@Service
public class ImageService {
    public long currentUnixTime() {
        return System.currentTimeMillis() / 1000L;
    }

    public String imageStoragePath() {
        return "storage/image/";
    }

    public byte[] getImage(final String path) {
        byte[] bytes;
        String dataPath = String.format("%s%s", imageStoragePath(), path);
        try {
            bytes = FileUtils
                    .readFileToByteArray(new File(dataPath));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No image: " + path);
        }
        return bytes;
    }

    public String uploadImage(MultipartFile multipartFile) {
        if (multipartFile == null
                || multipartFile.isEmpty()
                || multipartFile.getSize() > 10000000L) /* more than 10 MB */ {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        String path = null;
        if (!multipartFile.isEmpty()) {
            path = String.format("%s%d-%s",
                    imageStoragePath(), currentUnixTime(), multipartFile.getOriginalFilename());
            try {
                multipartFile.transferTo(new File(path).getAbsoluteFile());
            } catch (IOException e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Got an exception when uploading a file: " + multipartFile.getOriginalFilename());
            }
        }
        return "Uploaded a file: " + path;
    }
}
