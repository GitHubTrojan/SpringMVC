package review.db.pooling;

/**
 * Created by Vincent Wang(王言斌)) on 2017/3/12 0012.
 * Version 1.0.0
 * Description 线程池管理对象，实现线程池的对外单例
 */
public class PoolManager {
    private MyPoolImpl myPool;

    private static class createPool {
        private static MyPoolImpl myPoolImpl = new MyPoolImpl();
    }

    public static MyPoolImpl getInstance(){
        return createPool.myPoolImpl;
    }
}
