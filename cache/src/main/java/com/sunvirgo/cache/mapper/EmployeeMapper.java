package com.sunvirgo.cache.mapper;

import com.sunvirgo.cache.bean.Employee;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

/**
 * 类说明:
 *
 * @author : 黄刚
 * @date : 2020/7/26 21:49
 **/
@Mapper
@Component
public interface EmployeeMapper {
    /**
     * 方法说明:
     * @author : 黄刚
     * @date : 2020/7/26 21:50
     * @para : [id]
     * @return : com.sunvirgo.bean.Employee
     */
    @Select("Select * from employee where id=#{id}")
    public Employee getEmpById(Integer id);

    /**
     * 方法说明:
     * @author : 黄刚
     * @date : 2020/7/26 21:52
     * @para : [employee]
     * @return : void
     */
    @Update("Update employee set lastName=#{lastName}, email=#{email},gender=#{gender}, d_id=#{dId} where id=#{id}")
    public void updateEmp(Employee employee);

    /**
     * 方法说明:
     * @author : 黄刚
     * @date : 2020/7/26 21:54
     * @para : [id]
     * @return : void
     */
    @Delete("Delete from employee where id=#{id}")
    public void deleteEmpById(Integer id);

    /**
     * 方法说明:
     * @author : 黄刚
     * @date : 2020/7/26 21:56
     * @para : [employee]
     * @return : void
     */
    @Insert("Insert into employee(lastName,email,gender,d_id) values(#{lastName},#{email},#{gender},#{dId})")
    public void insertEmployee(Employee employee);

    /**
     * 方法说明:
     * @author : 黄刚
     * @date : 2020/8/16 22:01
     * @para : [lastName]
     * @return : com.sunvirgo.cache.bean.Employee
     */
    @Select("Select * from employee where lastName=#{lastName}")
    public Employee getEmpByLastName(String lastName);
}
