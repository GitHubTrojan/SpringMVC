package review.db.pooling;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Vincent Wang(王言斌)) on 2017/3/12 0012.
 * Version 1.0.0
 * Description 自定义数据库连接池测试类
 */
public class MyPoolTest {
    private static MyPoolImpl myPoolImpl = PoolManager.getInstance();
    /**
     * 查询
     */
    public static void searchALl(){
        String sql = "SELECT * FROM users";
        PooledConnections pooledConnections = myPoolImpl.getConnection();
        ResultSet resultSet = pooledConnections.queryBySql(sql);
        System.out.println("当前任务线程名称：" + Thread.currentThread().getName());
        try {
            while(resultSet.next()){
                System.out.print(resultSet.getString("uid") + "\t\t");
                System.out.println(resultSet.getString("uname") + "\t\t"); ;
            }
            resultSet.close();
            pooledConnections.free();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long starttime = System.currentTimeMillis();
        for (int i=0;i < 1000;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    searchALl();
                }
            }).start();
        }
        long endtime = System.currentTimeMillis();
        System.out.println("任务耗时：" + (endtime - starttime ));
    }
}
