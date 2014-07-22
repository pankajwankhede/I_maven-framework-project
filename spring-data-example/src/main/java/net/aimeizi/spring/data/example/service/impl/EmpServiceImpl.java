package net.aimeizi.spring.data.example.service.impl;

import java.util.List;

import net.aimeizi.spring.data.example.entities.Emp;
import net.aimeizi.spring.data.example.repositories.EmpRepository;
import net.aimeizi.spring.data.example.service.EmpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpRepository empRepository;
	
	@Override
	public List<Emp> find() {
		return empRepository.findAll();
	}

	@Override
	public Emp findOne(int id) {
		return empRepository.findOne(id);
	}

	@Override
	public Emp save(Emp emp) {
		return empRepository.save(emp);
	}

	@Override
	public Emp update(Emp emp) {
		
		Emp one = empRepository.findOne(emp.getEmpid());
		
		one.setEmpname(emp.getEmpname());
		
		one.setEmpaddress(emp.getEmpaddress());
		
		one.setDept(emp.getDept());
		
		return one;
	}

	@Override
	public Emp delete(Emp emp) {
		
		Emp one = empRepository.findOne(emp.getEmpid());	
		
		empRepository.delete(one);
		
		return one;
	}

}
