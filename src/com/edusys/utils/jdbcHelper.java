package com.edusys.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jdbcHelper {
    static String driver = "com.microsoft.sqlserver.SQLServerDriver";
    static String dburl ="jdbc:sqlserver://localhost:1433;databaseName=EduSys";
    static String user = "sa";
    static String pass = "";
   
//    static{
//        try{
//            Class.forName("com.microsoft.sqlserver.SQLServerDriver");
//        }catch(Exception e){
//            System.out.println("Loi driver");
//            throw new RuntimeException(e);
//        }
//    }
    
    public static PreparedStatement getStmt(String sql, Object...args) throws SQLException{
        Connection conn = DriverManager.getConnection(dburl,user,pass);
        PreparedStatement stmt;
        if(sql.trim().startsWith("{")){
            stmt = conn.prepareCall(sql);//PROC
            
        }else{
            stmt = conn.prepareStatement(sql);//SQL
        }
        for(int i = 0; i <args.length;i++){
            stmt.setObject(i+1, args[i]);
        }
        return stmt;
    }
    public static int update(String sql, Object...args) throws SQLException{
        try{
            PreparedStatement stmt = jdbcHelper.getStmt(sql, args);
            try{
                return stmt.executeUpdate();
            }finally{
                stmt.getConnection().close();
            }
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    public static ResultSet query(String sql, Object...args) throws SQLException{
        PreparedStatement stmt = jdbcHelper.getStmt(sql, args);
        return stmt.executeQuery();
    }
    public static Object value(String sql, Object...args){
        try{
            ResultSet rs = jdbcHelper.query(sql, args);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
            return  null;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
