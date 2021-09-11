package com.edusys.utils;

import java.sql.SQLException;

public class XJdbc {
    static String driver = "com.microsoft.sqlserver.SQLServerDriver";
    static String dburl = "jdbs:sqlserver://localhost;database=EduSys";
    static String user = "sa";
    static String pass = "";
    static{
        try{
            Class.forName(driver);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    //Truy van
    public static void query() throws SQLException{
        
    }
    //Thao tao
    public static void update() throws SQLException{
    }
}
