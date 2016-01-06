/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.twittener.DAO;

import com.twittener.Entity.Topic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mucheng
 */
public class TopicDAO {
    
    public ArrayList<Topic> getAllTopics() {
        ArrayList<Topic> topicList = new ArrayList<Topic>();
        String sql = "SELECT * FROM TBL_TOPIC;";
        Connection connection = null;
        Statement statement = null;
        
        try {
            Class.forName(DAOUtil.JDBC_DRIVER);
            connection = DriverManager.getConnection(
                    DAOUtil.DB_URL,DAOUtil.DB_USERNAME, DAOUtil.DB_PASSWORD);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Topic topic = new Topic();
                topic.setId(resultSet.getInt(Topic.COL_ID));
                topic.setTopic(resultSet.getString(Topic.COL_TOPIC));
                topicList.add(topic);
            }

            connection.close();
        } 
        catch (Exception ex) {
            System.out.println(ex);
        }
        return topicList;
    }
}
