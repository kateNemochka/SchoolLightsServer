package com.katenemochka.schoollights.component;

import com.katenemochka.schoollights.dao.ScheduleRepository;
import com.katenemochka.schoollights.domain.Schedule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ScheduledTasksCronFromDatabaseExpression {

    final static Logger logger = LoggerFactory.getLogger(ScheduledTasksCronFromDatabaseExpression.class);
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Scheduled(cron = "#{@getCronExpressionFromDb}")
    public void scheduleTasksCronFromDatabaseExpression() {
        logger.info("scheduleTasksCronFromDatabaseExpression executed at {}", LocalDateTime.now());
        System.out.println("Scheduled task execution");
    }

    @Bean
    public String getCronExpressionFromDb() {
        Schedule scheduleEvent = scheduleRepository.findByEventName("scheduleTasksCronFromDatabaseExpression");
        return scheduleEvent.getExpression();
    }
}
