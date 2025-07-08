package com.project.activity_service.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.project.activity_service.ExceptionHandling.ActivityNotFoundException;
import com.project.activity_service.dto.ActivityRequest;
import com.project.activity_service.dto.ActivityResponse;
import com.project.activity_service.model.Activity;
import com.project.activity_service.repository.ActivityRepository;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository repo;

    @Autowired
    private MongoTemplate mongoTemplate;

    public ActivityResponse addActivity(ActivityRequest activity) {

        Activity activityModel = new Activity();
        activityModel.setUserId(activity.getUserId());
        activityModel.setType(activity.getType());
        activityModel.setStartTime(activity.getStartTime());
        activityModel.setCaloriesBurned(activity.getCaloriesBurned());
        activityModel.setAdditionalMetrics(activity.getAdditionalMetrics());
        activityModel.setDuration(activity.getDuration());

        Activity savedActivity = repo.save(activityModel);

        return mapToResponse(savedActivity);
    }

    public ActivityResponse mapToResponse(Activity activity) {
        ActivityResponse response = new ActivityResponse();
        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setType(activity.getType());
        response.setStartTime(activity.getStartTime());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setDuration(activity.getDuration());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());
        return response;

    }

    public ActivityResponse getActivityById(String id) {
        Activity activity = repo.findById(id)
                .orElseThrow(() -> new ActivityNotFoundException("Activity not Found " + id));

        return mapToResponse(activity);

    }

    public List<ActivityResponse> findActivitiesByUserId(String userId) {
        List<Activity> activities = repo.findAllByUserId(userId);
        List<ActivityResponse> response = activities.stream().map(activity -> mapToResponse(activity))
                .collect(Collectors.toList());

        return response;
    }

    public ActivityResponse updateActivity(String id, ActivityRequest updatedData) {
        if (repo.existsById(id)) {
            Query query = new Query(Criteria.where("_id").is(id));
            Update update = new Update();
            if (updatedData.getType() != null) {
                update.set("type", updatedData.getType());
            }
            if (updatedData.getDuration() != null) {
                update.set("duration", updatedData.getDuration());
            }
            if (updatedData.getCaloriesBurned() != null) {
                update.set("caloriesBurned", updatedData.getCaloriesBurned());
            }
            if (updatedData.getAdditionalMetrics() != null) {
                update.set("additionalMetrics", updatedData.getAdditionalMetrics());
            }
            update.set("updatedAt", LocalDateTime.now());

            return mapToResponse(
                    mongoTemplate.findAndModify(query, update, new FindAndModifyOptions().returnNew(true),
                            Activity.class));
        } else {
            throw new ActivityNotFoundException(id + "Activity not found");
        }
    }

    public String deleteActivityById(String id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "deleted successfully";
        } else {
            throw new ActivityNotFoundException(id + "Activity not found");
        }

    }

}
