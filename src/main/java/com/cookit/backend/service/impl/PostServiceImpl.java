package com.cookit.backend.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.cookit.backend.dto.IngredientDto;
import com.cookit.backend.dto.PostDto;
import com.cookit.backend.entity.Comment;
import com.cookit.backend.entity.ContainsIngredient;
import com.cookit.backend.entity.ContainsIngredientId;
import com.cookit.backend.entity.HasTag;
import com.cookit.backend.entity.Ingredient;
import com.cookit.backend.entity.Photo;
import com.cookit.backend.entity.Post;
import com.cookit.backend.entity.User;
import com.cookit.backend.repository.BookmarkRepository;
import com.cookit.backend.repository.CommentRepository;
import com.cookit.backend.repository.ContainsIngredientRepository;
import com.cookit.backend.repository.HasTagRepository;
import com.cookit.backend.repository.IngredientRepository;
import com.cookit.backend.repository.PhotoRepository;
import com.cookit.backend.repository.PostRepository;
import com.cookit.backend.repository.RateRepository;
import com.cookit.backend.repository.TagRepository;
import com.cookit.backend.repository.UserLikesRepository;
import com.cookit.backend.repository.UserRepository;
import com.cookit.backend.service.PostService;

import jakarta.transaction.Transactional;
import com.cookit.backend.entity.Tag;

@Service
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;
    private ContainsIngredientRepository containsIngredientRepository;
    private HasTagRepository hasTagRepository;
    private TagRepository tagRepository;
    private PhotoRepository photoRepository;
    private UserRepository userRepository;
    private RateRepository rateRepository;
    private UserLikesRepository likeRepository;
    private BookmarkRepository bookmarkRepository;
    private CommentRepository commentRepository;
    private IngredientRepository ingredientRepository;

    public PostServiceImpl(PostRepository postRepository, ContainsIngredientRepository containsIngredientRepository, HasTagRepository hasTagRepository, TagRepository tagRepository, PhotoRepository photoRepository, UserRepository userRepository, RateRepository rateRepository, UserLikesRepository likeRepository, BookmarkRepository bookmarkRepository, CommentRepository commentRepository, IngredientRepository ingredientRepository) {
        this.postRepository = postRepository;
        this.containsIngredientRepository = containsIngredientRepository;
        this.hasTagRepository = hasTagRepository;
        this.tagRepository = tagRepository;
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
        this.rateRepository = rateRepository;
        this.likeRepository = likeRepository;
        this.bookmarkRepository = bookmarkRepository;
        this.commentRepository = commentRepository;
        this.ingredientRepository = ingredientRepository;
    }

    /**
     * Creates a post from a postDto object
     * It creates the post with the ingredients, the tags and the photos
     * It also creates the ingredients, tags and photos if they don't exist
     * @param postDto
     * @return Post
     */
    @Transactional
    @Override
    public Post createPost(PostDto postDto) {
        User user = userRepository.findByUsernameCaseInsensitive(postDto.getAuthor());
        Post post = new Post();
        post.setName(postDto.getName());
        post.setPublishDate(postDto.getPublishDate());
        post.setShortDescription(postDto.getShortDescription());
        post.setSteps(postDto.getSteps());
        post.setDifficulty(postDto.getDifficulty());
        post.setTime(postDto.getTime());
        post.setAuthor(user);
        postRepository.save(post);
        
        user.getPosts().add(post);
        if(postDto.getIngredients() != null) {
            for(IngredientDto ingredientDto : postDto.getIngredients()) {
                Ingredient ingredient = ingredientRepository.findByNameCaseInsensitive(ingredientDto.getName().toLowerCase());
                if(ingredient == null) {
                    ingredient = new Ingredient();
                    ingredient.setIngredientName(ingredientDto.getName().toLowerCase());
                    ingredientRepository.save(ingredient);
                }
                  
                    
                    Optional<ContainsIngredient> existingContainsIngredient = containsIngredientRepository.findById(new ContainsIngredientId(post.getId(), ingredient.getIngredientName()));
                    if(!existingContainsIngredient.isPresent()) {
                        ContainsIngredient containsIngredient = new ContainsIngredient(post, ingredient, ingredientDto.getQuantity(), ingredientDto.getUnit());
                        //containsIngredientRepository.save(containsIngredient);
                        ingredient.getContainsIngredients().add(containsIngredient);
                        post.getContainsIngredients().add(containsIngredient); 
                    }
            }
        }

        if(postDto.getTags() != null) {
            for(String tagName : postDto.getTags()) {
                Tag tag = tagRepository.findByNameCaseInsensitive(tagName);
                if(tag == null) {
                    tag = new Tag();
                    tag.setTagName(tagName);
                    tagRepository.save(tag);
                }
                HasTag hasTag = new HasTag(post, tag);
                //hasTagRepository.save(hasTag);
                post.getHasTags().add(hasTag);
                tag.getHasTags().add(hasTag);
            }
        }

        if(postDto.getPhotos() != null) {
            for(String photo : postDto.getPhotos()) {
                Photo photoEntity = new Photo(post, photo);
                //photoRepository.save(photoEntity);
                post.getPhotos().add(photoEntity);
            }
        }

       return post;
    }

    @Override
    public void updatePost(Post post) {
        postRepository.save(post);//need to check if this is correct
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public Post getPost(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public List<Post> getAllPosts() {
        System.out.println(postRepository.findAll());
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostsByDifficulty(String difficulty) {
        
        return postRepository.getPostsByDifficulty(difficulty);
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
        return postRepository.getUserPosts(username);
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

    @Override
    public List<Post> getPostsByUserAndDifficulty(String username, String difficulty) {
        return postRepository.getPostsByDifficultyAndUser(difficulty, username);
    }

}
