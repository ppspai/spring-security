package com.example.test.common;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileNode {
    private String fileName;
    private String type;
    private String parentPath;
    private String path;
    private List<FileNode> files;

}
