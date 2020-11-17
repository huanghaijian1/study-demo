package io;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
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
//        try{
//            String tempFilePath = "D://2015.doc";
//            File tempFile = new File(tempFilePath);
//            File file = new File("D://2015.pdf");
//
//            FileOutputStream os = new FileOutputStream(file);
//            Document doc = new Document(tempFilePath);//Address是将要被转化的word文档
//            doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
//            byte[] buff = file2byte(file);
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        OutputStream output = null;
        BufferedOutputStream bufferedOutput = null;

        try{
            String tempFilePath = "D://5.doc";
            File tempFile = new File(tempFilePath);
            File file = new File("D://5.pdf");

            File tempFile2 = new File("D://6.doc");

            File tempFile6 = new File("D://7.doc");


            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document("D://6.doc");//Address是将要被转化的word文档
            doc.save(os, SaveFormat.PDF);//全面支持DOC, DOCX, OOXML, RTF HTML, OpenDocument, PDF, EPUB, XPS, SWF 相互转换
            byte[] buff = file2byte(file);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 将文件转换成byte数组
     * @param tradeFile
     * @return
     */
    private static byte[] file2byte(File tradeFile){
        byte[] buffer = null;
        try{
            FileInputStream fis = new FileInputStream(tradeFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return buffer;
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
