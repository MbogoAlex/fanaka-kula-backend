package com.fanaka.kula.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                              // bigint unsigned

    @Column(name = "created_by_id")
    private Integer createdById;                  // int

    @Column(name = "branch_id")
    private Integer branchId;                     // int

    @Column(nullable = false)
    private String name;                          // varchar(191)

    private String username;                      // varchar(191)

    @Column(nullable = false, unique = true)
    private String email;                         // varchar(191)

    @Column(name = "email_verified_at")
    private LocalDateTime emailVerifiedAt;        // timestamp

    @Column(nullable = false)
    private String password;                      // varchar(191)

    @Column(name = "api_token", unique = true)
    private String apiToken;                      // varchar(80)

    @Column(name = "last_login")
    private LocalDateTime lastLogin;              // timestamp

    @Column(name = "first_name")
    private String firstName;                     // varchar(191)

    @Column(name = "last_name")
    private String lastName;                      // varchar(191)

    private String phone;                         // varchar(191)

    @Column(columnDefinition = "TEXT")
    private String address;                       // text

    private String city;                          // varchar(191)

    private String gender;                        // varchar(191)

    @Column(name = "enable_google2fa", nullable = false)
    private Boolean enableGoogle2fa;              // tinyint → Boolean

    @Column(name = "google2fa_secret", columnDefinition = "TEXT")
    private String google2faSecret;               // text

    private String otp;                           // varchar(191)

    @Column(name = "otp_expiry_date")
    private LocalDateTime otpExpiryDate;          // timestamp

    @Column(columnDefinition = "TEXT")
    private String notes;                         // text

    @Column(columnDefinition = "TEXT")
    private String photo;                         // text

    @Column(name = "remember_token")
    private String rememberToken;                 // varchar(100)

    @Column(name = "created_at")
    private LocalDateTime createdAt;              // timestamp

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;              // timestamp

    @Column(name = "region_id")
    private Long regionId;                        // bigint unsigned

    @Column(name = "district_id")
    private Long districtId;                      // bigint unsigned

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "model_has_roles",
            joinColumns = @JoinColumn(name = "model_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    @org.hibernate.annotations.WhereJoinTable(clause = "model_type = 'UserEntity'")
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Set<Role> roles;
}
