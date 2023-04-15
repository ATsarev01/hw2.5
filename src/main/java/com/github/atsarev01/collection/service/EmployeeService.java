package com.github.atsarev01.collection.service;

import com.github.atsarev01.collection.exeption.EmployeeAlreadyAddedExeption;
import com.github.atsarev01.collection.exeption.EmployeeNotFoundExeption;
import com.github.atsarev01.collection.exeption.EmployeeStorageIsFullExeption;
import com.github.atsarev01.collection.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeService {

    private static final int SIZE = 3;

    private Map <String, Employee> employees;

    public EmployeeService() {
        this.employees = new HashMap<>();
    }

    public Employee add(String firstName, String lastName) {
        if (employees.size() < SIZE) {
            Employee employee = new Employee(firstName, lastName);
            if (employees.containsKey(employee.getFullName() )) {
                throw new EmployeeAlreadyAddedExeption();
            }
            employees.put(employee.getFullName(), employee);
            return employee;
        }
        throw new EmployeeStorageIsFullExeption();

    }
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundExeption();
        }
        employees.remove(employee.getFullName());
        return employee;
    }
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(employee.getFullName())) {
            throw new EmployeeNotFoundExeption();
        }
        return employees.get(employee.getFullName());

    }

    public Collection<Employee> employees() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
