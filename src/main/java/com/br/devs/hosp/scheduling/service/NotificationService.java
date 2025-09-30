package com.br.devs.hosp.scheduling.service;

import com.br.devs.hosp.scheduling.controller.dto.NotificationRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final RabbitTemplate rabbitTemplate;

    public NotificationService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendNotification(NotificationRequest notification) {
        rabbitTemplate.convertAndSend("appointmentsQueue", notification);
    }
}