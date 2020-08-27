package io;

import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * io流工具类
 * String、byte[]、file、inputStream相互转换、 压缩包的生成与操作
 * 创建临时File时文件名最好唯一，避免多线程下创建出路径一样的File
 * @author hhj
 * @description
 * @date 2020/8/6 9:47
 */
public class FileAndIOUtils {


    /**
     * InputStream --> String
     * 方法一：速度快，但是比较耗内存，
     * @param is
     * @return
     */
    public static String inputStream2String(InputStream is){
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        BufferedReader in = new BufferedReader(inputStreamReader);
        StringBuffer buffer = new StringBuffer();
        try{
            String line = "";
            while ((line = in.readLine()) != null){
                buffer.append(line);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(in!=null){
                    in.close();
                }
                if(inputStreamReader!=null){
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }

    /**
     * InputStream --> String
     * 方法二：速度慢，耗资源少
     * @param ins
     * @return
     */
    public static String inputStream2String2(InputStream ins){
        String all_content= StringUtils.EMPTY;
        ByteArrayOutputStream outputstream = new ByteArrayOutputStream();
        try {
            byte[] str_b = new byte[1024];
            int i = -1;
            while ((i=ins.read(str_b)) > 0) {
                outputstream.write(str_b,0,i);
            }
            all_content = outputstream.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                outputstream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  all_content;
    }

    /**
     *InputStream --> file
     * @param ins
     * @param file
     */
    public static void inputstreamtofile(InputStream ins,File file){
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = ins.read(buffer, 0, 1024)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(os!=null){
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * byte[] --> file
     * byte[] byte = string.getBytes();
     * String --> file
     * @param byt
     * @param file
     * @return
     */
    public static void byteArrayToFile(byte[] byt,File file){
        OutputStream output = null;
        BufferedOutputStream bufferedOutput = null;
        try {
            output = new FileOutputStream(file);
            bufferedOutput = new BufferedOutputStream(output);
            bufferedOutput.write(byt);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(output != null){
                    output.close();
                }
                if(bufferedOutput != null){
                    bufferedOutput.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * File --> InputStream
     * @param file
     * @return
     */
    public static InputStream filetoInputStream(File file){
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    /**
     * String --> InputStream
     * @param str
     * @return
     */
    public static InputStream String2InputStream(String str){
        ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
        return stream;
    }

    /**
     * byte[] --> inputStream
     * @param byt
     * @return
     */
    public static InputStream byteArrayToInputStream(byte[] byt){ ;
        InputStream input = new ByteArrayInputStream(byt);
        return input;
    }


    /**
     * file --> byte[]
     * @param filePath
     * @return
     * @throws IOException
     */
    public byte[] getContent(String filePath) throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > Integer.MAX_VALUE) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while (offset < buffer.length
                && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
            offset += numRead;
        }
        // 确保所有数据均被读取
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file "
                    + file.getName());
        }
        fi.close();
        return buffer;
    }

    /**
     * file,inputStream --> byte[]
     * the traditional io way
     * 将file转换成byte数组
     * @param f
     * @return
     * @throws IOException
     */
    public static byte[] filetoByteArray(File f){
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
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

    /**
     * NIO way
     * file,inputStream --> byte[]
     * @param f
     * @return
     * @throws IOException
     */
    public static byte[] filetoByteArray2(File f) throws IOException {
        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
     * file --> byte[]
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] filetoByteArray3(String filename) throws IOException {

        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(filename, "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                    fc.size()).load();
            System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                // System.out.println("remain");
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     *   stream 压缩包流 --> List<File>
     * @param stream 压缩包流
     * @return 压缩包内fileList
     */
    public static List<File> operationZip(InputStream stream){
        ZipInputStream zis = null;
        List<File> fileList = new ArrayList<>();
        try {
            zis = new ZipInputStream(stream);
            ZipEntry ze;
//            //如果在多线程下，每个stream下的文件名有一样的就先创建一个父文件夹
//            File dir = new File(UUID.randomUUID().toString().replaceAll("-",""));
//            if (!dir.exists()) {// 判断目录是否存在
//                dir.mkdir();
//            }
            while ((ze = zis.getNextEntry()) != null) {
                //File tempFile = new File(dir,ze.getName())
                File tempFile = new File(ze.getName());
                inputstreamtofile(zis, tempFile);
                fileList.add(tempFile);
            }
//            return dir.listFiles();
            return fileList;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(zis != null){
                try {
                    zis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileList;
    }

    /**
     * fileList --> 生成zip
     * 生成压缩包并设置压缩参数，如：解压密码
     * @param destFileName 生成zip文件名
     * @param passwd 解压密码
     * @param fileList 生成zip里的文件列表
     * @return
     * zip4j技术生成压缩包功能更强大更多，java字段工具类生成压缩包不能设置解压密码
     */
    public static String compress(String destFileName, String passwd, File... fileList) {
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // 压缩方式
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); // 压缩级别
        if (!StringUtils.isEmpty(passwd)) {
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD); // 加密方式
            parameters.setPassword(passwd.toCharArray());
        }
        try {
            net.lingala.zip4j.core.ZipFile zipFile = new net.lingala.zip4j.core.ZipFile(destFileName);
            for (File file : fileList) {
                zipFile.addFile(file, parameters);
            }
            return destFileName;
        } catch (ZipException e) {
            e.printStackTrace();
        }
        return null;
    }

}
