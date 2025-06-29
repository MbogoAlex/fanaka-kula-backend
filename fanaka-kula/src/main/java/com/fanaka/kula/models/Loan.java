package com.fanaka.kula.models;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by_id")
    private Long createdById;

    @Enumerated(EnumType.STRING)
    @Column(name = "client_type", nullable = false, columnDefinition = "enum('client','group') default 'client'")
    private ClientType clientType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "group_id")
    private Long groupId;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "currency_id", nullable = false)
    private Long currencyId;

    @Column(name = "loan_product_id", nullable = false)
    private Long loanProductId;

    @Column(name = "loan_transaction_processing_strategy_id", nullable = false)
    private Long loanTransactionProcessingStrategyId;

    @Column(name = "fund_id", nullable = false)
    private Long fundId;

    @Column(name = "loan_purpose_id", nullable = false)
    private Long loanPurposeId;

    @Column(name = "loan_officer_id", nullable = false)
    private Long loanOfficerId;

    @Column(name = "linked_savings_id")
    private Long linkedSavingsId;

    @Column(name = "loan_disbursement_channel_id")
    private Long loanDisbursementChannelId;

    @Column(name = "submitted_on_date")
    private LocalDate submittedOnDate;

    @Column(name = "submitted_by_user_id")
    private Long submittedByUserId;

    @Column(name = "approved_on_date")
    private LocalDate approvedOnDate;

    @Column(name = "approved_by_user_id")
    private Long approvedByUserId;

    @Column(name = "approved_notes", columnDefinition = "TEXT")
    private String approvedNotes;

    @Column(name = "expected_disbursement_date")
    private LocalDate expectedDisbursementDate;

    @Column(name = "expected_first_payment_date")
    private LocalDate expectedFirstPaymentDate;

    @Column(name = "first_payment_date")
    private LocalDate firstPaymentDate;

    @Column(name = "expected_maturity_date")
    private LocalDate expectedMaturityDate;

    @Column(name = "disbursed_on_date")
    private LocalDate disbursedOnDate;

    @Column(name = "disbursed_by_user_id")
    private Long disbursedByUserId;

    @Column(name = "disbursed_notes", columnDefinition = "TEXT")
    private String disbursedNotes;

    @Column(name = "rejected_on_date")
    private LocalDate rejectedOnDate;

    @Column(name = "rejected_by_user_id")
    private Long rejectedByUserId;

    @Column(name = "rejected_notes", columnDefinition = "TEXT")
    private String rejectedNotes;

    @Column(name = "written_off_on_date")
    private LocalDate writtenOffOnDate;

    @Column(name = "written_off_by_user_id")
    private Long writtenOffByUserId;

    @Column(name = "written_off_notes", columnDefinition = "TEXT")
    private String writtenOffNotes;

    @Column(name = "closed_on_date")
    private LocalDate closedOnDate;

    @Column(name = "closed_by_user_id")
    private Long closedByUserId;

    @Column(name = "closed_notes", columnDefinition = "TEXT")
    private String closedNotes;

    @Column(name = "rescheduled_on_date")
    private LocalDate rescheduledOnDate;

    @Column(name = "rescheduled_by_user_id")
    private Long rescheduledByUserId;

    @Column(name = "rescheduled_notes", columnDefinition = "TEXT")
    private String rescheduledNotes;

    @Column(name = "withdrawn_on_date")
    private LocalDate withdrawnOnDate;

    @Column(name = "withdrawn_by_user_id")
    private Long withdrawnByUserId;

    @Column(name = "withdrawn_notes", columnDefinition = "TEXT")
    private String withdrawnNotes;

    @Column(name = "external_id", unique = true)
    private String externalId;

    @Column(name = "account_number", unique = true)
    private String accountNumber;

    @Column(nullable = false, precision = 65, scale = 6)
    private BigDecimal principal;

    @Column(name = "applied_amount", precision = 65, scale = 6)
    private BigDecimal appliedAmount;

    @Column(name = "approved_amount", precision = 65, scale = 6)
    private BigDecimal approvedAmount;

    @Column(name = "interest_rate", nullable = false, precision = 65, scale = 6)
    private BigDecimal interestRate;

    private Integer decimals;

    @Column(name = "instalment_multiple_of")
    private Integer instalmentMultipleOf = 1;

    @Column(name = "loan_term", nullable = false)
    private Integer loanTerm;

    @Column(name = "repayment_frequency", nullable = false)
    private Integer repaymentFrequency;

    @Enumerated(EnumType.STRING)
    @Column(name = "repayment_frequency_type", nullable = false,
            columnDefinition = "enum('days','weeks','months','years')")
    private RepaymentFrequencyType repaymentFrequencyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "interest_rate_type", nullable = false,
            columnDefinition = "enum('day','week','month','year','principal')")
    private InterestRateType interestRateType;

    @Column(name = "enable_balloon_payments", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean enableBalloonPayments = false;

    @Column(name = "allow_schedule_adjustments", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean allowScheduleAdjustments = false;

    @Column(name = "grace_on_principal_paid", nullable = false)
    private Integer graceOnPrincipalPaid = 0;

    @Column(name = "grace_on_interest_paid", nullable = false)
    private Integer graceOnInterestPaid = 0;

    @Column(name = "grace_on_interest_charged", nullable = false)
    private Integer graceOnInterestCharged = 0;

    @Column(name = "allow_custom_grace_period", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean allowCustomGracePeriod = false;

    @Column(name = "allow_topup", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean allowTopup = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "interest_methodology", nullable = false,
            columnDefinition = "enum('flat','declining_balance')")
    private InterestMethodology interestMethodology;

    @Column(name = "interest_recalculation", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean interestRecalculation = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "amortization_method",
            columnDefinition = "enum('equal_installments','equal_principal_payments')")
    private AmortizationMethod amortizationMethod;

    @Enumerated(EnumType.STRING)
    @Column(name = "interest_calculation_period_type",
            columnDefinition = "enum('daily','same')")
    private InterestCalculationPeriodType interestCalculationPeriodType;

    @Enumerated(EnumType.STRING)
    @Column(name = "days_in_year", columnDefinition = "enum('actual','360','365','364')")
    private DaysInYear daysInYear = DaysInYear.actual;

    @Enumerated(EnumType.STRING)
    @Column(name = "days_in_month", columnDefinition = "enum('actual','30','31')")
    private DaysInMonth daysInMonth = DaysInMonth.actual;

    @Column(name = "include_in_loan_cycle", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean includeInLoanCycle = false;

    @Column(name = "lock_guarantee_funds", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean lockGuaranteeFunds = false;

    @Column(name = "auto_allocate_overpayments", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean autoAllocateOverpayments = false;

    @Column(name = "allow_additional_charges", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean allowAdditionalCharges = false;

    @Column(name = "auto_disburse", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean autoDisburse = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",
            columnDefinition = "enum('pending','awaiting_disbursement','processing_disbursement','active','on_track','off_track','past_maturity','fully_paid','incomplete','on_hold')")
    private LoanStatus status;

    @Column(name = "disbursement_charges", precision = 65, scale = 6)
    private BigDecimal disbursementCharges;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private String classification;

    @Column(name = "deduct_interest_from_principal", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean deductInterestFromPrincipal = false;

    @Column(name = "loan_provisioning_id")
    private Long loanProvisioningId;

    private String amount;
    private String purpose;

    @Column(name = "projected_profit")
    private String projectedProfit;

    private Double interest;
    private Double penalties;

    @Column(name = "last_repayment_id")
    private Long lastRepaymentId;

    @Column(name = "repayment_schedule")
    private String repaymentSchedule;

    @Column(name = "onboarding_stage")
    private String onboardingStage;

    @Column(name = "put_on_hold_date")
    private LocalDateTime putOnHoldDate;

    @Column(name = "put_on_hold_by_user_id")
    private Long putOnHoldByUserId;

    @Column(name = "put_on_hold_notes", columnDefinition = "TEXT")
    private String putOnHoldNotes;

    public enum ClientType { client, group }
    public enum RepaymentFrequencyType { days, weeks, months, years }
    public enum InterestRateType { day, week, month, year, principal }
    public enum InterestMethodology { flat, declining_balance }
    public enum AmortizationMethod { equal_installments, equal_principal_payments }
    public enum InterestCalculationPeriodType { daily, same }
    public enum DaysInYear {
        actual("actual"),
        d360("360"),
        d365("365"),
        d364("364");

        private final String dbValue;
        DaysInYear(String dbValue) { this.dbValue = dbValue; }

        @Override
        public String toString() { return dbValue; }
    }

    public enum DaysInMonth {
        actual("actual"),
        m30("30"),
        m31("31");

        private final String dbValue;
        DaysInMonth(String dbValue) { this.dbValue = dbValue; }

        @Override
        public String toString() { return dbValue; }
    }
    public enum LoanStatus {
        pending, awaiting_disbursement, processing_disbursement,
        active, on_track, off_track, past_maturity,
        fully_paid, incomplete, on_hold
    }
}
