package multiThreading.concurrent;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StringAndUnicodeTransTest {

    public static void main(String[] args) {
//        String t1 ="Various \"Ways of Seeing\": A Critical Examination of Berger's Aesthetics of the Female Nude and Mulvey's Concept of the Male Gaze";
//        String t3 = "“测试123”A&B%<>^:;：；,，——\\${}/(\"\")@#~`?？";
//
//        System.out.println("字符串："+t1);
//
//        String unicode = string2Unicode(t1);
//        System.out.println("转义后："+unicode);
//
//        String str = unicode2String(unicode);
//        System.out.println("反转义："+str);
//
//        System.out.println(str.equals(t1));


//        String path = StringAndUnicodeTransTest.class.getClassLoader().getResource("archiveJson.json").getPath();
//        String s = readJsonFile(path);
//        JSONArray jsonArray = JSON.parseObject(s).getJSONObject("Meta").getJSONArray("ENTITY_ZMIS0007C03");
//        String lwtm = JSON.parseObject(jsonArray.get(0).toString()).get("ZM0007C03Z09").toString();
//        System.out.println(unicode2String(lwtm));

        String path = StringAndUnicodeTransTest.class.getClassLoader().getResource("archiveJson2.json").getPath();
        String s = readJsonFile(path);
        JSONObject jsonObject1 = JSON.parseObject(s).getJSONObject("Meta");
        List<Map<String,Object>> entity_approveentity = (List<Map<String,Object>>)jsonObject1.get("ENTITY_APPROVEENTITY");
        System.out.println(entity_approveentity.get(0).get("ENTITY_APPROVEENTITY_JSON"));


    }

    //读取json文件
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);
            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }

    /**
     * 字符串转换unicode,经过网络传送unicode.append("\\\\u" + Integer.toHexString(c));
     */
    public static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }
}
