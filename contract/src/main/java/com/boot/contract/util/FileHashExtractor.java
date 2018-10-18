package com.boot.contract.util;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;

public class FileHashExtractor {
    //파일 경로
    public static String extractFileHashSHA256(String fileName) throws Exception {

        String SAVE_DIRECTORU="c:/_contract/";
        String SHA = "";
        int buff = 16384;
        try {
            RandomAccessFile file = new RandomAccessFile(SAVE_DIRECTORU+fileName+".docx", "r");

            MessageDigest hashSum = MessageDigest.getInstance("SHA-256");

            byte[] buffer = new byte[buff];
            byte[] partialHash = null;

            long read = 0;

            // calculate the hash of the hole file for the test
            long offset = file.length();
            int unitsize;
            while (read < offset) {
                unitsize = (int) (((offset - read) >= buff) ? buff : (offset - read));
                file.read(buffer, 0, unitsize);

                hashSum.update(buffer, 0, unitsize);

                read += unitsize;
            }

            file.close();
            //partialHash = new byte[hashSum.getDigestLength()];
            partialHash = hashSum.digest();

            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < partialHash.length; i++) {
                sb.append(Integer.toString((partialHash[i] & 0xff) + 0x100, 16).substring(1));
            }
            SHA = sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return SHA;
    }
}
