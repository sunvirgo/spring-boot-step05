package com.sunvirgo.cache.controller;

import com.sunvirgo.cache.bean.Employee;
import com.sunvirgo.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类说明:
 *
 * @author : 黄刚
 * @date : 2020/7/26 22:21
 **/
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    /**
     * 方法说明:
     * @author : 黄刚
     * @date : 2020/7/26 22:22
     * @para : []
     * @return : com.sunvirgo.bean.Employee
     */
    @GetMapping("/emp/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id){
        Employee emp = employeeService.getEmp(id);
        return  emp;
    }

    /**
     * 方法说明:
     * @author : 黄刚
     * @date : 2020/8/4 21:39
     * @para : []
     * @return : com.sunvirgo.cache.bean.Employee
     */
    @GetMapping("/emp")
    public Employee updateEmployee(Employee employee){
        Employee emp = employeeService.updateEmp(employee);
        return  emp;
    }
}
