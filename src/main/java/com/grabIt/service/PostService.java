package com.grabIt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grabIt.dao.PostDao;
import com.grabIt.domain.Post;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	public int addSample(Post post) {
		return postDao.addPost(post);
		
	}

	public List<Post> getPosts(String search, String categoryId, String subCategoryId) {
		return postDao.getPosts(search, categoryId, subCategoryId);
	}

}
