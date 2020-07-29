package com.sunvirgo.cache.service;

import com.sunvirgo.cache.bean.Employee;
import com.sunvirgo.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
     * 方法说明:将方法的运行结果进行缓存：以后再要相同的数据，直接从缓存中获取，不用调方法
     * Cachemanager管理多个cache组件的，对缓存的真正的CRUD操作在Cache组件中，
     * 每一个缓存组件有自己唯一一个名字，几个属性，
     *      cacheNames/value:指定缓存组件的名字
     *      key:缓存数据使用的key，可以用它来指定。默认是使用方法参数的值   1-方法的返回值
     *          编写spel:#id-参数id得值 #a0  #p0  #root.args[0]
     *          keyGenerarator:key的生成器，可以自己指定key的生成器的组件id
     *           keyGenerarator/key:二选一使用
     *           cacheManager:指定缓存管理器,或者cacheResolver指定获取解析器
     *           condition:指定符合条件的情况下才缓存； condition = "#id>0"
     *           unless否定缓存，当unless指定的条件为true,方法的返回值就不会被缓存，可以获取到结果进行判断
     *                    unless=“#result == null”
     *           sync:是否使用异步模式
     * @author : 黄刚
     * @date : 2020/7/26 22:18
     * @para :
     * @return :
     */
    @Cacheable(cacheNames = "emp")
    public Employee getEmp(Integer id){
        Employee emp = employeeMapper.getEmpById(id);
        System.out.println("查询"+id+"员工");
        return  emp;
    }
}
