package com.sunvirgo.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * 方法说明: 搭建基本环境
 * 1.导入数据库文件，创建出department和employee表
 * 2.创建javabean封装数据
 * 3.整合Mybatis操作数据库：
 *    1.配置数据源信息
 *    2.使用注解版Mybatis
 *       1)@MapperScan指定需要扫描的mapper接口所在的包
 * @author : 黄刚
 * @date : 2020/7/26 21:24
 * @para :
 * @return :
 */
@MapperScan("com.sunvirgo.cache.mapper")
@SpringBootApplication
public class CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

}
