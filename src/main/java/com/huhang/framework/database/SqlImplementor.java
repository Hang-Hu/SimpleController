package com.huhang.framework.database;

import java.util.List;
import java.util.Map;

/**
 * Created by joanna on 4/12/17.
 */
public interface SqlImplementor {
    public List<Map<String, String>> executeQuery(String sql, Map<String, String> tableProperties);
    public void executeUpdate(String sql);
}
