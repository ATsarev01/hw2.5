package com.github.atsarev01.collection.service;

import com.github.atsarev01.collection.exeption.EmployeeAlreadyAddedExeption;
import com.github.atsarev01.collection.exeption.EmployeeNotFoundExeption;
import com.github.atsarev01.collection.exeption.EmployeeStorageIsFullExeption;
import com.github.atsarev01.collection.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Service
public class EmployeeService {

    private static final int SIZE = 3;

    private final Collection<Employee> employees = new ArrayList<>();

    public Employee add(String firstName, String lastName) {
        if (employees.size() < SIZE) {
            Employee employee = new Employee(firstName, lastName);
            if (employees.contains(employee)) {
                throw new EmployeeAlreadyAddedExeption();
            }
            employees.add(employee);
            return employee;
        }
        throw new EmployeeStorageIsFullExeption();

    }
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundExeption();
        }
        employees.remove(employee);
        return employee;
    }
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundExeption();
        }
        return employee;

    }

    public Collection<Employee> employees() {
        return Collections.unmodifiableCollection(employees);
    }
}
