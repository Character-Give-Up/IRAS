package org.character.iras.DataAccess.Interfaces;

import org.character.iras.Application;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 数据连接层最高层接口
 */
public interface DataAccess {
    /**
     * 获取数据库连接模板对象。此方法拥有默认实现，用于返回模板，通常不需要重写或覆盖。
     * @return 数据库连接模板对象
     */
    default JdbcTemplate getJdbcTemplate(){
        return Application.template;
    }
}
