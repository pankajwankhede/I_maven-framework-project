package net.aimeizi.spring.data.example;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.aimeizi.spring.data.example.config.WebConfig;
import net.aimeizi.spring.data.example.entities.Post;
import net.aimeizi.spring.data.example.service.PostService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=WebConfig.class,loader=AnnotationConfigContextLoader.class)
public class PostRepositoryTest {

	@Autowired
	PostService postService;
	
	@Test
	public void save() {
		Post post = new Post();
		post.setPostDate(new Date());
		post.setTitle("Second Post");
		
		postService.save(post);
		
		Post dbpost = postService.findOne(post.getPostId());
		assertNotNull(dbpost);
		System.out.println(dbpost.getTitle());
	}
	
	@Test
	public void find() {
		
		List<Post> list = postService.find();
		
		for (Post p : list) {
			System.out.println(p);
		}
		
	}
	
	@Test
	public void findOne() {
		
		System.out.println(postService.findOne(1));
		
	}
	
	@Test
	public void update(){
		
		Post post = new Post();
		post.setPostId(1);
		post.setPostDate(Calendar.getInstance().getTime());
		post.setTitle("update title");
		
		postService.update(post);
	}
	
	@Test
	public void delete(){
		
		Post post = postService.findOne(2);
		
		postService.delete(post);
		
		find();
	}

}
