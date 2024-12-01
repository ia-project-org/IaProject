package org.bankms.batch_processing.clientsconfig;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.bankms.clientsms.model.Client;
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
@RequiredArgsConstructor
public class ClientExportBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public JpaPagingItemReader<Client> clientDatabaseReader(EntityManagerFactory entityManagerFactory) {
        return createJpaPagingItemReader(entityManagerFactory, "SELECT s FROM Client s", "ClientDatabaseReader");
    }

    @Bean
    public FlatFileItemWriter<Client> clientCsvWriter() {
        return createFlatFileItemWriter("src/main/resources/clients.csv",
                new String[]{"id", "firstname", "lastname", "age"},
                new String[]{"id", "firstname", "lastname", "age"});

    }

    @Bean
    public Step exportClientsStep(EntityManagerFactory entityManagerFactory) {
        return new StepBuilder("exportClientsStep", jobRepository)
                .<Client, Client>chunk(10, platformTransactionManager)
                .reader(clientDatabaseReader(entityManagerFactory))
                .writer(clientCsvWriter())
                .build();
    }

    @Bean(name = "exportClientsJob")
    public Job exportClientsJob(EntityManagerFactory entityManagerFactory) {
        return new JobBuilder("exportClientsJob", jobRepository)
                .start(exportClientsStep(entityManagerFactory))
                .build();
    }
}
