package com.auth.detailes.service.common;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface FileStorageService {

    String uplode(MultipartFile file, String dir);

    String uplodeCustomName(MultipartFile file, String dir,String filename);

    Resource load(Long id);

    Optional<String> getExtension(String filename);

    Resource loadFile(String dirFile);
}
