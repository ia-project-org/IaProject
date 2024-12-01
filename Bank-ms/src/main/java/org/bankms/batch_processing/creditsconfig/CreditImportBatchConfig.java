package org.bankms.batch_processing.creditsconfig;


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
import org.springframework.transaction.PlatformTransactionManager;

import static org.bankms.batch_processing.utlis.Utils.createItemReader;

@Configuration
@RequiredArgsConstructor
public class CreditImportBatchConfig {

    private final CreditRepository CreditRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    @Bean
    public FlatFileItemReader<Credit> CreditItemReader() {
        FlatFileItemReader<Credit> itemReader = createItemReader("src/main/resources/credits.csv","CreditItemReader");
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    @Bean
    public RepositoryItemWriter<Credit> CreditWriter() {
        RepositoryItemWriter<Credit> writer = new RepositoryItemWriter<>();
        writer.setRepository(CreditRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public CreditProcessor Creditprocessor() {
        return new CreditProcessor();
    }

    @Bean
    public Step CreditImportStep() {
        return new StepBuilder("importCreditsStep", jobRepository)
                .<Credit, Credit>chunk(10, platformTransactionManager)
                .reader(CreditItemReader())
                .processor(Creditprocessor())
                .writer(CreditWriter())
                .build();
    }

    @Bean(name = "importCreditsJob")
    public Job CreditrubJob() {
        return new JobBuilder("importCreditsJob", jobRepository)
                .start(CreditImportStep())
                .build();
    }

    private LineMapper<Credit> lineMapper() {
        DefaultLineMapper<Credit> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("firstName","lastName","email","phoneNumber");
        BeanWrapperFieldSetMapper<Credit> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Credit.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

}
