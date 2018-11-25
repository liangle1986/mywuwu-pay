package com.mywuwu.common.util;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 曹文杰 on 2018/7/19.
 */
public class DBUtil {

    private static final String driver = "com.mysql.jdbc.Driver";
    private static final String url = "jdbc:mysql://localhost:3306/shiro?useUnicode=true&characterEncoding=utf8";
    private static final String username = "root";
    private static final String password = "123456";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnect(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 根据sql查询返回map列表
     * @param sql
     * @return
     */
    public static List<Map<String,Object>> Query(String sql){

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSetMetaData rsmd = null;
        ResultSet rs = null;
        List<Map<String,Object>> list = new ArrayList<>();
        try {
            connection = getConnect();
            if (connection!=null){
                ps = connection.prepareStatement(sql);
                rsmd =  ps.getMetaData();
                int columnNum = rsmd.getColumnCount();
                rs = ps.executeQuery();

                while (rs.next()){
                    Map<String,Object> map = new HashMap<>();
                    for (int i = 1; i <=columnNum ; i++) {
                        map.put(rsmd.getColumnName(i),rs.getObject(i));
                    }
                    list.add(map);
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            try {
                if (connection!=null){
                    connection.close();
                }
                if (ps!=null){
                    ps.close();
                }
                if (rs!=null){
                    rs.close();
                }
              } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;

    }

    /**
     * 根据sql查询返回String列表
     * @param sql
     * @return
     */
    public static List<String> Query2(String sql){

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> list = new ArrayList<>();
        try {
            connection = getConnect();
            if (connection!=null){
                ps = connection.prepareStatement(sql);
                rs = ps.executeQuery();

                while (rs.next()){
                    list.add(rs.getString(1));
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            try {
                if (connection!=null){
                    connection.close();
                }
                if (ps!=null){
                    ps.close();
                }
                if (rs!=null){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;

    }


    /**
     * 获取所有列的名称
     * @param sql
     * @return
     */
    public static List<String> getMetaData(String sql){
        Connection connection = null;
        PreparedStatement ps = null;
        List<String> list = new ArrayList<>();
        try {
            connection = getConnect();
            if (connection!=null){
                ps = connection.prepareStatement(sql);
                ResultSetMetaData rsmd =  ps.getMetaData();
                int columnNum = rsmd.getColumnCount();
                for (int i = 1; i <=columnNum ; i++) {
                    list.add(rsmd.getColumnName(i));
                }
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            try {
                if (connection!=null){
                    connection.close();
                }
                if (ps!=null){
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;

    }


    /**
     * 添加数据
     * @param sql
     * @return
     */
    public static int insert(String sql){

        Connection connection = null;
        PreparedStatement ps = null;
        int result = 0;

        try {
            connection = getConnect();
            if (connection!=null){
                ps = connection.prepareStatement(sql);
                result = ps.executeUpdate();
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            try {
                if (connection!=null){
                    connection.close();
                }
                if (ps!=null){
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
        return result;

    }

    /**
     * 执行存储过程
     * @param sql
     */
    public static void procedure(String sql){
        Connection conn = null;
        CallableStatement cs = null;

        conn = getConnect();
        if(conn!=null){
            try {
                cs = conn.prepareCall(sql);
                cs.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
                try {
                    if (conn!=null){
                        conn.close();
                    }
                    if (cs!=null){
                        cs.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取序列
     */
    public static String getSequence(String sql){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSetMetaData rsmd = null;
        ResultSet rs = null;
        String result = "";
        try {
            connection = getConnect();
            if (connection!=null){
                ps = connection.prepareStatement(sql);
                rs = ps.executeQuery();
                if(rs.next()){
                    result = rs.getString(1);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            try {
                if (connection!=null){
                    connection.close();
                }
                if (ps!=null){
                    ps.close();
                }
                if (rs!=null){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }




}
