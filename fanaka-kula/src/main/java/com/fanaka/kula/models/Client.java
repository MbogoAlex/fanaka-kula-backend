package com.fanaka.kula.models;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                         // bigint unsigned

    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "created_by_id")
    private Long createdById;

    @Column(name = "branch_id")
    private Long branchId;

    @Column(name = "loan_officer_id")
    private Long loanOfficerId;

    private String reference;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true, columnDefinition = "enum('male','female','other','unspecified')")
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('pending','active','inactive','deceased','unspecified','closed')")
    private ClientStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status", nullable = true,
            columnDefinition = "enum('married','single','divorced','widowed','unspecified','other')")
    private MaritalStatus maritalStatus;

    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "title_id")
    private Long titleId;

    @Column(name = "profession_id")
    private Long professionId;

    @Column(name = "client_type_id")
    private Long clientTypeId;

    private String mobile;
    private String phone;
    private String email;

    @Column(name = "external_id")
    private String externalId;

    private LocalDate dob;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String city;
    private String state;
    private String zip;
    private String employer;
    private String photo;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(columnDefinition = "TEXT")
    private String signature;

    @Column(name = "created_date")
    private LocalDate createdDate;

    @Column(name = "joined_date")
    private LocalDate joinedDate;

    @Column(name = "activation_date")
    private LocalDate activationDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "nrc_number")
    private String nrcNumber;

    @Column(name = "nrc_back")
    private String nrcBack;

    @Column(name = "nrc_front")
    private String nrcFront;

    @Column(name = "spouse_name")
    private String spouseName;

    @Column(name = "spouse_phone")
    private String spousePhone;

    @Column(name = "highest_education")
    private String highestEducation;

    private String dependants;
    private String occupation;

    @Column(name = "phone_type")
    private String phoneType;

    @Column(name = "uses_mobile_money")
    private String usesMobileMoney;

    @Column(name = "mobile_money_type")
    private String mobileMoneyType;

    private String age;

    // --- ENUM TYPES ---

    public enum Gender {
        male, female, other, unspecified
    }

    public enum ClientStatus {
        pending, active, inactive, deceased, unspecified, closed
    }

    public enum MaritalStatus {
        married, single, divorced, widowed, unspecified, other
    }
}
