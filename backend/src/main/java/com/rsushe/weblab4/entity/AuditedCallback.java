package com.rsushe.weblab4.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "audit_log")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AuditedCallback {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String methodName;

    @Column
    private LocalDateTime callbackTime;
}
