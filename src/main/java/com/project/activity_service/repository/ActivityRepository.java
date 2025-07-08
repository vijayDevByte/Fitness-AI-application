package com.project.activity_service.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.project.activity_service.model.Activity;

@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {

    List<Activity> findAllByUserId(String userId);

}
