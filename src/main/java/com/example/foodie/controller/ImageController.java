package com.example.foodie.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/image")
public class ImageController {
    private final static String IMAGE_STORAGE_PATH = "storage/image/";

    @GetMapping(value = "/{path}",
            produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @Operation(summary = "Get an image by 'path'")
    public @ResponseBody byte[] getImageAsResource(@PathVariable String path) {
        byte[] bytes = new byte[0];
        String dataPath = IMAGE_STORAGE_PATH + path;
        try {
            bytes = FileUtils
                    .readFileToByteArray(new File(dataPath));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No image: " + path);
        }
        return bytes;
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Upload a file to storage")
    public @ResponseBody String uploadImage(@RequestBody MultipartFile multipartFile) {
        if (multipartFile == null
                || multipartFile.isEmpty()
                || multipartFile.getSize() > 10000000L) /* more than 10 MB */ {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (!multipartFile.isEmpty()) {
            String path = IMAGE_STORAGE_PATH + multipartFile.getOriginalFilename();
            try {
                multipartFile.transferTo(new File(path).getAbsoluteFile());
            } catch (IOException e) {
                e.printStackTrace();
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Got an exception when uploading a file: " + multipartFile.getOriginalFilename());
            }
        }
        return "Uploaded a file: " + multipartFile.getOriginalFilename();
    }
}
