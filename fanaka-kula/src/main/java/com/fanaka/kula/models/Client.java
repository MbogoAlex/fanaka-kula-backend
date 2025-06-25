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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                         // bigint unsigned

    @Column(name = "region_id")
    private Long regionId;                   // bigint unsigned

    @Column(name = "district_id")
    private Long districtId;                 // bigint unsigned

    @Column(name = "created_by_id")
    private Long createdById;                // bigint unsigned

    @Column(name = "branch_id")
    private Long branchId;                   // bigint unsigned

    @Column(name = "loan_officer_id")
    private Long loanOfficerId;              // bigint unsigned

    private String reference;                // varchar(191)

    @Column(name = "account_number")
    private String accountNumber;            // varchar(191)

    @Column(name = "first_name")
    private String firstName;                // varchar(191)

    @Column(name = "middle_name")
    private String middleName;               // varchar(191)

    @Column(name = "last_name")
    private String lastName;                 // varchar(191)

    private String gender;                   // enum → String (e.g. “male”)

    @Column(nullable = false)
    private String status;                   // enum, NOT NULL

    @Column(name = "marital_status")
    private String maritalStatus;            // enum

    @Column(name = "country_id")
    private Long countryId;                  // bigint unsigned

    @Column(name = "title_id")
    private Long titleId;                    // bigint unsigned

    @Column(name = "profession_id")
    private Long professionId;               // bigint unsigned

    @Column(name = "client_type_id")
    private Long clientTypeId;               // bigint unsigned

    private String mobile;                   // varchar(191)

    private String phone;                    // varchar(191)

    private String email;                    // varchar(191)

    @Column(name = "external_id")
    private String externalId;               // varchar(191)

    private LocalDate dob;                   // DATE

    @Column(columnDefinition = "TEXT")
    private String address;                  // text

    private String city;                     // varchar(191)

    private String state;                    // varchar(191)

    private String zip;                      // varchar(191)

    private String employer;                 // varchar(191)

    private String photo;                    // varchar(191)

    @Column(columnDefinition = "TEXT")
    private String notes;                    // text

    @Column(columnDefinition = "TEXT")
    private String signature;                // text

    @Column(name = "created_date")
    private LocalDate createdDate;           // DATE

    @Column(name = "joined_date")
    private LocalDate joinedDate;            // DATE

    @Column(name = "activation_date")
    private LocalDate activationDate;        // DATE

    @Column(name = "created_at")
    private LocalDateTime createdAt;         // TIMESTAMP

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;         // TIMESTAMP

    @Column(name = "nrc_number")
    private String nrcNumber;                // varchar(255)

    @Column(name = "nrc_back")
    private String nrcBack;                  // varchar(191)

    @Column(name = "nrc_front")
    private String nrcFront;                 // varchar(191)

    @Column(name = "spouse_name")
    private String spouseName;               // varchar(191)

    @Column(name = "spouse_phone")
    private String spousePhone;              // varchar(191)

    @Column(name = "highest_education")
    private String highestEducation;         // varchar(191)

    private String dependants;               // varchar(191)

    private String occupation;               // varchar(191)

    @Column(name = "phone_type")
    private String phoneType;                // varchar(191)

    @Column(name = "uses_mobile_money")
    private String usesMobileMoney;          // varchar(191)

    @Column(name = "mobile_money_type")
    private String mobileMoneyType;          // varchar(191)

    private String age;                      // varchar(191)
}
