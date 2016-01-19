/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twittener.DAO;

import com.twittener.Entity.TweetHyperlink;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TweetHyperlinkDAO {
    
    public void insertHyperlink(TweetHyperlink tweetHyperlink) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DBUtils.JDBC_DRIVER);
            connection = DriverManager.getConnection(DBUtils.DB_URL, DBUtils.DB_USERNAME, DBUtils.DB_PASSWORD);

            String sql = "INSERT INTO " + TweetHyperlink.TBL_NAME  
                    + " (" + TweetHyperlink.COL_HYPERLINK + ", "
                    + TweetHyperlink.COL_TWEET_ID 
                    + ") VALUES (?, ?);";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, tweetHyperlink.getHyperlink());
            preparedStatement.setLong(2, tweetHyperlink.getTweet_Id());
            preparedStatement.executeUpdate();
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(TweetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DBUtils.closeDBResource(preparedStatement);
            DBUtils.closeDBResource(connection);
        }
    }
}
