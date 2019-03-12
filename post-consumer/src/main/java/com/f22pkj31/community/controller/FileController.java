package com.f22pkj31.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileController {



    @RequestMapping("uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file == null){
            return null;
        }
        String name = file.getOriginalFilename();
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        File newFile = new File("C:\\Users\\11362\\file"+name);
        file.transferTo(newFile);
        return "";
    }
}
