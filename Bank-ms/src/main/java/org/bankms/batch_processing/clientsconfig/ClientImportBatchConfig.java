package org.bankms.batch_processing.clientsconfig;


import lombok.RequiredArgsConstructor;
import org.bankms.clientsms.model.Client;
import org.bankms.clientsms.repository.ClientRepository;
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
import org.springframework.transaction.PlatformTransactionManager;

import static org.bankms.batch_processing.utlis.Utils.createItemReader;

@Configuration
@RequiredArgsConstructor
public class ClientImportBatchConfig {

    private final ClientRepository clientRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final JobRepository jobRepository;

    @Bean
    public FlatFileItemReader<Client> ClientItemReader() {
        FlatFileItemReader<Client> itemReader = createItemReader("src/main/resources/clients.csv","ClientItemReader");
        itemReader.setLineMapper(lineMapper());
        return itemReader;
    }

    @Bean
    public RepositoryItemWriter<Client> ClientWriter() {
        RepositoryItemWriter<Client> writer = new RepositoryItemWriter<>();
        writer.setRepository(clientRepository);
        writer.setMethodName("save");
        return writer;
    }

    @Bean
    public ClientProcessor Clientprocessor() {
        return new ClientProcessor();
    }

    @Bean
    public Step ClientimportStep() {
        return new StepBuilder("importClientsStep", jobRepository)
                .<Client, Client>chunk(10, platformTransactionManager)
                .reader(ClientItemReader())
                .processor(Clientprocessor())
                .writer(ClientWriter())
                .build();
    }

    @Bean(name = "importClientsJob")
    public Job ClientrubJob() {
        return new JobBuilder("importClientsJob", jobRepository)
                .start(ClientimportStep())
                .build();
    }

    private LineMapper<Client> lineMapper() {
        DefaultLineMapper<Client> lineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("firstName","lastName","email","phoneNumber");
        BeanWrapperFieldSetMapper<Client> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Client.class);
        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

}
