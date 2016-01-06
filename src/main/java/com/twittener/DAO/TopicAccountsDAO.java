/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twittener.DAO;

import com.twittener.Entity.TopicAccount;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mucheng
 */
public class TopicAccountsDAO {
    
    public ArrayList<TopicAccount> getAccountsByTopic(int topicId) {
        ArrayList<TopicAccount> accountsList = new ArrayList<TopicAccount>();
        String sql = "SELECT * FROM TBL_TOPIC_ACCOUNTS WHERE topic_id = " 
                + topicId + ";";
        Connection connection = null;
        Statement statement = null;
        
        try {
            Class.forName(DAOUtil.JDBC_DRIVER);
            connection = DriverManager.getConnection(
                    DAOUtil.DB_URL,DAOUtil.DB_USERNAME, DAOUtil.DB_PASSWORD);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

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

            connection.close();
        } 
        catch (Exception ex) {
            System.out.println(ex);
        }
        return accountsList;
    }
}
