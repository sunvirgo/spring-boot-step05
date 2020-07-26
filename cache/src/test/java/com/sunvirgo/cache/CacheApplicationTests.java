package com.sunvirgo.cache;

import com.sunvirgo.cache.bean.Employee;
import com.sunvirgo.cache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class CacheApplicationTests {
    @Resource
    EmployeeMapper employeeMapper;
    @Test
    void contextLoads() {
        Employee emp = employeeMapper.getEmpById(1);
        System.out.println(emp);
    }

}
