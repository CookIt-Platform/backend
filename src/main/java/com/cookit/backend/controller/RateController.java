package com.cookit.backend.controller;

import com.cookit.backend.dto.RateDto;
import com.cookit.backend.service.RateService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate")
public class RateController {
    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createRate(@RequestBody RateDto rateDto) {
        rateService.createRate(rateDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/get/all/post/{id}")
    public ResponseEntity<?> getAllRates(@PathVariable Long id) {
        return ResponseEntity.ok(rateService.getAllRates(id));
    }

    @GetMapping("/get/all/user/{username}")
    public ResponseEntity<?> getRates(@PathVariable String username) {
        return ResponseEntity.ok(rateService.getAllRates(username));
    }
}
