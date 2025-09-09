package com.br.devs.hosp.scheduling.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue appointmentsQueue() {
        return new Queue("appointmentsQueue", true);
    }
}
