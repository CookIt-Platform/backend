package com.cookit.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cookit.backend.entity.UserLikes;
import com.cookit.backend.entity.UserLikesId;

public interface UserLikesRepository extends JpaRepository<UserLikes, UserLikesId>{

}
