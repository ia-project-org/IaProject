package org.bankms.creditsms.batch_processing.config;


import lombok.RequiredArgsConstructor;
import org.bankms.creditsms.model.Credit;
import org.bankms.creditsms.repository.CreditRepository;
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

    private final CreditRepository creditRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;
    private final TaskExecutor taskExecutor;

    @Bean
    public FlatFileItemReader<Credit> itemReader() {
        FlatFileItemReader<Credit> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("C:\\Users\\HP\\IaProject\\Credits-ms\\src\\main\\resources\\Credits_import.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    @Bean
    public RepositoryItemWriter<Credit> writer() {
        RepositoryItemWriter<Credit> writer = new RepositoryItemWriter<>();
        writer.setRepository(creditRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public CreditProcessor processor() {
        return new CreditProcessor();
    }

    @Bean
    public Step importStep() {
        return new StepBuilder("csvImport", jobRepository)
                .<Credit, Credit>chunk(10, platformTransactionManager)
                .reader(itemReader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor)
                .build();
    }

    @Bean(name = "rubJob")
    public Job rubJob() {
        return new JobBuilder("tsImport", jobRepository)
                .start(importStep())
                .build();
    }

    private LineMapper<Credit> lineMapper() {
        DefaultLineMapper<Credit> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("montant","dateDebut","dateFin","status");
        BeanWrapperFieldSetMapper<Credit> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Credit.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }
}
