package com.huhang.framework.database;

/**
 * Created by joanna on 4/12/17.
 */
public interface DataSource {
    public String getDriverClass();

    public String getJdbcUrl();

    public String getUser();

    public String getPassword();
}
