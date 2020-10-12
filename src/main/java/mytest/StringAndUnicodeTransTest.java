package mytest;

public class StringAndUnicodeTransTest {

    public static void main(String[] args) {
        String t1 ="Various \"Ways of Seeing\": A Critical Examination of Berger's Aesthetics of the Female Nude and Mulvey's Concept of the Male Gaze";
        String t3 = "“测试123”A&B%<>^:;：；,，——\\${}/(\"\")@#~`?？''";
        String t2 = null;
        System.out.println("字符串："+t1);

        String unicode = string2Unicode(t1);
        System.out.println("转义后："+unicode);

        String str = unicode2String(unicode);
        System.out.println("反转义："+str);

        System.out.println(str.equals(t1));

    }

    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }

    /**
     * 字符串转换unicode
     */
    private static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }
}
