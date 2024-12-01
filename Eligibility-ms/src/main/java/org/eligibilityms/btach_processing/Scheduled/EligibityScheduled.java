package org.eligibilityms.btach_processing.Scheduled;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EligibityScheduled {

    private final JobLauncher jobLauncher;

    private final Job ClientEligibilityJob;



    @Scheduled(cron = "0 */2 * * * ?")
    public void startjob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("chunkSize", "20")  // Example value
                .addString("timestamp", String.valueOf(System.currentTimeMillis())) // Unique parameter
                .toJobParameters();
        jobLauncher.run(ClientEligibilityJob,jobParameters);
    }

}

