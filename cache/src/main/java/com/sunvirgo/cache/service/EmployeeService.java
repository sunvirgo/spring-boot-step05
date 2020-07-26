package com.sunvirgo.cache.service;

import com.sunvirgo.cache.bean.Employee;
import com.sunvirgo.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 类说明:
 *
 * @author : 黄刚
 * @date : 2020/7/26 22:14
 **/
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 方法说明:
     * @author : 黄刚
     * @date : 2020/7/26 22:18
     * @para :
     * @return :
     */
    public Employee getEmp(Integer id){
        Employee emp = employeeMapper.getEmpById(id);
        System.out.println("查询"+id+"员工");
        return  emp;
    }
}
