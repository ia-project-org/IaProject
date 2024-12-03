package org.bankms.clientsms.mapper;

import org.bankms.clientsms.dto.ClientCsvRecord;
import org.bankms.clientsms.model.ClientDetails;

public class ClientDetailsMapper {

    public static ClientDetails fromClientCsvRecordToClientDetails(ClientCsvRecord record) {
        return ClientDetails.builder()
                .month(record.getMonth())
                .age(record.getAge())
                .annualIncome(record.getAnnualIncome())
                .monthlyInhandSalary(record.getMonthlyInhandSalary())
                .totalEmiPerMonth(record.getTotalEmiPerMonth())
                .numBankAccounts(record.getNumBankAccounts())
                .numCreditCard(record.getNumCreditCard())
                .interestRate(record.getInterestRate())
                .numOfLoan(record.getNumOfLoan())
                .delayFromDueDate(record.getDelayFromDueDate())
                .numOfDelayedPayment(record.getNumOfDelayedPayment())
                .changedCreditLimit(record.getChangedCreditLimit())
                .numCreditInquiries(record.getNumCreditInquiries())
                .creditMix(record.getCreditMix())
                .outstandingDebt(record.getOutstandingDebt())
                .creditUtilizationRatio(record.getCreditUtilizationRatio())
                .creditHistoryAge(record.getCreditHistoryAge())
                .paymentOfMinAmount(record.getPaymentOfMinAmount())
                .amountInvestedMonthly(record.getAmountInvestedMonthly())
                .paymentBehaviour(record.getPaymentBehaviour())
                .monthlyBalance(record.getMonthlyBalance())
                .autoLoan(record.getAutoLoan())
                .creditBuilderLoan(record.getCreditBuilderLoan())
                .debtConsolidationLoan(record.getDebtConsolidationLoan())
                .homeEquityLoan(record.getHomeEquityLoan())
                .mortgageLoan(record.getMortgageLoan())
                .noLoan(record.getNoLoan())
                .notSpecified(record.getNotSpecified())
                .paydayLoan(record.getPaydayLoan())
                .personalLoan(record.getPersonalLoan())
                .studentLoan(record.getStudentLoan())
                .occupationAccountant(record.getOccupationAccountant())
                .occupationArchitect(record.getOccupationArchitect())
                .occupationDeveloper(record.getOccupationDeveloper())
                .occupationDoctor(record.getOccupationDoctor())
                .occupationEngineer(record.getOccupationEngineer())
                .occupationEntrepreneur(record.getOccupationEntrepreneur())
                .occupationJournalist(record.getOccupationJournalist())
                .occupationLawyer(record.getOccupationLawyer())
                .occupationManager(record.getOccupationManager())
                .occupationMechanic(record.getOccupationMechanic())
                .occupationMediaManager(record.getOccupationMediaManager())
                .occupationMusician(record.getOccupationMusician())
                .occupationScientist(record.getOccupationScientist())
                .occupationTeacher(record.getOccupationTeacher())
                .occupationWriter(record.getOccupationWriter())
                .build();
    }
}
