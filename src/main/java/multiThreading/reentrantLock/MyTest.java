package multiThreading.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author hhj
 * @description
 * @date 2020/8/13 11:22
 */
public class MyTest {



    public static void main(String[] args) {
//        //公平锁与非公平锁的设置-公平锁
//        test1();

        test2();
    }


    /**
     * lock 与 lockInterruptibly比较区别在于：
     * lock 优先考虑获取锁，待获取锁成功后，才响应中断。
     * lockInterruptibly 优先考虑响应中断，而不是响应锁的普通获取或重入获取。
     *
     * 详细区别：
     * ReentrantLock.lockInterruptibly允许在等待时由其它线程调用等待线程的Thread.interrupt方法来中断等待线程的等待而直接返回，
     * 这时不用获取锁，而会抛出一个InterruptedException。 ReentrantLock.lock方法不允许Thread.interrupt中断,
     * 即使检测到Thread.isInterrupted,一样会继续尝试获取锁，失败则继续休眠。只是在最后获取锁成功后再把当前线程置为interrupted状态,然后再中断线程
     */
    public static void run1(Lock lock){
        for(int j =0; j < 5; j++){
            try {
                //System.out.println(i+"获得锁前");
                lock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName()+"已获得锁"+j);
                Thread.sleep(1);
                //System.out.println();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                ///System.out.println(i+"释放锁");
                lock.unlock();
            }
        }
    }
    public static void run2(Lock firstlock,Lock secondlock){
            try {
                firstlock.lockInterruptibly();
                Thread.sleep(100);
                secondlock.lockInterruptibly();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                firstlock.unlock();
                secondlock.unlock();
                System.out.println(Thread.currentThread().getName()+"成功释放锁");
            }
    }

    /**
     * ReentrantLock特性一：公平锁与非公平锁的设置
     * synchronized(lock){}只能为非公平锁
     */
    public static void test1(){
        //公平锁，谁等待时间长谁先获取锁，默认为false,非公平锁final Lock lock = new ReentrantLock();
        final Lock lock = new ReentrantLock(true);
        //final Lock lock = new ReentrantLock();
        new Thread(()->run1(lock),"线程1").start();
        new Thread(()->run1(lock),"线程2").start();
        new Thread(()->run1(lock),"线程3").start();
        new Thread(()->run1(lock),"线程4").start();
        new Thread(()->run1(lock),"线程5").start();
        new Thread(()->run1(lock),"线程6").start();
    }

    /**
     * 从源代码，总结出Java线程的状态有以下几种：
     *   thread1.getState()可获得状态值
     *     NEW：一个尚未启动的线程的状态。也称之为初始状态、开始状态。
     *     RUNNABLE：一个可以运行的线程的状态，可以运行是指这个线程已经在JVM中运行了，但是有可能正在等待其他的系统资源。也称之为就绪状态、可运行状态。
     *     BLOCKED：一个线程因为等待监视锁而被阻塞的状态。也称之为阻塞状态。
     *     WAITING：一个正在等待的线程的状态。也称之为等待状态。造成线程等待的原因有三种，分别是调用Object.wait()、join()以及LockSupport.park()方法。处于等待状态的线程，正在等待其他线程去执行一个特定的操作。例如：因为wait()而等待的线程正在等待另一个线程去调用notify()或notifyAll()；一个因为join()而等待的线程正在等待另一个线程结束。
     *     TIMED_WAITING：一个在限定时间内等待的线程的状态。也称之为限时等待状态。造成线程限时等待状态的原因有五种，分别是：Thread.sleep(long)、Object.wait(long)、join(long)、LockSupport.parkNanos(obj,long)和LockSupport.parkUntil(obj,long)。
     *     TERMINATED：一个完全运行完成的线程的状态。也称之为终止状态、结束状态。
     *
     *     当线程状态为：TERMINATED时，会清除掉thread.interrupt()设置的中断阻塞状态！
     */
    /** 都只是获取或设置线程的中断阻塞状态标志，并不会直接中断线程，用法 if(test){执行任务...}else{//中断线程false true thread1.isInterrupted(); thread1.interrupt();thread1.isInterrupted();}
     * thread1.interrupt()  作用是 设置当前线程（thread1）为中断阻塞状态(使得指定线程中断阻塞状态，并将阻塞标志位置为true。)
     * thread1.isInterrupted()  作用是只测试此线程(thread1)是否为中断阻塞状态 ，不清除中断阻塞状态。
     * thread1.interrupted() 作用是测试当前代码运行所在线程(是main，而不是thread1，除非在thread1.run里调用)是否为中断阻塞状态（检查中断标志），
     *                       返回一个boolean并清除中断阻塞状态，第二次再调用时中断阻塞状态已经被清除，将返回一个false。
     *  thread1.getState()可获得状态值
     * 当线程状态为：TERMINATED时，会清除掉thread.interrupt()设置的中断阻塞状态！
     * TERMINATED：一个完全运行完成的线程的状态。也称之为终止状态、结束状态。
     */
    public static void test2(){
        final Lock lock = new ReentrantLock();
        final Lock lock2 = new ReentrantLock();
        Thread thread1 = new Thread(() -> run2(lock,lock2),"线程一");

        Thread thread2 = new Thread(() -> run2(lock2,lock),"线程二");


        thread1.start();
        thread2.start();

        //中断当前（thread1）线程
        thread1.interrupt();

        //thread1.isInterrupted()作用是只测试此线程(thread1)是否被中断 ，不清除中断阻塞状态。
        System.out.println("thread1.interrupt()后第一次调用thread1.isInterrupted()："+thread1.isInterrupted());
        System.out.println("thread1.interrupt()后第二次调用thread1.isInterrupted()："+thread1.isInterrupted());

        //thread1.interrupted()作用是测试当前线程(main，而不是thread1)是否被中断（检查中断标志），返回一个boolean并清除中断阻塞状态，第二次再调用时中断阻塞状态已经被清除，将返回一个false。
        System.out.println("thread1.interrupt()后第一次调用thread1.interrupted()："+thread1.interrupted());
        System.out.println("thread1.interrupt()后第二次调用thread1.interrupted()："+thread1.interrupted());
        System.out.println("thread1是否存活："+thread1.isAlive());

        Thread.currentThread().interrupt();
        System.out.println("Thread.currentThread().interrupt()后第一次调用thread1.interrupted()："+thread1.interrupted());
        System.out.println("Thread.currentThread().interrupt()后第二次调用thread1.interrupted()："+thread1.interrupted());
        System.out.println("Thread.currentThread()是否存活："+Thread.currentThread().isAlive());

        System.out.println("结论：");
        System.out.println("   thread1.interrupt() 作用是 设置当前线程（thread1）为中断阻塞状态(使得指定线程中断阻塞状态，并将阻塞标志位置为true。)");
        System.out.println("   thread1.isInterrupted()  作用是只测试此线程(thread1)是否为中断阻塞状态 ，不清除中断阻塞状态。");
        System.out.println("   thread1.interrupted() 作用是测试当前代码运行所在线程(是main，而不是thread1，除非在thread1.run里调用)是否为中断阻塞状态（检查中断标志），");
        System.out.println("                         返回一个boolean并清除中断阻塞状态，第二次再调用时中断阻塞状态已经被清除，将返回一个false。");
    }


}
