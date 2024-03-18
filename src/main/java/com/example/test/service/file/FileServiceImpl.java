package com.example.test.service.file;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.test.common.FileNode;

@Service
public class FileServiceImpl implements FileService{

    @Override
    public Map<String, Object> searchFile(String path) {
        Map<String, Object> result = new LinkedHashMap<String, Object>();

        File file = new File(path);
        FileNode fileNode = new FileNode();
        String fileName = "".equals(file.getName()) ? "D드라이브" : file.getName();

        fileNode.setFileName(fileName);
        fileNode.setPath(file.getAbsolutePath());
        fileNode.setParentPath(file.getParentFile() != null ? file.getParentFile().getPath() : null);
        if(file.isDirectory()) {
            fileNode.setType("folder");
        } else if (file.isFile()) {
            fileNode.setType("file");
        }

        List<FileNode> childFileNodeList = new LinkedList<FileNode>();

        File[] childFiles = file.listFiles();

        for(File childFile : childFiles) {
            FileNode childFileNode = new FileNode();
            childFileNode.setFileName(childFile.getName());
            childFileNode.setPath(childFile.getAbsolutePath());
            if(childFile.isDirectory()) {
                childFileNode.setType("folder");
            } else if(childFile.isFile()) {
                childFileNode.setType("file");
            }
            childFileNodeList.add(childFileNode);
        }

        childFileNodeList = childFileNodeList.stream().sorted((a, b) -> {
            if(a.getType().equals("file") && b.getType().equals("folder")) {
                return 1;
            } else if(a.getType().equals("folder") && b.getType().equals("file")) {
                return -1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList());


        fileNode.setFiles(childFileNodeList);

        result.put("file", fileNode);

        return result;
    }
    
    public List<FileNode> getChildFile(File file) {
        List<FileNode> result = new LinkedList<FileNode>();

        File[] fileList = file.listFiles();

        for(File childFile : fileList) {
            FileNode childFileNode = new FileNode();
            childFileNode.setFileName(childFile.getName());
            if(childFile.isDirectory()) {
                childFileNode.setType("folder");
                childFileNode.setFiles(getChildFile(new File(childFile.getPath())));
            } else if(childFile.isFile()) {
                childFileNode.setType("file");
            }
            result.add(childFileNode);
        }

        return result;
    }
}
