package com.f22pkj31.community.controller;

import com.f22pkj31.community.entity.UploadResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Value("${resources_path}")
    private String filePath;

    @RequestMapping("uploadFile")
    public UploadResp uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        UploadResp uploadResp = new UploadResp();
        if (file == null) {
            uploadResp.setError("1");
            return uploadResp;
        }
        String name = file.getOriginalFilename();
        if (StringUtils.isEmpty(name)) {
            uploadResp.setError("1");
            return uploadResp;
        }
        if (new File(filePath + name).exists()) {
            uploadResp.setError("0");
            uploadResp.setUrl(name);
            return uploadResp;
        }
        name = System.currentTimeMillis() + name.substring(name.lastIndexOf("."));
        log.debug("name={}", name);
        File newFile = new File(filePath + name);
        file.transferTo(newFile);
        uploadResp.setError("0");
        uploadResp.setUrl("http://127.0.0.1:8010/" + name);


        return uploadResp;
    }

}
