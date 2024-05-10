package com.cookit.backend.service;

import java.util.List;

import com.cookit.backend.dto.PostDto;
import com.cookit.backend.entity.Post;
import com.cookit.backend.response.PostResponse;

public interface PostService {

    public Post createPost(PostDto post);
    public void updatePost(Post post);
    public void deletePost(Long id);
    public Post getPost(Long id);
    public List<PostResponse> getAllPosts();
    public List<PostResponse> getTopLikedPosts(Integer num);
    public List<PostResponse> getRecentPosts(Integer num);
    public List<PostResponse> getPostsByDifficulty(String difficulty);
    public List<PostResponse> getPostsByUserAndDifficulty(String username, String difficulty);
    public List<Post> getPostsByTagList(List<String> tags);
    public List<Post> getPostsByTag(String tag);
    public List<PostResponse> getPostsByUser(String username);
    public List<Post> getPostsByName(String name);
    public List<Post> getPostsByIngredient(String ingredient);
    public List<Post> getPostsByIngredientList(List<String> ingredients);
}
