
import org.apache.commons.codec.binary.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hhj
 * @description
 * @date 2020/7/29 10:21
 */
public class HTTPtest {

    public static void main(String[] args) {

        System.out.println("abcd.json".substring("abcd.json".lastIndexOf(".")));

////        System.out.println("1287938647234904064.zip".substring(0,"1287938647234904064.zip".indexOf(".zip")));
//        String msgFromSysID = "JWXT";
//
//        int rd = (int) ((Math.random() * 9 + 1) * 1000);
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//        String date = dateFormat.format(new Date());
//        String reqSerialNum = msgFromSysID + date + rd;
//
//
//        // 用私钥生成签名
//        String privatekey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCAunRgZ+/r/to1buncWKHz62yXsKBsEeO8foCMgEMCGCp5GFUm0Y8wx4by833hAr7AzTjlcxTAeD8+Ruw+FHzJJbgS/UkirXGy2oifRUDNsSWuJT7p826AcuZFpa7M2MnIknGg2IbRNHc643XjUFEsrCIPulQBneFjfYibeqyZI2pdmZvLDHuUAyiaiOYuy1zFvowiAP7e+k0SvDD38sQ41aBtAJPuRY9w4/f0KrBcT1QZjONjD5Loy89MWJdfRGSN+BcuGfGEmVgMXJ5Pha8APQvhurobRB0YpOWTX509/lgodt+t3nExwI5QcxO8Hw9kL6QCLqKOij/Kxh0a6wdhAgMBAAECggEAWmeYF6SnIKxI/0G3x+IPS0jJaPbIk100/UoyaXoilTP3kDgAhVSjJXTbV4CdpQrSgIEgNjROHKXWdJxGiYrF4FY3Ik7zsADzWtKTJJf0XQDOH7FUr+Ugc+E9yygCzSZrAAuo9Xdbp00bdyYLwurDN4ZkHXuUR9ZjVgXxjI/o61qs+riOlO19DADMT1Hd2NRMl4r0EeG7yLdn1vhRsFZWMPcC1VtQnIRppnGWENpQjylH/gD5spOP/WVmNGRFgJZZk0xn0xxgAT37+Q1ocoxqB6z2VoaVGnkgQu0iuB5qKrfWZobiG8GDtw4WOxYiO+9iqePIxM9kK5Rfs3ytY/CLgQKBgQDNwCUkH7lv3lwqb6gEuxXT8Ww/uRlMsKhgFwifQW6fmdRoiWOYBql9d8AKn9oS0djzaQiH4Yim8fQEEUl10bDMf71j6FjO7PlQmJrE5ZhF7SE5GSHj4/XjtEWP4sevLQ7l45j922Ff8/VybkY/xe6jDa6WyQrcftfmIYV+8Ud6eQKBgQCgKsGmkk5I5Szv3Hadx6mI5aIYGXJFeSgbYUOXASAts8oCosI0oLAA9amooqNN8HucWjAxnWtdM+VKKIu+jfHH/dI/2dw3WiGB5ql3ydtz7mptMIkyw1ifBaGi+THJU/J6HuG6QATc3aBRe/bkCSK8tsJE1FqkxcBe4+hujEE6KQKBgAQUAeh0ZIWOH7+LlQX6+1THHUEqFxFJEx1Md5RIhLvPHcGwr3Os9iERhZHwVy82T5mLhGrFEqNUemS2PwGB1YJIKeXYDiKT2eE6FR8/+cOxDo6GLADLXO1Vfmd6tECJoRj3aOnnNkfjmwLYorXS44cB12sPsxJpxTe/hFjeyW7pAoGAIq8foGKucTqvsIGdbi1+mRahM3bPOBw0gzlc2aZ0wXDdnhSA2cSyWILaappNouMg4YrmRNhCF7zVOmntWSaD5Tsh/JHBDnuGJgs9R6N+0mwev+Zyz3teBZHCnvg3v9FeAPQPDbYvV2TnAr5FiLwxuYwaJa2Ws3ffYK0Z+FaHCYECgYA4Cia70EaovdzJjbggirnKtnOiV6y9Z7+R9z1FydC1unIhcgFSfYLJ8Owl/2Zj1yWrV9XuzZPOxndluFFnwmV08SwJEVBuC1SYi5Ll+sEz2LIAKzyD4noIHTNRCpHxoyPxQJeupn48rC9aFpphRfjbx8KYQ9bYCa1LLVTIxW5fIw==";
//        byte[] bs = sign(Base64.decodeBase64(privatekey),reqSerialNum);
//
//        System.out.println(Base64.encodeBase64String(bs)); // 签名域
//        System.out.println(reqSerialNum);
//        System.out.println(date);
    }

}
