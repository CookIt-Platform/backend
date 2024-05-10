package com.cookit.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.cookit.backend.entity.UserLikes;
import com.cookit.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.cookit.backend.dto.IngredientDto;
import com.cookit.backend.dto.PostDto;
import com.cookit.backend.entity.Post;
import com.cookit.backend.repository.ContainsIngredientRepository;
import com.cookit.backend.repository.HasTagRepository;
import com.cookit.backend.repository.IngredientRepository;
import com.cookit.backend.repository.PhotoRepository;
import com.cookit.backend.repository.PostRepository;
import com.cookit.backend.repository.TagRepository;
import com.cookit.backend.response.PostResponse;

import jakarta.transaction.Transactional;

@Service
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;
    private ContainsIngredientRepository containsIngredientRepository;
    private HasTagRepository hasTagRepository;
    private TagRepository tagRepository;
    private PhotoRepository photoRepository;
    private PhotoService photoService;
    private HasTagService hasTagService;
    private IngredientRepository ingredientRepository;
    private ContainsIngredientService containsIngredientService;
    private CommentService commentService;
    private UserLikesService userLikesService;
    private BookmarkService bookmarkService;
    private RateService rateService;
    @Autowired

    public PostServiceImpl(PostRepository postRepository, PhotoService photoService, ContainsIngredientRepository containsIngredientRepository,
                           HasTagRepository hasTagRepository, TagRepository tagRepository, PhotoRepository photoRepository,
                           HasTagService hasTagService, IngredientRepository ingredientRepository, ContainsIngredientService containsIngredientService,
                           CommentService commentService, UserLikesService userLikesService, BookmarkService bookmarkService, RateService rateService) {
        this.postRepository = postRepository;
        this.containsIngredientRepository = containsIngredientRepository;
        this.hasTagRepository = hasTagRepository;
        this.tagRepository = tagRepository;
        this.photoRepository = photoRepository;
        this.photoService = photoService;
        this.hasTagService = hasTagService;
        this.ingredientRepository = ingredientRepository;
        this.containsIngredientService = containsIngredientService;
        this.commentService = commentService;
        this.userLikesService = userLikesService;
        this.bookmarkService = bookmarkService;
        this.rateService = rateService;
    }

    /**
     * Creates a post from a postDto object
     * It creates the post with the ingredients, the tags and the photos
     * It also creates the ingredients, tags and photos if they don't exist
     * @param postDto
     * @return Post
     */
    @Override
    public Post createPost(PostDto postDto) {
        //User user = userRepository.findByUsernameCaseInsensitive(postDto.getAuthor());

        postRepository.createPost(postDto.getDifficulty(), postDto.getName(), 
                                    postDto.getPublishDate(), postDto.getShortDescription(), 
                                    postDto.getSteps(), postDto.getTime(), postDto.getAuthor());
        Long id = postRepository.getLastPost();
        
        if(postDto.getIngredients() != null) {
            for(IngredientDto ingredientDto : postDto.getIngredients()) {
                if (ingredientRepository.findByNameCaseInsensitive(ingredientDto.getName()) == null) {
                    ingredientRepository.createIngredient(ingredientDto.getName());
                }
                containsIngredientRepository.createContainsIngredient(ingredientDto.getName(), id, ingredientDto.getQuantity(), ingredientDto.getUnit().toString());
            }
        }

        if(postDto.getTags() != null) {
            for(String tagName : postDto.getTags()) {
                if(tagRepository.findByNameCaseInsensitive(tagName) == null) {
                    tagRepository.createTag(tagName);
                }

                hasTagRepository.createHasTag(id, tagName);

            }
        }

        if(postDto.getPhotos() != null) {
            for(String photo : postDto.getPhotos()) {
                photoRepository.createPhoto(photo, id);
            }
        }

       return postRepository.getPost(id);
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
    public List<PostResponse> getPostsByUser(String username) {
        return createPostResponses(postRepository.getUserPosts(username));
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
        postResponse.setHasTags(hasTagService.getTagsOfPost(post.getId()));
        postResponse.setContainsIngredients(containsIngredientService.getPostIngredients(post.getId()));
        postResponse.setPhotos(photoService.getAllPhotos(post.getId()));
        postResponse.setNumLikes(userLikesService.getNumLikes(post.getId()));
        postResponse.setComments(commentService.getPostComments(post.getId()));
        postResponse.setNumBookmarks(bookmarkService.getNumBookmarks(post.getId()));
        postResponse.setNumComments(commentService.getNumComments(post.getId()));
        postResponse.setAverageRating(rateService.getAverageRating(post.getId()));
        return postResponse;
    }

    @Override
    public List<Post> getPostsByName(String name) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostsByName'");
    }

    @Override
    public List<Post> getPostsByIngredient(String ingredient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostsByIngredient'");
    }

    @Override
    public List<Post> getPostsByIngredientList(List<String> ingredients) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostsByIngredientList'");
    }

    @Override
    public List<Post> getPostsByTagList(List<String> tags) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostsByTagList'");
    }

    @Override
    public List<Post> getPostsByTag(String tag) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPostsByTag'");
    }

    @Override
    public List<PostResponse> findPostsByKeyword(String keyword) {
        return createPostResponses(postRepository.findPostsByKeyword(keyword));
    }



}
