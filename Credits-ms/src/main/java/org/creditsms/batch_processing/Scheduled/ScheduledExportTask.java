package org.creditsms.batch_processing.Scheduled;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledExportTask {

    private final JobLauncher jobLauncher;

    private final Job exportJob;

}

