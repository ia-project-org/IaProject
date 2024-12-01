package org.eligibilityms.btach_processing;

import lombok.RequiredArgsConstructor;
import org.eligibilityms.dto.ClientDetailsDto;
import org.eligibilityms.model.EligibilityStatus;
import org.eligibilityms.proxy.BankMsProxy;
import org.eligibilityms.proxy.IaModelMsProxy;
import org.eligibilityms.repository.EligibilityStatusRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class EligibilityBtachConfig {

    private final JobRepository jobRepository;

    private final EligibilityStatusRepository eligibilityStatusRepository;

    private final IaModelMsProxy iaModelMsProxy;

    private final BankMsProxy bankMsProxy;

    private final PlatformTransactionManager platformTransactionManager;

    @Bean
    public FeignPagingItemReader clientDetailsReader(BankMsProxy clientFeignClient) {
        return new FeignPagingItemReader(clientFeignClient, getChunkSize()); // Taille du chunk
    }


    @Bean
    public EligibilityProcessor clientEligibilityProcessor(){
        return new EligibilityProcessor(iaModelMsProxy);
    }

   @Bean
    public RepositoryItemWriter<EligibilityStatus> clientEligibilityWriter(){
       RepositoryItemWriter<EligibilityStatus> writer = new RepositoryItemWriter<EligibilityStatus>();
       writer.setRepository(eligibilityStatusRepository);
       writer.setMethodName("save");
       return writer;
   }

   @Bean(name = "EligibilityStep")
    public Step EligibilityStep(){
       return new StepBuilder("EligibilityStep",jobRepository)
                .<ClientDetailsDto, EligibilityStatus>chunk( getChunkSize(), platformTransactionManager)
                .reader(clientDetailsReader(bankMsProxy))
                .processor(clientEligibilityProcessor())
                .writer(clientEligibilityWriter())
                .build();
   }


   @Bean(name = "EligibilityJob")
   public Job EligibilityJob(){
       return new JobBuilder("ClientEligibilityJob",jobRepository)
               .start(EligibilityStep())
               .preventRestart()
               .build();
   }

   public int getChunkSize(){
       String value = System.getProperty("chunkSize");
       return value != null ? Integer.parseInt(value) : 10;
   }

}
