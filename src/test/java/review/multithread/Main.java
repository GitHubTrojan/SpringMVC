package review.multithread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Vincent Wang(王言斌)) on 2017/3/7 0007.
 * Version 1.0.0
 * Description
 */
public class Main {
    private static int result = 0;
    private static StringBuffer resultString = new StringBuffer("");
    public static void main(String[] args) {
//        final CountDownLatch countDown = new CountDownLatch(1000);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (Main.class){
                    resultString.append(" ").append(Thread.currentThread().getName());
                    result++;
                }
//                countDown.countDown();
            }
        };
        Thread thread;
        for (int i = 0; i < 100; i++){
            thread = new Thread(runnable);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        try {
//            countDown.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("result: \t" + result);
        System.out.println("result: \t\n" + resultString);
    }
}
