package com.junxtar.example.datasource.repository.secondary;

import com.junxtar.example.datasource.domain.secondary.A;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SecondaryRepository extends JpaRepository<A, Long> {
}
