package com.huhang.framework.database;

/**
 * Created by joanna on 4/12/17.
 */
public class DataSourceImpl implements DataSource{
    private String driverClass;
    private String jdbcUrl;
    private String user;
    private String password;
    @Override
    public String getDriverClass() {
        return driverClass;
    }
    @Override
    public String getJdbcUrl() {
        return jdbcUrl;
    }
    @Override
    public String getUser() {
        return user;
    }
    @Override
    public String getPassword() {
        return password;
    }
}
