package review.basic;

/**
 * Created by Vincent on 2017/3/5.
 */
public class Test {
    public static void main(String[] args) {
        SingletonModel singletonModel = SingletonModel.getInstance();
        for (int i=0; i < 3; i++) {
            //  未测试多线程环境
            System.out.println("原始:\t" + singletonModel.getInstanceVersion1().hashCode());
            System.out.println("方法上synchronized:\t" + singletonModel.getInstanceVersion2().hashCode());
            System.out.println("类上synchronized:\t" + singletonModel.getInstanceVersion3().hashCode());
            System.out.println("使用volatile:\t" + singletonModel.getInstanceVersion4WithVolatile().hashCode());
            System.out.println("推荐方式:\t" + singletonModel.getInstance().hashCode());
        }
    }
}
