package com.cookit.backend.service;


import com.cookit.backend.dto.RateDto;
import com.cookit.backend.entity.Rate;
import com.cookit.backend.entity.RateId;

import java.util.Set;

public interface RateService {
    public void createRate(RateDto rateDto);
    public void deleteRate(RateId rateId);
    public void updateRate(RateDto rateDto);
    public Set<Rate> getAllRates(String username);
    public Set<Rate> getAllRates(Long postId);
}
