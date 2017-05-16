package com.huhang.framework.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by joanna on 4/12/17.
 */
public class SessionFactoryImpl implements SessionFactory{
    private DataSource dataSource;
    private Connection connection;
    private String mappingLocation;
    @Override
    public Connection getConnection(){
        if(connection==null)
            openConnection();
        return connection;
    }

    @Override
    public List<String> getMappingLocations() {
        List<String> mappingLocations=new LinkedList<>();
        mappingLocations.add(mappingLocation);
        return mappingLocations;
    }

    private void openConnection(){
        try {
            Class.forName(dataSource.getDriverClass());
            System.out.println("Connecting to a database...");
            this.connection = DriverManager.getConnection(dataSource.getJdbcUrl(),
                    dataSource.getUser(),
                    dataSource.getPassword());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private boolean closeConnection(){
        if(connection !=null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if(connection !=null)
            closeConnection();
    }

}
