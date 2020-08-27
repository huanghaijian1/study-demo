package multiThreading.prodecerAndConsumer;

import java.util.List;

/**
 * 消费者
 * @author hhj
 * @description
 * @date 2020/8/13 9:19
 */
public class ConsumerRunnable implements Runnable{

    private Object lock;

    private List<String> data;

    private String threadName;


    public ConsumerRunnable(){

    }
    public ConsumerRunnable(Object lock, List<String> data,String threadName){
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
            synchronized (lock){
                while (data.size() == 0){
                    System.out.println("因为暂无可购买产品"+threadName+"进入wait等待状态");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(threadName+"正前往商店购买产品-" + System.currentTimeMillis());
                }
                String content = data.get(0);
                data.remove(0);
                System.out.println(threadName+"成功购买 "+content);
            }
            try {
               int rondom = (int) (Math.random() * 10000);
                System.out.println(threadName+"-"+rondom);
                Thread.sleep(rondom);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
