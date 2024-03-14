package com.buercorp.longxiaolin;

import com.buercorp.longxiaolin.utils.DruidUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author 小林
 * Create on 2024/3/14 10:41
 */
public class DruidUtilTest {
    @Test
    public void getDataSource() throws SQLException {
        // 获取druid的数据库连接池
        DataSource dataSource = DruidUtil.getDataSource();

        //测试插入数据
        QueryRunner queryRunner = new QueryRunner(DruidUtil.getDataSource());
        String sql = "insert into user(`username`, `password`, `address`, `nickname`, `gender`, `email`) values (?,?,?,?,?,?)";

        //将user用户存储的数据 插入 到数据库中
        int i = queryRunner.update(sql, "testuser05", "483212", "深圳", "DevOps海洋的渔夫", "male", "123@qq.com");
        System.out.println("插入的数据行数: " + i);
    }
}
