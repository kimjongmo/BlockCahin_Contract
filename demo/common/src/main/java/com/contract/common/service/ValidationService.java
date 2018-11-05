package com.contract.common.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.contract.common.util.FileHashExtractor.extractFileHashSHA256;


@Service
public class ValidationService {

    public void saveFile(MultipartFile file) {
        String path = "c:/_temp";

        File savePath = new File(path);

        if (!savePath.exists()) {
            savePath.mkdirs();
        }
        byte[] bytes = new byte[0];
        try {
            bytes = file.getBytes();

            Path saveFile = Paths.get(savePath.getPath() + "/" + file.getOriginalFilename());
            Files.write(saveFile, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean validate(String userId, String fileName,String compFile,String fileHash){
//        String[] str = chainCode.getBlock(fileName,userId);
//        String[] str1 = str[2].split("\\\"");
//        //블록에서 저장되어있던 해쉬값.
//        String blockHash = str1[3];
        String compFileHash="";
        //사용자의 파일 해쉬값
        try {
            compFileHash = extractFileHashSHA256("c:/_temp/"+compFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileHash.equals(compFileHash);

    }
}
