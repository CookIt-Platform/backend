package com.cookit.backend.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cookit.backend.entity.Ingredient;
import com.cookit.backend.entity.Post;
import com.cookit.backend.repository.PostRepository;
import com.cookit.backend.service.PostService;

@Service
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void createPost(Post post) {
        
    }

    @Override
    public void updatePost(Post post) {
        
    }

    @Override
    public void deletePost(long id) {
        
    }

    @Override
    public Post getPost(long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Post> getPostsByDifficulty(String difficulty) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Post> getPostsByTagList(List<String> tags) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Post> getPostsByTag(String tag) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Post> getPostsByUser(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Post> getPostsByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Post> getPostsByIngredient(String ingredient) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Post> getPostsByIngredientList(List<String> ingredients) {
        // TODO Auto-generated method stub
        return null;
    }

}
