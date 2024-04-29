package com.cookit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.Follows;
import com.cookit.backend.entity.FollowsId;


public interface FollowsRepository extends JpaRepository<Follows, FollowsId>{

}
