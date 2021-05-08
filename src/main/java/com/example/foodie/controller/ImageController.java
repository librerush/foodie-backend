package com.example.foodie.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/image")
public class ImageController {
    private final static String IMAGE_STORAGE_PATH = "data/image/";

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
            e.printStackTrace();
        }
        return bytes;
    }
}
