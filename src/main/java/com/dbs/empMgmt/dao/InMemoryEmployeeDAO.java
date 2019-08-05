package com.dbs.empMgmt.dao;

import com.dbs.empMgmt.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository("InMemory")
public class InMemoryEmployeeDAO implements EmployeeDAO {

    private static final List<Employee> empList = new ArrayList<>();

    @Override
    public Employee save(Employee employee) {
        empList.add(employee);
        return employee;
    }

    @Override
    public Employee update(long empId, Employee employee) {
        Optional<Employee> optionalEmp = empList.stream().filter(emp -> emp.getId() == empId).findFirst();
        optionalEmp.ifPresent(employee1 -> empList.add(empList.indexOf(employee), employee));
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        return empList;
    }

    @Override
    public Employee findById(long id) {
        Optional<Employee> optionalEmployee = empList.stream().filter(employee -> employee.getId() == id).findFirst();
        if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }
        return null;
    }

    @Override
    public void deleteEmployee(long id) {
       empList.removeIf(employee->employee.getId()==id);
    }
}