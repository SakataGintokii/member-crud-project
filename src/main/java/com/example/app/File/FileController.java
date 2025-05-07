package com.example.app.File;



import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file")
public class FileController {

    //上傳檔案
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        File uploadFiles  = new File("uploads");

        if(!uploadFiles.exists()) {
            uploadFiles.mkdirs();
        }

        //儲存資料並輸出結果
        try{
            String fileName = file.getOriginalFilename();
            Path path = Paths.get(uploadFiles.getAbsolutePath(), fileName);
            file.transferTo(path.toFile());
            return "上傳成功 ："+fileName;
        }catch (Exception e){
            e.printStackTrace();
            return "上傳失敗 :" + e.getMessage();
        }

    }

    //下載檔案
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName){

        File file = new File("uploads",fileName);

        if(!file.exists()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Resource resource = new FileSystemResource(file);

        HttpHeaders httpHeaders = new HttpHeaders();

        //下載附件
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");

        //顯示檔案內容，設置標頭以及屬性
        return ResponseEntity.ok().headers(httpHeaders).contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);

    }


}
