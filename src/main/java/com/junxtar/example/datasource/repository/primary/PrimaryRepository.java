package com.junxtar.example.datasource.repository.primary;

import com.junxtar.example.datasource.domain.primary.A;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrimaryRepository extends JpaRepository<A, Long> {
}
