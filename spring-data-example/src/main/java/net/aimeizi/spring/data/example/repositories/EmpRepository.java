package net.aimeizi.spring.data.example.repositories;

import net.aimeizi.spring.data.example.entities.Emp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpRepository extends JpaRepository<Emp, Integer> {

}
