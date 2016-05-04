package com.grabIt.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.grabIt.dao.PostDao;
import com.grabIt.domain.Post;

@Service
public class PostService {
	
	@Autowired
	private PostDao postDao;
	
	public int addSample(Post post) {
		
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
				  "cloud_name", "vinayak118",
				  "api_key", "278713757411919",
				  "api_secret", "qC2dHyCsUUcI7WjMfnSQ1Mrv2mE"));
		try {
			Map uploadResult = cloudinary.uploader().upload(post.getImage(), ObjectUtils.asMap());
			post.setImagePath(uploadResult.get("url").toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		return postDao.addPost(post);
		
	}

	public List<Post> getPosts(String search, String categoryId, String subCategoryId, String userId) {
		return postDao.getPosts(search, categoryId, subCategoryId, userId);
	}

	public Post getPost(String id) {
		return postDao.getPost(id);
	}

}
