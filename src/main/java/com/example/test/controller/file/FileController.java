package com.example.test.controller.file;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test.service.file.FileService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {
    
    final FileService fileService;

    @GetMapping("/file")
    public String fileMain() {
        return "file/file";
    }

    @PostMapping("/fileSystem")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> searchFile(String path) {
        //Map<String, Object> result = new LinkedHashMap<String, Object>();
        try {
            Map<String, Object> file = fileService.searchFile(path);
            return new ResponseEntity<>(file, HttpStatus.OK);
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
