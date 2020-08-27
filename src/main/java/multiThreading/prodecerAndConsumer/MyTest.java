package multiThreading.prodecerAndConsumer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author hhj
 * @description
 * @date 2020/8/13 9:37
 */
public class MyTest {

    /**
     * 这里是单生产者多消费者，
     * 如果是多生产者多消费者，每个生产者一个锁，消费者随机获得一个锁匹配对应生产者。同时可以设置消费者等待时间，若超过这改变获得的生产者锁
     * @param args
     */

    public static void main(String[] args) {
        Object lock = new Object();
        List<String> data = Collections.synchronizedList(new ArrayList<>());

        String threadName = "消费者一号";
        ConsumerRunnable cr1 = new ConsumerRunnable(lock, data, threadName);
        Thread t1 = new Thread(cr1);
        t1.setName(threadName);
        t1.start();

        String threadName2 = "消费者二号";
        ConsumerRunnable cr2 = new ConsumerRunnable(lock, data, threadName2);
        Thread t2 = new Thread(cr2);
        t2.setName(threadName2);
        t2.start();

        if (data == null || data.size() == 0) {
            String threadName11 = "生产者一号";
            ProducerRunnable cr11 = new ProducerRunnable(lock, data, threadName11);
            Thread t11 = new Thread(cr11);
            t11.setName(threadName11);
            t11.start();
        }

    }
}
