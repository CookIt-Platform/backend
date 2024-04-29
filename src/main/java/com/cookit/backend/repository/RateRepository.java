package com.cookit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.Rate;
import com.cookit.backend.entity.RateId;

public interface RateRepository extends JpaRepository<Rate, RateId>{

}
