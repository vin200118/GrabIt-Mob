package com.grabIt.dao;

import java.util.List;

import com.grabIt.domain.Post;

public interface PostDao {

	int addPost(Post post);

	List<Post> getPosts(String search, String categoryId, String subCategoryId);

}
