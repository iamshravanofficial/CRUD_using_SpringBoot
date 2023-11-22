package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmpRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class EmpServiceImplementation implements EmpService {
	@Autowired
	private EmpRepository empRepository;
	
	@Override
	public Employee saveEmployee(Employee emp) {
		Employee newemp = empRepository.save(emp);
		return newemp;
	}

	@Override
	public List<Employee> getAllEmp() {
		
		return empRepository.findAll();
	}

	@Override
	public Employee getEmpId(int id) {
		
		return empRepository.findById(id).get();
	}

	@Override
	public Boolean deleteEmp(int id) {
		Employee emp = empRepository.findById(id).get();
		if(emp!=null) {
			empRepository.delete(emp);
			return true;
		}
		return false;
	}
	
	public void removeSessionmsg() {
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		session.removeAttribute("msg");
	}

}
