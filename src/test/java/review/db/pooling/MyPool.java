package review.db.pooling;

/**
 * Created by Vincent Wang(王言斌)) on 2017/3/12 0012.
 * Version 1.0.0
 * Description  数据库连接池自实现
 */
public interface MyPool {
    /**\
     * 获取池化对象
     * @return
     */
    PooledConnections getConnection();

    /**
     * 创建新的池化连接
     * @param count
     */
    void createConnection(int count);
}
