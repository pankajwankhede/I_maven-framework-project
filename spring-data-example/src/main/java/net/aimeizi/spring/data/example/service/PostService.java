package net.aimeizi.spring.data.example.service;

import java.util.List;

import net.aimeizi.spring.data.example.entities.Post;

public interface PostService {
	
	List<Post> find();
	
	Post findOne(int id);
	
	Post save(Post post);
	
	Post update(Post post);

	Post delete(Post post);
}
