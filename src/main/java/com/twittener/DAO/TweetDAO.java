/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twittener.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;

/**
 *
 * @author Mucheng
 */
public class TweetDAO {
    
    public void insertTweet(Status tweet) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DAOUtil.JDBC_DRIVER);
            connection = DriverManager.getConnection(DAOUtil.DB_URL, DAOUtil.DB_USERNAME, DAOUtil.DB_PASSWORD);

            String sql = "INSERT INTO TBL_TWEETS (tweet_id, text, created_at, "
                    + "twitter_uid, twitter_uname) "
                    + "VALUES (?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, tweet.getId());
            preparedStatement.setString(2, tweet.getText().replaceAll("\\S+://\\S+", ""));
            preparedStatement.setDate(3, new Date(tweet.getCreatedAt().getTime()));
            preparedStatement.setLong(4, tweet.getUser().getId());
            preparedStatement.setString(5, tweet.getUser().getScreenName());

            int rowsAffected = preparedStatement.executeUpdate();
        } 
        catch (Exception ex) {
            System.out.println(ex);
            Logger.getLogger(TweetDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            try {
                preparedStatement.close();
                connection.close();
            } 
            catch (SQLException ex) {
                Logger.getLogger(TweetDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
