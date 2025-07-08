package com.project.activity_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.activity_service.dto.ActivityRequest;
import com.project.activity_service.dto.ActivityResponse;
import com.project.activity_service.dto.ApiResponse;
import com.project.activity_service.service.ActivityService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    @Autowired
    ActivityService service;

    @PostMapping("/addactivity")
    public ResponseEntity<ApiResponse<ActivityResponse>> postMethodName(@RequestBody ActivityRequest activity) {
        ApiResponse<ActivityResponse> response = new ApiResponse<>(HttpStatus.CREATED,
                "data created successfully",
                service.addActivity(activity));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ActivityResponse>> getActivityById(@PathVariable String id) {

        ApiResponse<ActivityResponse> response = new ApiResponse<>(HttpStatus.OK, "data fetched successfully",
                service.getActivityById(id));
        return ResponseEntity.ok(response);

    }

    @GetMapping("/activities")
    public ResponseEntity<ApiResponse<List<ActivityResponse>>> getActivityByUser(
            @RequestHeader Map<String, String> headers) {

        String userId = headers.get("userid");

        List<ActivityResponse> activities = service.findActivitiesByUserId(userId);
        ApiResponse<List<ActivityResponse>> response = new ApiResponse<>(HttpStatus.OK, "activity fetched successfully",
                activities);

        return ResponseEntity.ok(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ActivityResponse>> updateActivityById(@PathVariable String id,
            @RequestBody ActivityRequest activityRequest) {

        System.out.println(activityRequest);
        ApiResponse<ActivityResponse> response = new ApiResponse<>(HttpStatus.OK, "activity updated successfully",
                service.updateActivity(id, activityRequest));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteActivityById(@PathVariable String id) {
        ApiResponse<String> response = new ApiResponse<>(HttpStatus.OK, id + "deleted successfully",
                service.deleteActivityById(id));
        return ResponseEntity.ok(response);
    }

}
