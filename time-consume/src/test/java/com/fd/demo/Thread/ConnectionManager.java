package com.fd.demo.Thread;

import javax.websocket.Session;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author ：zxq
 * @date ：Created in 2020/9/8 17:16
 */

public class ConnectionManager {

    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<Connection>(){
        @Override
        protected Connection initialValue(){
            Connection conn = null;
            try{
                conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/test",
                        "root",
                        "root"
                );
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return conn;
        }
    };

    public static Connection getConnection(){
        return connectionHolder.get();
    }

    public static void setConnectionHolder(Connection conn){
        connectionHolder.set(conn);
    }

}
