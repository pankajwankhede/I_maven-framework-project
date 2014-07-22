package net.aimeizi.spring.data.example.service.impl;

import java.util.List;

import net.aimeizi.spring.data.example.entities.Post;
import net.aimeizi.spring.data.example.repositories.PostRepository;
import net.aimeizi.spring.data.example.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

	@Autowired
	PostRepository repository;

	@Override
	public List<Post> find() {
		return repository.findAll();
	}

	@Override
	public Post save(Post post) {
		return repository.save(post);
	}

	@Override
	public Post update(Post post) {
		
		Post one = repository.findOne(post.getPostId());
		
		one.setTitle(post.getTitle());
		one.setPostDate(post.getPostDate());
		
		return one;
		
	}

	@Override
	public Post delete(Post post) {
		
		Post one = repository.findOne(post.getPostId());
		
		repository.delete(one);
		
		return one;
	}

	@Override
	public Post findOne(int id) {
		return repository.findOne(id);
	}

}
