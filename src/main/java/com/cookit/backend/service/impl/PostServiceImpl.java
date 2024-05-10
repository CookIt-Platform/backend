package com.cookit.backend.service.impl;

import java.util.ArrayList;
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
import com.cookit.backend.response.PostResponse;
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
        //User user = userRepository.findByUsernameCaseInsensitive(postDto.getAuthor());

        postRepository.createPost(postDto.getDifficulty(), postDto.getName(), 
                                    postDto.getPublishDate(), postDto.getShortDescription(), 
                                    postDto.getSteps(), postDto.getTime(), postDto.getAuthor());
        Post post = postRepository.getLastPost();
        
        if(postDto.getIngredients() != null) {
            for(IngredientDto ingredientDto : postDto.getIngredients()) {
                if(ingredientRepository.findByNameCaseInsensitive(ingredientDto.getName()) == null) {
                    ingredientRepository.createIngredient(ingredientDto.getName().toLowerCase());
                }
                containsIngredientRepository.createContainsIngredient(ingredientDto.getName(), post.getId(), ingredientDto.getQuantity(), ingredientDto.getUnit().toString());  
            }
        }

        if(postDto.getTags() != null) {
            for(String tagName : postDto.getTags()) {
                if(tagRepository.findByNameCaseInsensitive(tagName) == null) {
                    tagRepository.createTag(tagName);
                }

                hasTagRepository.createHasTag(post.getId(), tagName);

            }
        }

        if(postDto.getPhotos() != null) {
            for(String photo : postDto.getPhotos()) {
                photoRepository.createPhoto(photo, post.getId());
            }
        }

       return postRepository.getPost(post.getId());
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
    public PostResponse getPost(Long id) {
        return createResponse(postRepository.getPost(id));
    }

    @Override
    public List<PostResponse> getAllPosts() {
        return createPostResponses(postRepository.getAllPosts());
    }

    @Override
    public List<PostResponse> getPostsByDifficulty(String difficulty) {
        
        return createPostResponses(postRepository.getPostsByDifficulty(difficulty));
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
    public List<PostResponse> getPostsByUser(String username) {
        return createPostResponses(postRepository.getUserPosts(username));
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
    public List<PostResponse> getPostsByUserAndDifficulty(String username, String difficulty) {
        return createPostResponses(postRepository.getPostsByDifficultyAndUser(difficulty, username));
    }
  

    @Override
    public List<PostResponse> getTopLikedPosts(Integer num) {
        return createPostResponses(postRepository.getTopLikedPosts(num));
    }

    @Override
    public List<PostResponse> getRecentPosts(Integer num) {
        return createPostResponses(postRepository.getRecentPosts(num));
    }


    public List<PostResponse> createPostResponses(List<Post> posts) {
        List<PostResponse> postResponses = new ArrayList<>();
        for(Post post : posts) {
            postResponses.add(createResponse(post));
        }
        return postResponses;
    }

    public PostResponse createResponse(Post post) {
        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setName(post.getName());
        postResponse.setPublishDate(post.getPublishDate());
        postResponse.setShortDescription(post.getShortDescription());
        postResponse.setSteps(post.getSteps());
        postResponse.setDifficulty(post.getDifficulty());
        postResponse.setTime(post.getTime());
        postResponse.setAuthor(post.getAuthor().getUsername());

        return postResponse;
    }
    

}
