package com.cookit.backend.controller;

import com.cookit.backend.dto.RateDto;
import com.cookit.backend.entity.Rate;
import com.cookit.backend.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/rate")
public class RateController {
    private final RateService rateService;

    @Autowired
    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRate(@RequestBody RateDto rateDto) {
        rateService.createRate(rateDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping ("/delete")
    public ResponseEntity<?> deleteRate(@RequestBody RateDto rateDto) {
        rateService.deleteRate(rateDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateRate(@RequestBody RateDto rateDto) {
        rateService.updateRate(rateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/all/post/{id}")
    public Set<Rate> getAllPostRates(@PathVariable Long id) {
        return rateService.getAllPostRates(id);
    }

    @GetMapping("/get/all/user/{username}")
    public Set<Rate> getAllUserRates(@PathVariable String username) {
        return rateService.getAllUserRates(username);
    }

    @GetMapping("get/post/averagerating/{id}")
    public Double getAverageRating(@PathVariable Long id) {
        return rateService.getAverageRating(id);
    }
}
