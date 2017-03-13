package review.db.pooling;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Vincent Wang(王言斌)) on 2017/3/12 0012.
 * Version 1.0.0
 * Description 池化连接对象
 */
public class PooledConnections {
    //  真正的数据库连接对象
    private Connection connection;
    //  标志数据库连接对象，是否可用（是否被占用）
    private boolean isBusy = false;

    public PooledConnections(Connection connection, boolean isBusy){
        this.connection = connection;
        this.isBusy  = isBusy;
    }

    /**
     * 释放 管道连接 对象
     */
    public void free(){
        this.isBusy = false;
    }

    public ResultSet queryBySql(String sql){
        ResultSet result = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            result = ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
