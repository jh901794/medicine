package com.medicine.medicine.domain.repository;


import com.medicine.medicine.domain.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByMediidAndCategory(Long Mediid, String category);
}