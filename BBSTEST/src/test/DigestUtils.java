package test;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
 
public class DigestUtils {
     
    public static String extractFileHashSHA256(String filename) throws Exception {
         
        String SHA = "";
        int buff = 16384;
        try {
            RandomAccessFile file = new RandomAccessFile(filename, "r");
 
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
            partialHash = new byte[hashSum.getDigestLength()];
            partialHash = hashSum.digest();
             
            StringBuffer sb = new StringBuffer();
            for(int i = 0 ; i < partialHash.length ; i++){
                sb.append(Integer.toString((partialHash[i]&0xff) + 0x100, 16).substring(1));
            }
            SHA = sb.toString();
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }	
         
        return SHA;
    }
     
    public static void main(String[] args) throws Exception {
    	String hash = "07afddb14d2b8bd5277445e56a3d281740aca48effe927bac1a05354a0dd503f";
    	String file = extractFileHashSHA256("C:/Users/kim/Desktop/변경전.txt");
        System.out.println(hash.equals(file));
    }  
}

//pptx excel 의 경우 열고 닫기만 해도 해시가 변함.