package enAndDecryption;


import org.jasypt.util.text.BasicTextEncryptor;

import java.util.Map;


/**
 * @author hhj
 * @description
 * @date 2020/7/28 16:37
 */
public class TestUtil {
    public static void main(String[] args) {
//        //配置文件中密码加密方法
//        propertiesjm();
//
//        //私钥解密
//        String uuid =  RSAUtils.decryptDataOnJava("SU16crv0FtPlRHWDxGVk00dkImUk3ddDO2Gz8FEGWolHM1uWeKYO2U0nOxONua4nnN4BYBgr5fktj77p6RNOSHaf7AkpJYkCct63/O0UWojxYBo9UsehuP9QcB22QMpBHG/isbPnI3X3Z5S+5nN1Fh4CxRqsjWKXS4J3xifymss=",
//                "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJ+/PdeSXHbpfjq93Mj+FAIISHxxyMHORolMoZXCy7a+UhKHRUg0/k9IZzYn4WH0xp/vvYdSqs8d0iXpGYgLRbDnv4G/JJWJ92rcv0OsXIaZgNgkq6mtpSygvVXAHcdbtRQP+bT5wk2YklpV7MNsVy+fcL7IQCaON6EgTRa+eH1ZAgMBAAECgYAtopJmHPexOK7I1ShXes34bqlWc6LoSqc33d5jTAyqHeCQTnhqUkk8GPDzNZ1bQHPliLVaDTGijWBVWCvlA2Na26wkRPBR7XmH0j4sGowTx9UgWk2gKOR9iKaEhlms9dENiOVZsZ40caPlLQO7r/cp0HFiz8TM+EtJX/QaZCX1AQJBAObMyX22mrx13DLVO8vrkVwrKZK88mUGoMEvTTCoLrBdUZAslqjjOVS+OOdfVWe0FcCcNGWcQNttEx2pQT4P31ECQQCxMGqg60Ys385w8wz7GkMLYsBhjhdELh1trdmQxPDfOImr+5LilI5tqmob7Ffl6gsisLm8Q2e4h9vTCPzZ6YuJAkA6S1VFHF8e5MH2UOdmjRxM5FX+yxyKKc9mm/TqKkCIqTlVqf1vsNylK9qhjfG7XL/1wz50v6+d/IHMhxiEfWvRAkBvAEXMSZfMxOgvGhQnumwF1fdvJfStYbZN33tMlhPPwzt74j5Cc45GZVvT4luuTNBtCwlQR2nS0EmGCztbyFMBAkEAh6J+J2L2d7KMrZn7zCYAGlTq5sQeooVT6g698fk+f+6S3j307WB8ZCktKwMfUBpR4LxJJ9aA0Mimcv0/hdHUdg==");
//        System.out.println(uuid);
//
//        //公钥加密
//        String key = RSAUtils.encryptedDataOnJava(uuid,
//                "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCk8VaAAYCOrfHKAuittPOkcH5SNpCWLG9cE1MxTBVXHiI2YZI8cJ+/uv26WAMqRyPAoBV/9li0liBps9BrYHGGcNGL8E337a+2VeHjRj7WEIE7qzdogZxPmjKoe1gaEJajdroHUmqtnpIdSx57QNEmqAnVK1A40pMhVXkdvKdzuwIDAQAB");
//        System.out.print(key);
//
//        //生成私钥 公钥 秘钥方法
//        Map<String, Object> keyMap;
//        try {
//            keyMap = CreateSecrteKey.initKey();
//            //公钥
//            String publicKey = CreateSecrteKey.getPublicKey(keyMap);
//            System.out.println(publicKey);
//            //私钥
//            String privateKey = CreateSecrteKey.getPrivateKey(keyMap);
//            System.out.println(privateKey);
//
//            System.out.println(CreateSecrteKey.getPublicKey(keyMap).equals(publicKey));
//            keyMap = CreateSecrteKey.initKey();
//            System.out.println(CreateSecrteKey.getPublicKey(keyMap).equals(publicKey));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    /**
     * 配置文件中密码加密方法 password = ENC(password)
     */
    private static void propertiesjm(){
        /*配置文件中密码加密方法 start*/
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();

        // 加密密钥 (长点复杂点的字符串就可以,约定好就行)
        textEncryptor.setPassword("9e5eb701949a152dabb25cf9a21b768f2439fa9dd76437d4209bbee8088a1be5");
        //textEncryptor.setPassword(UUID.randomUUID().toString());
        // 要加密的数据（如数据库的用户名或密码）
        String password = textEncryptor.encrypt("j1ifAsYZ");
        //加密结果
        System.out.println("password:" + password);

        //解密结果
        String p = textEncryptor.decrypt(password);
        System.out.println("p:" + p);
        /*配置文件中密码加密方法 end*/

        System.out.println("mq测试环境密码："+textEncryptor.decrypt("u6AFKIiSiUTcEczimCBbdg=="));
        System.out.println("mq生产环境密码："+textEncryptor.decrypt("YEtRYcqUE8A+Ptm2dL6RaA=="));
    }

}
