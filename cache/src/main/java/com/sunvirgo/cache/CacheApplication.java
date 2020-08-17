package com.sunvirgo.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 方法说明:
 * 一.搭建基本环境
 * 1.导入数据库文件，创建出department和employee表
 * 2.创建javabean封装数据
 * 3.整合Mybatis操作数据库：
 *    1.配置数据源信息
 *    2.使用注解版Mybatis
 *       1)@MapperScan指定需要扫描的mapper接口所在的包
 * 二.快速体验缓存
 *    步骤：
 *        1.开启基于注解的缓存
 *        2.标注缓存注解即可
 *           @Cacheable
 *           @CacheEvict
 *           @Cacheput
 * 默认使用的是ConcurrentMapCacheManager==ConcurrentMapCache,将数据保存在ConcurrentMap<Onject></>
 * 开发使用缓存中间件：redis,memcached,ehcacahe
 * 1.安装redis 使用docker
 * 2.引入redis的stater
 * @author : 黄刚
 * @date : 2020/7/26 21:24
 * @para :
 * @return :
 */
@EnableCaching
@MapperScan("com.sunvirgo.cache.mapper")
@SpringBootApplication
public class CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }

}
