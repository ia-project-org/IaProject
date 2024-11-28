package org.bankms.clientsms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
public class ClientDetails {

    @Id
    @JsonProperty("clientId")
    private Long clientId;

    @JsonProperty("month")
    @Column(nullable = false)
    private Integer month;

    @JsonProperty("age")
    @Column(nullable = false)
    private Integer age;

    @JsonProperty("annual_income")
    @Column(nullable = false)
    private Double annualIncome;

    @JsonProperty("monthly_inhand_salary")
    @Column(nullable = false)
    private Double monthlyInhandSalary;

    @JsonProperty("total_emi_per_month")
    @Column(nullable = false)
    private Double totalEmiPerMonth;

    @JsonProperty("num_bank_accounts")
    @Column(nullable = false)
    private Integer numBankAccounts;

    @JsonProperty("num_credit_card")
    @Column(nullable = false)
    private Integer numCreditCard;

    @JsonProperty("interest_rate")
    @Column(nullable = false)
    private Double interestRate;

    @JsonProperty("num_of_loan")
    @Column(nullable = false)
    private Integer numOfLoan;

    @JsonProperty("delay_from_due_date")
    @Column(nullable = false)
    private Integer delayFromDueDate;

    @JsonProperty("num_of_delayed_payment")
    @Column(nullable = false)
    private Integer numOfDelayedPayment;

    @JsonProperty("changed_credit_limit")
    @Column(nullable = false)
    private Double changedCreditLimit;

    @JsonProperty("num_credit_inquiries")
    @Column(nullable = false)
    private Integer numCreditInquiries;

    @JsonProperty("credit_mix")
    @Column(nullable = false)
    private String creditMix;

    @JsonProperty("outstanding_debt")
    @Column(nullable = false)
    private Double outstandingDebt;

    @JsonProperty("credit_utilization_ratio")
    @Column(nullable = false)
    private Double creditUtilizationRatio;

    @JsonProperty("credit_history_age")
    @Column(nullable = false)
    private Integer creditHistoryAge;

    @JsonProperty("payment_of_min_amount")
    @Column(nullable = false)
    private String paymentOfMinAmount;

    @JsonProperty("amount_invested_monthly")
    @Column(nullable = false)
    private Double amountInvestedMonthly;

    @JsonProperty("payment_behaviour")
    @Column(nullable = false)
    private Integer paymentBehaviour;

    @JsonProperty("monthly_balance")
    @Column(nullable = false)
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

    public ClientDetails(){

    }
}
