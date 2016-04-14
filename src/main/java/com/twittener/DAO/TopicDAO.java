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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Mucheng
 */
public class TopicDAO {
    
    public ArrayList<Topic> getAllTopics() {
        ArrayList<Topic> topicList = new ArrayList<>();
        String sql = "SELECT * FROM TBL_TOPIC WHERE twitter_id = 0;";
        Connection connection = null;
        Statement statement = null;
        
        try {
            Class.forName(DBUtils.JDBC_DRIVER);
            connection = DriverManager.getConnection(
                    DBUtils.DB_URL,DBUtils.DB_USERNAME, DBUtils.DB_PASSWORD);
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
        catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }
        return topicList;
    }
}
