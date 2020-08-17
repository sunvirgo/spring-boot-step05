package com.sunvirgo.cache;

import com.sunvirgo.cache.bean.Employee;
import com.sunvirgo.cache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class CacheApplicationTests {
    @Resource
    EmployeeMapper employeeMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;//操作字符串
    @Autowired
    RedisTemplate redisTemplate;//k-v都是对象
    @Resource
    RedisTemplate<String,Employee> myRedisTemplate;
    
    /**
     * 方法说明:  Redis常见的五大数据类型
     * String(字符串);List（列表）;Set(集合);Hash（散列）;ZSet（有序集合）
     *         stringRedisTemplate.opsForValue();[String(字符串)]
     *         stringRedisTemplate.opsForList();[List(列表)]
     *         stringRedisTemplate.opsForSet();[Set(集合)]
     *         stringRedisTemplate.opsForHash();[Hash(散列)]
     *         stringRedisTemplate.opsForZSet();[ZSet(有序集合)]
     * @author : 黄刚
     * @date : 2020/8/17 22:44
     * @para : []
     * @return : void
     */ 
    @Test
    void contextLoads() {
        //给Redis中保存数据
        stringRedisTemplate.opsForValue().append("msg","hello");
        String msg = stringRedisTemplate.opsForValue().get("msg");
        System.out.println(msg);
        stringRedisTemplate.opsForList().leftPush("mylist","1");
        stringRedisTemplate.opsForList().leftPush("mylist","2");
        stringRedisTemplate.opsForList().leftPush("mylist","3");

        Employee emp = employeeMapper.getEmpById(1);
        System.out.println(emp);
        //测试保存对象
        //默认如果保存对象使用JDK序列化机制，序列化后的数据保存到redis中
        redisTemplate.opsForValue().set("emp-01",emp);
        //1.将数据以json方式保存
        //2.RedisTemplate默认的序列化规则,改变默认的序列化规则
        myRedisTemplate.opsForValue().set("emp-01",emp);


    }

}
