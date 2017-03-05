package review.basic;

/**
 * Created by Vincent on 2017/3/5.
 * Description 复习回顾单利设计模式与线程安全
 */
public class SingletonModel {

    private SingletonModel instance;

    public SingletonModel(){

    }

    /**
     * single-thread version.
     * basic achievement. which will cause multi-thread safe problems
     */
    public SingletonModel getInstanceVersion1() {
        if (instance == null) {
            instance = new SingletonModel();
        }
        return instance;
    }

    /**
     * Correct but possibly expensive multithreaded version
     */
    public synchronized SingletonModel getInstanceVersion2(){
        if (instance == null) {
            instance = new SingletonModel();
        }
        return instance;
    }

    /**
     * Correct but possibly expensive multithreaded version
     */
    public SingletonModel getInstanceVersion3(){
        if (instance == null) {
            synchronized (this) {
                if (instance == null) {
                    instance = new SingletonModel();
                }
            }
        }
        return instance;
    }

    /**
     * since jdk 1.5 using volatile
     */
    private volatile  SingletonModel volatileInstance;

    public SingletonModel getInstanceVersion4WithVolatile(){
        SingletonModel temp = volatileInstance;
        if (temp == null) {
            synchronized (this) {
                temp = volatileInstance;
                if (temp == null) {
                    volatileInstance = temp = new SingletonModel();
                }
            }
        }
        return instance;
    }

    /**
     * In software engineering, the initialization-on-demand holder (design pattern) idiom is a lazy-loaded singleton.
     * In all versions of Java, the idiom enables a safe, highly concurrent lazy initialization with good performance
     * prefer to do/achieve singleton like this below.
     * **/
    private static class LazyHolder {
        private static final SingletonModel singletonModel = new SingletonModel();
    }

    public static SingletonModel getInstance() {
        return LazyHolder.singletonModel;
    }
}
