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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
public class RateServiceImpl implements RateService{
    private RateRepository rateRepository;

    @Autowired
    public RateServiceImpl(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @Override
    public void createRate(RateDto rateDto) {
        rateRepository.createRate(rateDto.getPostId(), rateDto.getUsername(), rateDto.getValue());
    }

    @Override
    public void deleteRate(RateDto rateDto) {
        rateRepository.deleteRate(rateDto.getPostId(), rateDto.getUsername());
    }

    @Override
    public void updateRate(RateDto rateDto) {
        rateRepository.updateRate(rateDto.getValue(), rateDto.getPostId(), rateDto.getUsername());
    }

    @Override
    public Double getAverageRating(Long postID) {
        return rateRepository.findPostAverageRating(postID);
    }

    @Override
    public Set<Rate> getAllUserRates(String username) {
        return rateRepository.getAllUserRates(username);
    }

    @Override
    public Set<Rate> getAllPostRates(Long postId) {
        return rateRepository.getAllPostRates(postId);
    }
}
