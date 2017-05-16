package com.huhang.framework.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by joanna on 4/12/17.
 */
public class MysqlImplementor implements SqlImplementor{
    SessionFactory sessionFactory;

    //select query
    public List<Map<String, String>> executeQuery(String sql, Map<String, String> tableProperties){
        List<Map<String, String>> objectList=new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = sessionFactory.getConnection().createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                //retieve by column name
                for(Map.Entry<String, String> entry: tableProperties.entrySet()){
                    entry.getKey();
                    entry.setValue(resultSet.getString(entry.getKey()));
                }
                objectList.add(tableProperties);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(statement);
            close(resultSet);
        }
        return objectList;
    }

    // Executes the given SQL statement,
    // which may be an INSERT, UPDATE, or DELETE statement
    // or an SQL statement that returns nothing, such as an SQL DDL statement.
    public void executeUpdate(String sql){
        Statement statement = null;
        try {
            statement = sessionFactory.getConnection().createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(statement);
        }

    }
    private void close(AutoCloseable autoCloseable){
        if(autoCloseable!=null)
            try {
                autoCloseable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
