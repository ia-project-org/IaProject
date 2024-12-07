package org.bankms.clientsms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientCsvRecord {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String cin;

    @JsonProperty("month")
    private Integer month;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("annual_income")
    private Double annualIncome;

    @JsonProperty("monthly_inhand_salary")
    private Double monthlyInhandSalary;

    @JsonProperty("total_emi_per_month")
    private Double totalEmiPerMonth;

    @JsonProperty("num_bank_accounts")
    private Integer numBankAccounts;

    @JsonProperty("num_credit_card")
    private Integer numCreditCard;

    @JsonProperty("interest_rate")
    private Double interestRate;

    @JsonProperty("num_of_loan")
    private Integer numOfLoan;

    @JsonProperty("delay_from_due_date")
    private Integer delayFromDueDate;

    @JsonProperty("num_of_delayed_payment")
    private Integer numOfDelayedPayment;

    @JsonProperty("changed_credit_limit")
    private Double changedCreditLimit;

    @JsonProperty("num_credit_inquiries")
    private Integer numCreditInquiries;

    @JsonProperty("credit_mix")
    private String creditMix;

    @JsonProperty("outstanding_debt")
    private Double outstandingDebt;

    @JsonProperty("credit_utilization_ratio")
    private Double creditUtilizationRatio;

    @JsonProperty("credit_history_age")
    private Integer creditHistoryAge;

    @JsonProperty("payment_of_min_amount")
    private String paymentOfMinAmount;

    @JsonProperty("amount_invested_monthly")
    private Double amountInvestedMonthly;

    @JsonProperty("payment_behaviour")
    private Integer paymentBehaviour;

    @JsonProperty("monthly_balance")
    private Double monthlyBalance;

    @JsonProperty("auto_loan")
    private Integer autoLoan;

    @JsonProperty("credit_builder_loan")
    private Integer creditBuilderLoan;

    @JsonProperty("debt_consolidation_loan")
    private Integer debtConsolidationLoan;

    @JsonProperty("home_equity_loan")
    private Integer homeEquityLoan;

    @JsonProperty("mortgage_loan")
    private Integer mortgageLoan;

    @JsonProperty("no_loan")
    private Integer noLoan;

    @JsonProperty("not_specified")
    private Integer notSpecified;

    @JsonProperty("payday_loan")
    private Integer paydayLoan;

    @JsonProperty("personal_loan")
    private Integer personalLoan;

    @JsonProperty("student_loan")
    private Integer studentLoan;

    @JsonProperty("occupation_Accountant")
    private Integer occupationAccountant;

    @JsonProperty("occupation_Architect")
    private Integer occupationArchitect;

    @JsonProperty("occupation_Developer")
    private Integer occupationDeveloper;

    @JsonProperty("occupation_Doctor")
    private Integer occupationDoctor;

    @JsonProperty("occupation_Engineer")
    private Integer occupationEngineer;

    @JsonProperty("occupation_Entrepreneur")
    private Integer occupationEntrepreneur;

    @JsonProperty("occupation_Journalist")
    private Integer occupationJournalist;

    @JsonProperty("occupation_Lawyer")
    private Integer occupationLawyer;

    @JsonProperty("occupation_Manager")
    private Integer occupationManager;

    @JsonProperty("occupation_Mechanic")
    private Integer occupationMechanic;

    @JsonProperty("occupation_Media_Manager")
    private Integer occupationMediaManager;

    @JsonProperty("occupation_Musician")
    private Integer occupationMusician;

    @JsonProperty("occupation_Scientist")
    private Integer occupationScientist;

    @JsonProperty("occupation_Teacher")
    private Integer occupationTeacher;

    @JsonProperty("occupation_Writer")
    private Integer occupationWriter;
}
