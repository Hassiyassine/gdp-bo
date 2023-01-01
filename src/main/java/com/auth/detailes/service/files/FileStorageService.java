package com.auth.detailes.service.files;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


/**
 * @author 	: Yassin OUICHOU | Ouichou.IT@gmail.com
 */
@Service
@Slf4j
public class FileStorageService {



    /**
     * @param fileLocation
     * @return
     */
    public Resource loadFileAsResource(Path fileLocation) {
        try {
            Resource resource = new UrlResource(fileLocation.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + fileLocation.getFileName());
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("File not found " + fileLocation.getFileName(), ex);
        }
    }

}