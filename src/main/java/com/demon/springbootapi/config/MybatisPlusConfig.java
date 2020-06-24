package com.demon.springbootapi.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: Demon
 * @date 2019/8/10 12:09
 * @description: mybatis-plus 配置
 */
@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {

    /**
     * 分页插件
     * paginationInterceptor.setLimit(你的最大单页限制数量，默认 500 条，小于 0 如 -1 不受限制);
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLimit(-1);
        paginationInterceptor.setDbType(DbType.MYSQL);
        return paginationInterceptor;
    }

}
