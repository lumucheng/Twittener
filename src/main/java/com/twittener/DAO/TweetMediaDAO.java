/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twittener.DAO;

import com.twittener.Entity.TweetMedia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Mucheng
 */
public class TweetMediaDAO {
    
    public void insertTweetMedia(TweetMedia media) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DBUtils.JDBC_DRIVER);
            connection = DriverManager.getConnection(DBUtils.DB_URL, DBUtils.DB_USERNAME, DBUtils.DB_PASSWORD);

            String sql = "INSERT INTO " + TweetMedia.TBL_NAME 
                    + "(" + TweetMedia.COL_MEDIA_ID + ", " 
                    + TweetMedia.COL_MEDIA_TYPE + ", "
                    + TweetMedia.COL_MEDIA_URL + ", "
                    + TweetMedia.COL_TWEET_ID + "," 
                    + TweetMedia.COL_MEDIAL_VIDEOURL + ") "
                    + "VALUES (?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, media.getMedia_Id());
            preparedStatement.setString(2, media.getMedia_Type());
            preparedStatement.setString(3, media.getMedia_Url());
            preparedStatement.setLong(4, media.getTweet_Id());
            preparedStatement.setString(5, media.getMedia_VideoUrl());
            preparedStatement.executeUpdate();
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(TweetMediaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DBUtils.closeDBResource(preparedStatement);
            DBUtils.closeDBResource(connection);
        }
    }
}
