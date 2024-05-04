package com.cookit.backend.service;

import java.util.List;

import com.cookit.backend.entity.Post;

public interface PostService {

    public void createPost(Post post);
    public void updatePost(Post post);
    public void deletePost(long id);
    public Post getPost(long id);
    public List<Post> getAllPosts();
    public List<Post> getPostsByDifficulty(String difficulty);
    public List<Post> getPostsByTagList(List<String> tags);
    public List<Post> getPostsByTag(String tag);
    public List<Post> getPostsByUser(String username);
    public List<Post> getPostsByName(String name);
    public List<Post> getPostsByIngredient(String ingredient);
    public List<Post> getPostsByIngredientList(List<String> ingredients);
}
