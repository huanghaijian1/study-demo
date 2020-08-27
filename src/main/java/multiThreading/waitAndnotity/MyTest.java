package multiThreading.waitAndnotity;

/**
 * @author hhj
 * @description
 * @date 2020/8/12 11:56
 */
public class MyTest {

    public static void main(String[] args) {

        try {
            Object lock = new Object();

            ThreadA a = new ThreadA(lock);
            Thread aa = new Thread(a);
            aa.start();

            Thread.sleep(50);


            ThreadB b = new ThreadB(lock);
            Thread bb = new Thread(b);
            bb.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
