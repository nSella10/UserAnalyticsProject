package com.analytics.backend.repository;

import com.analytics.backend.model.UserAction;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionRepository extends MongoRepository<UserAction,String> {
}
