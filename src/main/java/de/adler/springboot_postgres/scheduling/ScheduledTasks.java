package de.adler.springboot_postgres.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@EnableScheduling
class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * This bean retrieves the cron expression form the configuration
     *
     * @param cronValue String from config
     * @return Cron expression
     */
    @Bean
    public String cronBean(@Value("${spring.scheduling.cron}") String cronValue) {
        return cronValue;
    }

    @Scheduled(initialDelay = 5000, fixedRateString = "${spring.scheduling.fixed}")
    public void scheduleTaskUsingfidexRate() {
        log.info("Running scheduleTaskUsingfidexRate: The time is now {}", dateFormat.format(new Date()));
    }

    @Scheduled(cron = "#{@cronBean}")
    public void scheduleTaskUsingCronExpression() {
        log.info("Running scheduleTaskUsingCronExpression: The time is now {}", dateFormat.format(new Date()));
    }
}