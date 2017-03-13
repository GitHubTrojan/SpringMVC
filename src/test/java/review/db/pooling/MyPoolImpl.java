package review.db.pooling;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Vector;

/**
 * Created by Vincent Wang(王言斌)) on 2017/3/12 0012.
 * Version 1.0.0
 * Description
 */
public class MyPoolImpl implements MyPool {
    private static String driver = "";
    private static String url = "";
    private static String userName = "";
    private static String password = "";
    private static int initCapacity = 10;
    private static int stepSize = 2;
    private static int maxSize = 15;
    private Vector<PooledConnections> pooledConnections = new Vector<PooledConnections>();

    public MyPoolImpl() {
        init();
    }

    private void init() {
        //  获取配置文件
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("pooled_connection.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  初始化Connection相关参数
        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        userName = properties.getProperty("userName");
        password = properties.getProperty("password");
        //  连接池初始化相关参数
        initCapacity = Integer.valueOf(properties.getProperty("initCapacity"));
        stepSize = Integer.valueOf(properties.getProperty("stepSize"));
        maxSize = Integer.valueOf(properties.getProperty("maxSize")) >= 0? maxSize : 0;
        //  注册驱动并初始化连接池
        try {
            Driver jdbcDirver = (Driver) Class.forName(driver).newInstance();
            DriverManager.registerDriver(jdbcDirver);
        } catch (Exception e) {
            e.printStackTrace();
        }
        createConnection(initCapacity);
    }


    /**
     * 连接池对外开放获取管道连接的服务
     * @return
     */
    @Override
    public PooledConnections getConnection() {
        if (pooledConnections.size() == 0){
            System.out.println("当前连接池未初始化，无链接对象可提供！");
        }
        //  检查是否有可用的管道连接
        PooledConnections pooledConnection = getAvailableConnection();
        while (pooledConnection  == null){
            createConnection(stepSize);
            pooledConnection = getAvailableConnection();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return pooledConnection;
    }

    /**
     * 获取可用的池化连接
     * 加锁适应高并发
     * 通过校验 isBusy 属性的方式校验
     * @return
     */
    private synchronized PooledConnections getAvailableConnection() {
        for (PooledConnections conn : pooledConnections) {
            //  判断是否繁忙
            if (!conn.isBusy()){
                Connection connection = conn.getConnection();
                //  判断是否有效
                try {
                    //  失效替换
                    if (!connection.isValid(2000)){
                        Connection validConnection = DriverManager.getConnection(url, userName, password);
                        conn.setConnection(validConnection);;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                conn.setBusy(true);
                return conn;
            }
        }
        return null;
    }

    /**
     * 暴漏此方法调用，用以创建数据库连接池中的连接对象
     *
     * @param count
     */
    @Override
    public void createConnection(int count) {
        //  判断是否符合创建新连接的条件
        if (maxSize > 0 && pooledConnections.size() + count > maxSize) {
            System.out.println("创建管道连接失败，原因是将超过允许的最大值！");
            throw  new RuntimeException("创建管道连接失败，原因是将超过允许的最大值！");
        }
        for (int i=0;i < count;i++){
            try {
               Connection connection =  DriverManager.getConnection(url, userName, password);
               PooledConnections pooledConnection = new PooledConnections(connection,false);
               pooledConnections.add(pooledConnection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
