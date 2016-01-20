package com.twittener.DAO;

import com.twittener.Entity.TopicAccount;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import twitter4j.Status;

public class TopicAccountsDAO {
    
    public ArrayList<TopicAccount> getAccountsByTopic(int topicId) {
        
        ArrayList<TopicAccount> accountsList = new ArrayList<>();
        String sql = "SELECT * FROM " + TopicAccount.TBL_NAME + 
                " WHERE " + TopicAccount.COL_TOPIC_ID + " = " + 
                topicId + ";";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            Class.forName(DBUtils.JDBC_DRIVER);
            connection = DriverManager.getConnection(
                    DBUtils.DB_URL,DBUtils.DB_USERNAME, DBUtils.DB_PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                TopicAccount topicAccount = new TopicAccount();
                topicAccount.setId(resultSet.getInt(TopicAccount.COL_ID));
                topicAccount.setName(resultSet.getString(TopicAccount.COL_NAME));
                topicAccount.setScreenName(resultSet.getString(TopicAccount.COL_SCREENNAME));
                topicAccount.setTwitterId(resultSet.getLong(TopicAccount.COL_TWITTER_ID));
                topicAccount.setLatestTweetId(resultSet.getLong(TopicAccount.COL_LATEST_TWEET_ID));
                topicAccount.setTopicId(resultSet.getInt(TopicAccount.COL_TOPIC_ID));
                accountsList.add(topicAccount);
            }
        } 
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(TopicAccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DBUtils.closeDBResource(resultSet);
            DBUtils.closeDBResource(statement);
            DBUtils.closeDBResource(connection);
        }
        
        return accountsList;
    }
    
    public void updateMaxTweetId(Long twitterId) {
        CallableStatement statement = null;
        Connection connection = null;
        
        try {
            String sql = "{call UpdateTweetId(?)}";
            Class.forName(DBUtils.JDBC_DRIVER);
            connection = DriverManager.getConnection(
                    DBUtils.DB_URL,DBUtils.DB_USERNAME, DBUtils.DB_PASSWORD);
            statement = connection.prepareCall(sql);
            statement.setLong(1, twitterId);
            statement.execute();
        }
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            Logger.getLogger(TopicAccountsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally {
            DBUtils.closeDBResource(statement);
            DBUtils.closeDBResource(connection);
        }
    }
    
    public void updateMaxTweetId(Long maxId, Long twitterId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DBUtils.JDBC_DRIVER);
            connection = DriverManager.getConnection(DBUtils.DB_URL, DBUtils.DB_USERNAME, DBUtils.DB_PASSWORD);

            String sql = "UPDATE " + TopicAccount.TBL_NAME + " SET "
                    + TopicAccount.COL_LATEST_TWEET_ID + " = ? WHERE "
                    + TopicAccount.COL_TWITTER_ID + " = ?;"; 
                    
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, maxId);
            preparedStatement.setLong(2, twitterId);
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
