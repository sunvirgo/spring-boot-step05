package com.sunvirgo.cache.service;

import com.sunvirgo.cache.bean.Employee;
import com.sunvirgo.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

/**
 * 类说明:
 *
 * @author : 黄刚
 * @date : 2020/7/26 22:14
 **/
@CacheConfig(cacheNames = "emp")//抽取缓存的公共配置
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 方法说明:将方法的运行结果进行缓存：以后再要相同的数据，直接从缓存中获取，不用调方法
     * Cachemanager管理多个cache组件的，对缓存的真正的CRUD操作在Cache组件中，
     * 每一个缓存组件有自己唯一一个名字，几个属性：
     *      cacheNames/value:指定缓存组件的名字，将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存
     *      key:缓存数据使用的key，可以用它来指定。默认是使用方法参数的值   1-方法的返回值
     *          编写SpEl:#id-参数id得值 #a0  #p0  #root.args[0]
     *          getEmp[2]
     *
     *      keyGenerarator:key的生成器，可以自己指定key的生成器的组件id
     *           keyGenerarator/key:二选一使用
     *      cacheManager:指定缓存管理器,或者cacheResolver指定获取解析器
     *      condition:指定符合条件的情况下才缓存； condition = "#id>0"
     *                condition = "#a0>1" 第一个参数大于才缓存
     *      unless否定缓存，当unless指定的条件为true,方法的返回值就不会被缓存，可以获取到结果进行判断
     *                    unless=“#result == null”
     *      sync:是否使用异步模式
     * 原理：
     *  1.自动配置类
     *  2.缓存的配置类
     *   "org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration"
     *   "org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration"
     *   "org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration"
     *   "org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration"
     *   "org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration"
     *   "org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration"
     *   "org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration"
     *   "org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration"
     *   "org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration"
     *   "org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration"
     *  3.哪个配置类默认生效
     *  4.给容器中注册了一个CacheManager,ConcurrentMapCacheManager
     *  5.可以获取和创建ConcurrentMapCache类型的缓存组件，它的作用将数据保存在ConcurrentMap中
     *  运行流程：
     * @Cacheable:
     * 1.方法运行之前，先去查询Cache（缓存组件）按照cacheName指定的名字获取（cacheManager先获取相应的缓存）
     * 第一次获取缓存如果没有Cache会自动创建
     * 2.去Cache中查找缓存内容，使用一个key,默认就是方法的参数，key是按照某种策略生成的，默认是使用keyGenerator生成的，
     * 默认使用SimplekKeyGenerator生成key:
     *    如果没有参数：key=new SimpleKey();
     *    如果有一个参数：key=参数值；
     *    如果有多个参数：key=new SimpleKey(params);
     * 3.没查到缓存就调用目标方法
     * 4.将目标方法查到的结果，放进缓存中
     * @Cacheable标注的方法执行之前先来检查缓存中有没有这个数据，默认按照参数得值作为key去查询缓存，如果没有就运行方法并将
     * 结果放入缓存，以后再来调用就可以直接使用缓存中的数据；
     *
     * 核心：
     *    1）.使用CacheManager[ConcurrentMapCacheManager]按照名字得到Cache[ConcurrentMapCache]组件
     *    2）.key使用keyGenerator生成的，默认是SimplekKeyGenerator
     *
     * @author : 黄刚
     * @date : 2020/7/26 22:18
     * @para :
     * @return :
     */
    //key="#root.methodName+'['+#id+']'"
    //keyGenerator = "myKeyGenerator"
    @Cacheable(cacheNames = "emp",keyGenerator = "myKeyGenerator",condition = "#a0>1")
    public Employee getEmp(Integer id){
        Employee emp = employeeMapper.getEmpById(id);
        System.out.println("查询"+id+"员工");
        return  emp;
    }
    /**
     * 方法说明:
     * @CachePut:既调用方法，又更新缓存数据；同步更新缓存
     * 修改了数据库的某个数据，同事更新缓存；
     * 运用时机：
     * 1.先调用目标方法
     * 2.将目标方法的结果缓存起来
     * 测试步骤：
     * 1.查询2号员工，查询到的结果会放在缓存中
     *      key:1 value: lastName:张三
     * 2，以后查询还是以前的结果、
     * 3.更新2号员工；【lastName:lisi;gender:0】
     *      将方法的返回值也放进缓存了；
     *      key:传入的employee对象 值：返回的employee对象
     * 4.查询2号员工？
     *    应该是更新后的员工；
     *        key="#employee.id":使用传入的参数的员工id；
     *        key="#result.id":使用返回后的id
     *           @Cacheable的key是不能用#result.id
     *    为什么是没更新前的？【2号员工没有在缓存中更新】
     * @author : 黄刚
     * @date : 2020/8/4 21:38
     * @para : []
     * @return : com.sunvirgo.cache.bean.Employee
     */
    @CachePut(value = "emp", key = "#employee.id")
    public Employee updateEmp(Employee employee){
        System.out.println(employee.toString());
        employeeMapper.updateEmp(employee);
        return employee;
    }
    /**
     * 方法说明: @CacheEvict:清除缓存
     * key:指定要清除的数据
     * allEntires=true:指定清除这个缓存中的所有数据
     * beforeInvocation = false:缓存的清除是否在方法之前执行
     *    默认代表缓存清除操作是在方法执行之后执行；如果出现异常缓存就不会清除
     * beforeInvocation = true：
     *    代表缓存清除操作是在方法执行之前执行；无论是否出现异常，缓存都会清除
     * @author : 黄刚
     * @date : 2020/8/15 13:57
     * @para : []
     * @return : void
     */
    @CacheEvict(value = "emp",key = "#id")
    public void deleteEmp(Integer id){
        System.out.println("deleteEmp"+id);
        //employeeMapper.deleteEmpById(id);
    }

    /**
     * 方法说明:@Caching定义复杂的缓存
     * @author : 黄刚
     * @date : 2020/8/16 22:03
     * @para : [lastName]
     * @return : com.sunvirgo.cache.bean.Employee
     */
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp",key = "#result.id"),
                    @CachePut(value = "emp",key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName){
        return employeeMapper.getEmpByLastName(lastName);
    }
}
