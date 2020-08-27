package multiThreading.waitAndnotity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hhj
 * @description
 * @date 2020/8/12 11:49
 */
public class MyList {

    private static List<String> list = new ArrayList<String>();

    public static void add() {
        list.add("anyString");
    }

    public static int size() {
        return list.size();
    }




}
