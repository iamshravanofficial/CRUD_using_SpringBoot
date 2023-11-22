package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Employee;

public interface EmpService {
	
	public Employee saveEmployee(Employee emp);
	
	public List<Employee> getAllEmp();
	
	public Employee getEmpId(int id);
	
	public Boolean deleteEmp(int id);
}
