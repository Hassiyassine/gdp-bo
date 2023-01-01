package com.auth.detailes.web.api.secure.download;

import com.auth.detailes.business.entites.auth.User;
import com.auth.detailes.business.entites.common.Document;
import com.auth.detailes.business.repositories.auth.UserRepository;
import com.auth.detailes.business.repositories.common.DocumentRepository;
import com.auth.detailes.service.common.FileStorageService;
import com.auth.detailes.service.referentiel.DocumentService;
import com.auth.detailes.web.common.ApiResponse;
import com.auth.detailes.web.common.CustomRestController;
import com.auth.detailes.web.common.EndPointConstent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;




@RestController
@AllArgsConstructor
@RequestMapping(EndPointConstent.FILE_ENDPOINT_REST)
@Slf4j
public class SecureFileStorageRestController extends CustomRestController {
    @Autowired
    private  FileStorageService service;
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    FileStorageService fileStorageService;
    @Autowired
    UserRepository repository;

    @PostMapping("/upload")
    public String upload(MultipartFile file) throws IOException {

        String path = this.service.uplode(file,"sadi");
       return  path;
    }

    @GetMapping("/download/{id}")
        public ResponseEntity<Resource> serveFile(@PathVariable("id") Long id) throws IOException {

            Resource resource = this.service.load(id);
            MediaType mediaType = getMediaTypeForFileName(resource.getFilename());
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .contentType(mediaType)
                    .contentLength(resource.getFile().length())
                    .body(resource);
        }


    @GetMapping("/show-by-url")
    public ResponseEntity<byte[]>  exportFileByURL(@RequestParam("id") Long id,@RequestParam("target") String target) throws IOException {
        String url = null;
        if(target!=null){
            if(target.equalsIgnoreCase("produit")){
                //Optional<Produit> opt = produitService.find(id);
            //    if(opt.isPresent()){
              //      url = opt.get().getLogo();
                //    return allowFetchingToByte(url);
                //}
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/export-by-url")
    public ResponseEntity<Resource> showFileByURL(@RequestPart("url") String url) throws IOException {

        Resource resource = this.service.loadFile(url);
        MediaType mediaType = getMediaTypeForFileName(resource.getFilename());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .contentType(mediaType)
                .contentLength(resource.getFile().length())
                .body(resource);
    }

     @GetMapping("delete/{id}")
     public ApiResponse<Boolean>  deleteFile(@PathVariable Long id)
     {
         Optional<Document> document= this.documentRepository.findById(id);
         if(document.isPresent()){
             this.documentRepository.delete(document.get());
             return new ApiResponse<>(Boolean.TRUE,null,true,null);
         }
         return new ApiResponse<>(Boolean.FALSE,null,true,null);
     }

    private MediaType getMediaTypeForFileName(String fileName) {
        try {
            return MediaType.parseMediaType(this.servletContext.getMimeType(fileName));
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }


    @GetMapping("/{uniqueCode}")
    public ResponseEntity<byte[]>  exportFileByte(@PathVariable() String uniqueCode,Principal principal) throws IOException {
        Optional<User> opt = this.userService.findAccountByLogin("admin");
        if (opt.isPresent()){
            Optional<Document> optDoc = documentService.findByUniqeCode(uniqueCode);
            if(optDoc.isPresent() &&
                    Collections.disjoint(Collections.singletonList(optDoc.get().getExtension()), Arrays.asList("PNG","png","jpeg","jpg","gif"))){
                Document document = optDoc.get();
                return allowFetchingToByte(document.getPath());
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(path = "/export/{id}", produces = {MediaType.TEXT_HTML_VALUE})
    public void download(
            @PathVariable(value = "id",required = true) Long  id, Principal principal,
            HttpServletResponse response){
        if(principal.getName()!= null){
            return;
        }else{
            try {
                Resource resource = this.service.load(id);
                if(resource.getFilename()!=null) {
                    this.returnFullExcelDocument(response,
                            resource.getInputStream(),
                            resource.getFilename(),this.service.getExtension(resource.getFilename()).get());
                }
            } catch (Exception e) {
                log.error("Download failed error messages : {}",e.getMessage());
            }
        }
    }


    @GetMapping(path = "/download", produces = {MediaType.TEXT_HTML_VALUE})
    public void avatar(Principal principal,
            HttpServletResponse response){
        if(principal.getName()== null){
            return;
        }else{
            try {
             User user= repository.findByUserName(principal.getName()).get();
             if(user.getAvatar()!=null){
                 Resource resource = this.service.loadFile(user.getAvatar());
                 if(resource.getFilename()!=null) {
                     MimetypesFileTypeMap mime = new MimetypesFileTypeMap();
                     response.setHeader("Content-Disposition", "; filename="+resource.getFilename().toLowerCase());
                     response.setContentType(mime.getContentType(resource.getFilename().toLowerCase()));
                     IOUtils.copy(resource.getInputStream(), response.getOutputStream());
                     resource.getInputStream().close();
                     response.flushBuffer();
                 }
             }

            } catch (Exception e) {
                log.error("Download failed error messages : {}",e.getMessage());
            }
        }
    }

    @PostMapping(path = "/logo", produces = {MediaType.TEXT_HTML_VALUE})
    public void logo(@RequestPart("dir") String dir,  Principal principal,
                       HttpServletResponse response){
        if(principal.getName()== null){
            return;
        }else{
            try {
                if(dir!=null){
                    Resource resource = this.service.loadFile(dir);
                    if(resource.getFilename()!=null) {
                        MimetypesFileTypeMap mime = new MimetypesFileTypeMap();
                        response.setHeader("Content-Disposition", "; filename="+resource.getFilename().toLowerCase());
                        response.setContentType(mime.getContentType(resource.getFilename().toLowerCase()));
                        IOUtils.copy(resource.getInputStream(), response.getOutputStream());
                        resource.getInputStream().close();
                        response.flushBuffer();
                    }
                }

            } catch (Exception e) {
                log.error("Download failed error messages : {}",e.getMessage());
            }
        }
    }

    private void returnFullExcelDocument(HttpServletResponse response, InputStream inputStream, String filename, String contentType){
        if(inputStream != null){
            try {
                response.setHeader("Content-Disposition", "attachment; filename="+ filename);
                response.setContentType(contentType);
                IOUtils.copy(inputStream, response.getOutputStream());
                inputStream.close();
                response.flushBuffer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private ResponseEntity<byte[]> allowFetchingToBytes(String url){
        Resource resource = this.service.loadFile(url);
        String contentType = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(resource.getFilename());
        System.err.println(resource.getFilename());
        try {
            return ResponseEntity.ok()
                    .header("Content-Disposition", "inline; filename=" +resource.getFilename())
                    .contentType(MediaType.valueOf(contentType))
                    .body(IOUtils.toByteArray(resource.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                resource.getInputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.notFound().build();
    }

}
