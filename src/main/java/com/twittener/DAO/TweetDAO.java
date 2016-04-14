package com.twittener.DAO;

import com.twittener.Entity.TweetHyperlink;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;

public class TweetDAO {
    
    public void insertTweet(Status tweet) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DBUtils.JDBC_DRIVER);
            connection = DriverManager.getConnection(DBUtils.DB_URL, 
                    DBUtils.DB_USERNAME, DBUtils.DB_PASSWORD);

            String sql = "INSERT INTO TBL_TWEETS (tweet_id, text, created_at, "
                    + "twitter_uid, twitter_uname) "
                    + "VALUES (?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, tweet.getId());
            preparedStatement.setString(2, tweet.getText().replaceAll("\\S+://\\S+", ""));
            preparedStatement.setTimestamp(3, new Timestamp(tweet.getCreatedAt().getTime()));
            preparedStatement.setLong(4, tweet.getUser().getId());
            preparedStatement.setString(5, tweet.getUser().getScreenName());
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
