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

    //获取所有日志
    List<Logs> getAllLogs();

    //根据id获取日志
    Logs getLogsById(Integer id);

    //添加日志
    void addLogs(Logs logs);

    //更新日志
    void updateLogs(Logs logs);

    //根据id删除日志
    void deleteLogs(Integer id);

}
