package com.library.pro.dao.impl;

import com.library.pro.dao.LogsDao;
import com.library.pro.model.po.Logs;
import com.library.pro.utils.DruidUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @className: BooksDaoImpl <br/>
 * @description: 日志持久层接口实现 <br/>
 * @author: xh <br/>
 * @date: 2023/05/20 <br/>
 * @version: 1.0.0 <br/>
 */

public class LogsDaoImpl implements LogsDao {

    private final QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public List<Logs> getAllLogs() {
        List<Logs> logsList = null;
        try {
            logsList = queryRunner.query("SELECT * FROM logs", new BeanListHandler<>(Logs.class, new BasicRowProcessor(new GenerousBeanProcessor())));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logsList;
    }

    @Override
    public Logs getLogsById(Integer id) {
        Logs logs = null;
        try {
            logs = queryRunner.query("SELECT * FROM logs WHERE id = ?", new BeanHandler<>(Logs.class, new BasicRowProcessor(new GenerousBeanProcessor())), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return logs;
    }

    @Override
    public void addLogs(Logs logs) {
        try {
            queryRunner.update("INSERT INTO logs (user_id, action, description, create_time) VALUES (?, ?, ?, ?)",
                    logs.getUserId(), logs.getAction(), logs.getDescription(), logs.getCreateTime());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateLogs(Logs logs) {
        try {
            queryRunner.update("UPDATE logs SET userId = ?, action = ?, description = ?, createTime = ? WHERE id = ?",
                    logs.getUserId(), logs.getAction(), logs.getDescription(), logs.getCreateTime(), logs.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLogs(Integer id) {
        try {
            queryRunner.update("DELETE FROM logs WHERE id = ?", id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
