package com.library.pro.dao.impl;

import com.library.pro.dao.ShowTableDao;
import com.library.pro.model.vo.*;
import com.library.pro.utils.DruidUtils;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.GenerousBeanProcessor;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @className: ShowTableDaoImpl <br/>
 * @description: 首页图表持久层实现类 <br/>
 * @author: CatKitty 33641 <br/>
 * @date: 2023/05/23 <br/>
 * @version: 1.0.0 <br/>
 */
public class ShowTableDaoImpl implements ShowTableDao {

    private final QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public List<CategoryCountVO> selectCategoryCountByMonth(int month) throws SQLException {
        String sql = "SELECT c.NAME as category, count(*) as count FROM loans l LEFT JOIN book_category bc ON l.book_id = bc.book_id LEFT JOIN categories c ON bc.category_id = c.id WHERE DATE(FROM_UNIXTIME( l.loan_date / 1000 )) >= DATE_SUB( CURDATE(), INTERVAL ? MONTH ) GROUP BY c.NAME";
        return queryRunner.query(sql, new BeanListHandler<>(CategoryCountVO.class), month);
    }

    @Override
    public List<BorrowRateVO> selectBorrowRateByMonth(int month) throws SQLException {
        String sql = "SELECT " +
                " month_list.MONTH as month, " +
                " IFNULL( borrow_count, 0 ) AS borrow_count, " +
                " IFNULL( return_count, 0 ) AS return_count, " +
                " IFNULL( return_rate, 0 ) AS return_rate  " +
                "FROM " +
                " ( " +
                " SELECT " +
                "  DATE_FORMAT( DATE_SUB( CURDATE(), INTERVAL n MONTH ), '%Y-%m' ) AS MONTH  " +
                " FROM " +
                "  ( SELECT 0 AS n UNION SELECT 1 UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5 UNION SELECT 6 ) AS month_offset  " +
                " ) AS month_list " +
                " LEFT JOIN ( " +
                " SELECT " +
                "  DATE_FORMAT( FROM_UNIXTIME( loan_date / 1000 ), '%Y-%m' ) AS MONTH, " +
                "  SUM( CASE WHEN `status` = 1 OR `status` = 2 OR `status` = 3 OR `status` = 0 THEN 1 ELSE 0 END ) AS borrow_count, " +
                "  SUM( CASE WHEN `status` = 3 THEN 1 ELSE 0 END ) AS return_count, " +
                "  IFNULL( " +
                "   SUM( CASE WHEN `status` = 3 THEN 1 ELSE 0 END ) * 100 / SUM( CASE WHEN `status` = 1 OR `status` = 2 OR `status` = 3 THEN 1 ELSE 0 END ), " +
                "   0  " +
                "  ) AS return_rate  " +
                " FROM " +
                "  loans  " +
                " WHERE " +
                "  DATE( " +
                "  FROM_UNIXTIME( loan_date / 1000 )) >= DATE_SUB( CURDATE(), INTERVAL ? MONTH )  " +
                " GROUP BY " +
                " MONTH  " +
                " ) AS loan_stats ON month_list.MONTH = loan_stats.MONTH  " +
                "ORDER BY " +
                " month_list.MONTH";
        return queryRunner.query(sql, new BeanListHandler<>(BorrowRateVO.class, new BasicRowProcessor(new GenerousBeanProcessor())), month);
    }

    @Override
    public List<CategorySizeVO> selectCategorySize() throws SQLException {
        String sql = "SELECT " +
                " c.NAME as name, " +
                " count(*) as count  " +
                "FROM " +
                " book_category bc " +
                " LEFT JOIN categories c ON bc.category_id = c.id  " +
                "GROUP BY " +
                " c.NAME";
        return queryRunner.query(sql, new BeanListHandler<>(CategorySizeVO.class));
    }

    @Override
    public List<CategoryBookDurationVO> selectCategoryBookDuration() throws SQLException {
        String sql = "SELECT " +
                " c.NAME as name, " +
                " AVG( " +
                "  DATEDIFF( " +
                "   FROM_UNIXTIME( return_date / 1000 ), " +
                "  FROM_UNIXTIME( loan_date / 1000 ))) AS borrow_duration  " +
                "FROM " +
                " loans l " +
                " LEFT JOIN book_category bc ON l.book_id = bc.book_id " +
                " LEFT JOIN categories c ON bc.category_id = c.id  " +
                "WHERE " +
                " `status` = 3  " +
                "GROUP BY " +
                " l.book_id;";
        return queryRunner.query(sql, new BeanListHandler<>(CategoryBookDurationVO.class, new BasicRowProcessor(new GenerousBeanProcessor())));
    }

    @Override
    public List<HotBookVO> selectHotBook(int size, int month) throws SQLException {
        String sql = "SELECT " +
                " b.id as book_id, " +
                " b.title AS book_name, " +
                " COUNT(*) AS borrow_count,  " +
                " b.current_stock AS current_stock  " +
                "FROM " +
                " loans l " +
                " JOIN books b ON l.book_id = b.id  " +
                "WHERE " +
                " DATE( " +
                " FROM_UNIXTIME( l.loan_date / 1000 )) >= DATE_SUB( CURDATE(), INTERVAL ? MONTH )  " +
                "GROUP BY " +
                " book_name  " +
                "ORDER BY " +
                " borrow_count DESC  " +
                " LIMIT ?;";
        return queryRunner.query(sql, new BeanListHandler<>(HotBookVO.class, new BasicRowProcessor(new GenerousBeanProcessor())), month, size);
    }
}
