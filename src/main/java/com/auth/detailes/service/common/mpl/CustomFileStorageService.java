package com.auth.detailes.service.common.mpl;

import com.auth.detailes.business.entites.common.Document;
import com.auth.detailes.business.repositories.common.DocumentRepository;
import com.auth.detailes.service.common.FileStorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@Slf4j
public class CustomFileStorageService implements FileStorageService {

    @Value("${drowabel.lib.images}")
    private String path;

    @Autowired
    private   DocumentRepository repository;

    @Override
    public String uplode(MultipartFile file, String dir) {
        try {
            if (!dir.endsWith("/") && !file.getOriginalFilename().startsWith("/")) {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(this.path + dir +"/"+ file.getOriginalFilename());
                Files.createDirectories(Paths.get(this.path +"/"+dir));
                Files.write(path, bytes);
                System.out.println("> File has been uploaded successfully...");
                return path.toString();
            }
            return null;
        } catch (IOException e) {
            log.error("ERROR  TO UPLOAD FILE ERROR NAME : {}",e.getMessage());
            return null;
        }
    }

    @Override
    public String uplodeCustomName(MultipartFile file, String dir,String filename) {
        try {
            if (!dir.endsWith("/") && !filename.startsWith("/")) {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(this.path + dir +"/"+ filename);
                Files.createDirectories(Paths.get(this.path +"/"+dir));
                Files.write(path, bytes);
                System.out.println("> File has been uploaded successfully...");
                return path.toString();
            }
            return null;
        } catch (IOException e) {
            log.error("ERROR  TO UPLOAD FILE ERROR NAME : {}",e.getMessage());
            return null;
        }
    }

    @Override
    public Resource load(Long id) {
        try {
            Document document = this.repository.findById(id).get();
            if (document!=null){
                Path root1 = Paths.get(document.getPath());
                //Path file = root1.resolve(fileName);
                Resource resource = new UrlResource(root1.toUri());
                if (resource.exists() || resource.isReadable()) {
                    System.err.println("if");
                    return resource;
                } else {
                    System.err.println("else");
                    log.error("ERROR  TO UPLOAD FILE ERROR NAME : {}",path);
                    throw new RuntimeException("Could not read the file!");
                }
            }
            throw new RuntimeException("Could File not find!");

        } catch (MalformedURLException e) {
            System.err.println("catch");
            log.error("Could not read the file path is : {}",e.getMessage());
            throw new RuntimeException("Error: " + e.getMessage());
        }

    }


    @Override
    public Resource loadFile(String dirFile) {
        try {
            Path file = Paths.get(dirFile);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("FAIL!");
        }
    }

    @Override
    public Optional<String> getExtension(String filename) {
        return Optional.ofNullable(filename).filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public Boolean deleteFile(String fileName) {
        try {
            Path file = Paths.get(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists()) {
                return resource.getFile().delete();
            } else {
                throw new RuntimeException("File not found " + fileName);
            }
        }  catch (IOException ex) {
            throw new RuntimeException("File not found " + fileName, ex);
        }
    }

}
