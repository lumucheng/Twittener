/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twittener.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBUtils {
    
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public static final String DB_URL = 
            "jdbc:mysql://muchengdbinstance.ck4ob98s6wyd.ap-southeast-1.rds.amazonaws.com/twittener";
    public static final String DB_USERNAME = "awsmucheng";
    public static final String DB_PASSWORD = "64543104Mucheng";
    
    public static void closeDBResource(ResultSet rs) {
        try {
            rs.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    public static void closeDBResource(Connection conn) {
        try {
            conn.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeDBResource(Statement stmt) {
        try {
            stmt.close();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(DBUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
