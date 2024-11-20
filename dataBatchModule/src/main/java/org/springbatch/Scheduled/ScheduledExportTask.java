package org.springbatch.Scheduled;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledExportTask {

    private final JobLauncher jobLauncher;

    private final Job exportJob;

    @Scheduled(cron = "0 0 0 * * ?")  // Ceci exécute le job tous les jours à minuit
    public void runExportJob() {
        try {
            jobLauncher.run(exportJob, new JobParameters());
            System.out.println("Export Job exécuté à " + new java.util.Date());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

