package com.library.pro.dao;

import com.library.pro.model.po.Logs;

import java.util.List;

/**
 * @className: LogsDao <br/>
 * @description: 日志持久层接口 <br/>
 * @author: xh <br/>
 * @date: 2023/05/20 <br/>
 * @version: 1.0.0 <br/>
 */
public interface LogsDao {

    List<Logs> getAllLogs();
    Logs getLogsById(Integer id);
    void addLogs(Logs logs);
    void updateLogs(Logs logs);
    void deleteLogs(Integer id);

}
