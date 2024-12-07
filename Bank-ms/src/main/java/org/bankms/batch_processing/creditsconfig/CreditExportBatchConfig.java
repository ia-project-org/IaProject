package org.bankms.batch_processing.creditsconfig;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.bankms.creditsms.model.Credit;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import static org.bankms.batch_processing.utlis.Utils.createFlatFileItemWriter;
import static org.bankms.batch_processing.utlis.Utils.createJpaPagingItemReader;

@Configuration
public class CreditExportBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    public CreditExportBatchConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public JpaPagingItemReader<Credit> creditDatabaseReader(EntityManagerFactory entityManagerFactory) {
        return createJpaPagingItemReader(entityManagerFactory, "SELECT s FROM Credit s", "CreditDatabaseReader");
    }

    @Bean
    public FlatFileItemWriter<Credit> creditCsvWriter() {
        return createFlatFileItemWriter("src/main/resources/credits.csv",
                new String[]{"id", "firstname", "lastname", "age"},
                new String[]{"id", "firstname", "lastname", "age"});
    }

    @Bean
    public Step exportCreditsStep(EntityManagerFactory entityManagerFactory) {
        return new StepBuilder("exportCreditsStep", jobRepository)
                .<Credit, Credit>chunk(10, platformTransactionManager)
                .reader(creditDatabaseReader(entityManagerFactory))
                .writer(creditCsvWriter())
                .build();
    }

    @Bean(name = "exportCreditsJob")
    public Job exportCreditsJob(EntityManagerFactory entityManagerFactory) {
        return new JobBuilder("exportCreditsJob", jobRepository)
                .start(exportCreditsStep(entityManagerFactory))
                .build();
    }
}
