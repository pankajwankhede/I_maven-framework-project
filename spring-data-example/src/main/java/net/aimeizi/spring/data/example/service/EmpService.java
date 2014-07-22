package net.aimeizi.spring.data.example.service;

import java.util.List;

import net.aimeizi.spring.data.example.entities.Emp;

public interface EmpService {

	List<Emp> find();
	
	Emp findOne(int id);
	
	Emp save(Emp emp);
	
	Emp update(Emp emp);

	Emp delete(Emp emp);
	
}
