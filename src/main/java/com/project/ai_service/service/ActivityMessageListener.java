package com.project.ai_service.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.project.ai_service.dto.Activity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ActivityMessageListener {

    @RabbitListener(queues = "activity.queue")
    public void processActivity(Activity activity) {
        log.info("Received activity for processing: {}", activity.getId());
    }

}
