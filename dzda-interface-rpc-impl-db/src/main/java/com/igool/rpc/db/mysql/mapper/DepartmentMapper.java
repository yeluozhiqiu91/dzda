package com.igool.rpc.db.mysql.mapper;

import com.igool.rpc.db.model.thrift.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wang on 2017/10/26.
 */
public interface DepartmentMapper {

    List<Department> getDepartmentList();

    void addDepartment(Department department);

    Department findDepartmentById(@Param("departmentId") int departmentId);

    void updateDepartment(Department department);

    int getChildrenCount(@Param("departmentId") int departmentId);

    int getPersonCount(@Param("departmentId") int departmentId);

    void deleteDepartmentById(@Param("departmentId") int departmentId);
}
