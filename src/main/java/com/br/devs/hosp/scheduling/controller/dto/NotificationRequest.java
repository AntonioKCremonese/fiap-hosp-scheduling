package com.br.devs.hosp.scheduling.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NotificationRequest {
    private String title;
    private String message;
    private String userEmail;
    private String userId;
}