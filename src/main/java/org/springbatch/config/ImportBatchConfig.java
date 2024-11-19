package org.springbatch.config;


import lombok.RequiredArgsConstructor;
import org.springbatch.client.Client;
import org.springbatch.client.StudentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class ImportBatchConfig {

    private final StudentRepository studentRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;
    private final TaskExecutor taskExecutor;

    @Bean
    public FlatFileItemReader<Client> itemReader() {
        FlatFileItemReader<Client> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/sample-data.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    @Bean
    public RepositoryItemWriter<Client> writer() {
        RepositoryItemWriter<Client> writer = new RepositoryItemWriter<>();
        writer.setRepository(studentRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public ClientProcessor processor() {
        return new ClientProcessor();
    }

    @Bean
    public Step importStep() {
        return new StepBuilder("csvImport", jobRepository)
                .<Client, Client>chunk(200, platformTransactionManager)
                .reader(itemReader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor)
                .build();
    }

    @Bean(name = "rubJob")
    public Job rubJob() {
        return new JobBuilder("StudentsImport", jobRepository)
                .start(importStep())
                .build();
    }

    private LineMapper<Client> lineMapper() {
        DefaultLineMapper<Client> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("firstname", "lastname", "age");
        BeanWrapperFieldSetMapper<Client> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Client.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
}
