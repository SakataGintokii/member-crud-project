package com.example.app.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.io.FileOutputStream;

public class DownloadFileClient {

    public static void downloadFile(String fileName, String savePath) {
        RestTemplate restTemplate = new RestTemplate();

        // 設定url
        String url = "http://localhost:8080/file/download/" + fileName;

        // 發送GET請求，接收byte[]
        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);

        //檢查下載是否成功
        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
            byte[] fileBytes = response.getBody();

            // 儲存資料到本地端
            try (FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {
                fileOutputStream.write(fileBytes);
                System.out.println("下載成功，儲存在：" + savePath);
            } catch (Exception e) {
                System.err.println("下載失敗，HTTP狀態：" + response.getStatusCode());
            }
        }
    }

    public static void main(String[] args) {
        downloadFile("cat.jpg", "/Users/sakatagintoki/Downloads/cat.jpg");
    }

}