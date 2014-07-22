package net.aimeizi.spring.data.example.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.aimeizi.spring.data.example.entities.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
