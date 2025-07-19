package com.project.ai_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.ai_service.repository.RecommendationRepository;
import com.project.ai_service.model.Recommendation;

@Service
public class RecommendationSevice {
    @Autowired
    private RecommendationRepository repo;

    public List<Recommendation> getUserRecommendation(String userId) {
        return repo.findByUserId(userId);
    }

    public Recommendation getActivityRecommendation(String activityId) {
        return repo.findByActivityId(activityId)
                .orElseThrow(() -> new RuntimeException("no recommendation found" + activityId));
    }

}
