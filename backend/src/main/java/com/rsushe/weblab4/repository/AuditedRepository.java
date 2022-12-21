package com.rsushe.weblab4.repository;

import com.rsushe.weblab4.entity.AuditedCallback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditedRepository extends JpaRepository<AuditedCallback, Long> {
}
