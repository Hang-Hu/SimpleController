package com.huhang.framework.database;

import java.sql.Connection;
import java.util.List;

/**
 * Created by joanna on 4/12/17.
 */
public interface SessionFactory {
    public Connection getConnection();
    public List<String> getMappingLocations();
}
