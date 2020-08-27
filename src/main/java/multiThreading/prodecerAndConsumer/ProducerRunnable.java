package multiThreading.prodecerAndConsumer;

import java.util.List;
import java.util.UUID;

/**
 * @author hhj
 * @description
 * @date 2020/8/13 9:31
 */
public class ProducerRunnable implements Runnable {


    private Object lock;

    private List<String> data;

    private String threadName;


    public ProducerRunnable(){

    }
    public ProducerRunnable(Object lock, List<String> data,String threadName){
        this.lock = lock;
        this.data = data;
        this.threadName = threadName;
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        while (true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock){
                for(int i = 1; i<4; i++){
                    data.add("产品"+i+"-"+ UUID.randomUUID().toString().replaceAll("-",""));
                }
                System.out.println(threadName+"已生产出产品正在唤醒所有消费者-" + System.currentTimeMillis());

                lock.notifyAll();
            }
        }


    }
}
