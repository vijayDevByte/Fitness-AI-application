package com.project.ai_service.dto;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Data;

@Data
public class Activity {

    private String id;
    private String userId;

    private ActivityType type;
    private Integer duration;
    private Integer caloriesBurned;
    private LocalDateTime startTime;
    private Map<String, Object> additionalMetrics;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum ActivityType {
        RUNNING,
        WALKING,
        CYCLING,
        SWIMMING,
        WEIGHT_TRAINING,
        YOGA,
        CARDIO,
        STRETCHING,
        OTHER
    }
}
