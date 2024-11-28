package org.bankms.clientsms.batch_processing.config;

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
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ExportBatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final TaskExecutor taskExecutor;

    @Bean
    public JpaPagingItemReader<Client> databaseReader(EntityManagerFactory entityManagerFactory) {
        JpaPagingItemReader<Client> reader = new JpaPagingItemReader<>();
        reader.setQueryString("SELECT s FROM Client s"); // JPQL query
        reader.setEntityManagerFactory(entityManagerFactory);
        reader.setPageSize(10); // Adjust as needed
        reader.setName("databaseReader");
        return reader;
    }

    @Bean
    public FlatFileItemWriter<Client> csvWriter() {
        FlatFileItemWriter<Client> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("src/main/resources/clients.csv")); // Output file path
        writer.setName("csvWriter");
        writer.setHeaderCallback(writer1 -> writer1.write("id,firstname,lastname,age")); // CSV header
        writer.setLineAggregator(new DelimitedLineAggregator<Client>() {{
            setDelimiter(",");
            setFieldExtractor(new BeanWrapperFieldExtractor<Client>() {{
                setNames(new String[]{"id", "firstname", "lastname", "age"});
            }});
        }});
        return writer;
    }

    @Bean
    public Step exportStep(EntityManagerFactory entityManagerFactory) {
        return new StepBuilder("exportStep", jobRepository)
                .<Client, Client>chunk(10, platformTransactionManager)
                .reader(databaseReader(entityManagerFactory))
                .writer(csvWriter())
                .taskExecutor(taskExecutor)
                .build();
    }

    @Bean(name = "exportJob")
    public Job exportJob(EntityManagerFactory entityManagerFactory) {
        return new JobBuilder("StudentsExport", jobRepository)
                .start(exportStep(entityManagerFactory))
                .build();
    }
}
