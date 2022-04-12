package com.medicine.medicine.domain.repository;

import com.medicine.medicine.domain.entity.MediEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MediRepository extends JpaRepository<MediEntity, Long> {
    List<MediEntity> findByTitleContaining(String keyword);
    List<MediEntity> findBytransContaining(String trans);
    List<MediEntity> findBydelivertBetween(LocalDate start, LocalDate end);
    List<MediEntity> findByid(Long id);
}
