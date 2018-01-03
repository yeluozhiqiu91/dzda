package com.igool.rpc.db.service.impl;

import com.facebook.swift.codec.ThriftField;
import com.igool.rpc.db.model.thrift.Department;
import com.igool.rpc.db.mysql.mapper.DepartmentMapper;
import com.igool.rpc.db.service.thrift.DepartmentService;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wang on 2017/10/26.
 */
public class DepartmentServiceImpl implements DepartmentService{
    @Autowired
    DepartmentMapper departmentMapper;
    @Override
    public void close() {
        this.close();
    }

    @Override
    public List<Department> getDepartmentList() throws TException {
        return departmentMapper.getDepartmentList();
    }

    @Override
    public void addDepartment(@ThriftField(value = 1, name = "department", requiredness = ThriftField.Requiredness.NONE) Department department) throws TException {
        departmentMapper.addDepartment(department);
    }

    @Override
    public Department findDepartmentById(@ThriftField(value = 1, name = "departmentId", requiredness = ThriftField.Requiredness.NONE) int departmentId) throws TException {
        Department department=departmentMapper.findDepartmentById(departmentId);
        if(department!=null){
            return department;
        }else{
            return new Department();
        }
    }

    @Override
    public void updateDepartment(@ThriftField(value = 1, name = "department", requiredness = ThriftField.Requiredness.NONE) Department department) throws TException {
        departmentMapper.updateDepartment(department);
    }

    @Override
    public int getChildrenCount(@ThriftField(value = 1, name = "departmentId", requiredness = ThriftField.Requiredness.NONE) int departmentId) throws TException {
        return departmentMapper.getChildrenCount(departmentId);
    }

    @Override
    public int getPersonCount(@ThriftField(value = 1, name = "departmentId", requiredness = ThriftField.Requiredness.NONE) int departmentId) throws TException {
        return departmentMapper.getPersonCount(departmentId);
    }

    @Override
    public void deleteDepartmentById(@ThriftField(value = 1, name = "departmentId", requiredness = ThriftField.Requiredness.NONE) int departmentId) throws TException {
        departmentMapper.deleteDepartmentById(departmentId);
    }
}
