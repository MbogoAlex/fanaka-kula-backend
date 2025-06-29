package com.fanaka.kula.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 191)
    private String name;

    @Column(name = "system_name", length = 191)
    private String systemName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_cash", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isCash = false;

    @Column(name = "is_online", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isOnline = false;

    @Column(name = "is_system", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean isSystem = false;

    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean active = true;

    @Column
    private Integer position;

    @Column(columnDefinition = "TEXT")
    private String options;

    @Column(name = "unique_id", length = 191)
    private String uniqueId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
