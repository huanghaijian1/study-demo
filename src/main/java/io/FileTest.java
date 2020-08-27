package io;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.io.ZipOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * @author hhj
 * @description
 * @date 2020/8/10 16:10
 */
public class FileTest {
    public static void main(String[] args) {
        File startFile = new File("D://1291307043519770627.zip");

        ZipInputStream zis = null;
        List<File> fileList = new ArrayList<>();
        File dir = new File("1291307043519770627");
        if (!dir.exists()) {// 判断目录是否存在
            dir.mkdir();
        }
        try {
            zis = new ZipInputStream(new FileInputStream(startFile));
            ZipEntry ze;



            while ((ze = zis.getNextEntry())!=null){
                String zeName = ze.getName();
                //File tempFile = new File(zeName);
                File tempFile = new File(dir,zeName);
                FileAndIOUtils.inputstreamtofile(zis,tempFile);

            }
            FileAndIOUtils.compress("JWXT_1291307043519770627.zip","abc123",dir.listFiles());

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //startFile.delete();
            //startFile.deleteOnExit();
            for(File file : dir.listFiles()){
                file.delete();
                file.deleteOnExit();
            }
            dir.delete();
//            File file = new File("JWXT_1291307043519770627.zip");
//            file.delete();
//            file.deleteOnExit();
        }

    }

    public static byte[] filetoByteArray(InputStream fileInputStream){
        ByteArrayOutputStream bos = null;
        BufferedInputStream in = null;
        try {
            bos = new ByteArrayOutputStream(fileInputStream.available());
            in = new BufferedInputStream(fileInputStream);
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null){
                    in.close();
                }
                if(bos != null){
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
