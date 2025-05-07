package com.example.app.client;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;

public class UploadFileClient {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        // 設定檔案路徑
        File file = new File("/Users/sakatagintoki/Documents/picture/cat.jpg");
        FileSystemResource fileResource = new FileSystemResource(file);

        // 準備form-data資料
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileResource);  // key 為後端接收的參數名
        body.add("description", "這是檔案描述");

        // 設定HTTP標頭為multipart/form-data
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // 將資料和標頭包裝成HttpEntity物件
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // 發送post請求並接收回應
        String serverUrl = "http://localhost:8080/file/upload";
        ResponseEntity<String> response = restTemplate.postForEntity(serverUrl, requestEntity, String.class);

        System.out.println("伺服器回應：" + response.getBody());
    }
}