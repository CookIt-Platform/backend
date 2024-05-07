package com.cookit.backend.service.impl;

import com.cookit.backend.dto.RateDto;
import com.cookit.backend.entity.Post;
import com.cookit.backend.entity.Rate;
import com.cookit.backend.entity.RateId;
import com.cookit.backend.entity.User;
import com.cookit.backend.repository.PostRepository;
import com.cookit.backend.repository.RateRepository;
import com.cookit.backend.repository.UserRepository;
import com.cookit.backend.service.RateService;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class RateServiceImpl implements RateService{
    private RateRepository rateRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;
    public RateServiceImpl(RateRepository rateRepository, PostRepository postRepository, UserRepository userRepository) {
        this.rateRepository = rateRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createRate(RateDto rateDto) {
        Rate rate = new Rate();
        rate.setValue(rateDto.getValue());
        User user = userRepository.findById(rateDto.getUsername()).orElseThrow();
        Post post = postRepository.findById(rateDto.getPostId()).orElseThrow();
        rate.setUserId(user);
        rate.setPostId(post);
        rateRepository.save(rate);
    }

    @Override
    public void deleteRate(RateId rateId) {
        rateRepository.deleteById(rateId);
    }

    @Override
    public void updateRate(RateDto rateDto) {

    }

    @Override
    public Set<Rate> getAllRates(String username) {
        return userRepository.findById(username).orElseThrow().getRates();
    }

    @Override
    public Set<Rate> getAllRates(Long postId) {
        return postRepository.findById(postId).orElseThrow().getRates();
    }
}
