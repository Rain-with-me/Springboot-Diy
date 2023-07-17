package com.lu.edu.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Author: 雨同我
 * @Description:
 * @DateTime: 2022/10/13 12:42
 **/

@Configuration
@MapperScan("com.lu.edu.mapper")
// 开启事务
@EnableTransactionManagement
public class MybatisPlusConfig {

    /**
     * @Description:  //配置分页插件
     *                // 官网地址配置: [https://baomidou.com/pages/97710a/#%E5%B1%9E%E6%80%A7%E4%BB%8B%E7%BB%8D]
     * @Author: 雨同我
     * @DateTime: 2023/7/17 14:44
    */

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        // paginationInnerInterceptor.setOverflow(false);
        // 设置最大单页限制数量，默认不限条，-1 不受限制
        // paginationInnerInterceptor.setMaxLimit(500L);


        //数据库类型是MySql，因此参数填写DbType.MYSQL
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
