package com.medicine.medicine.domain.repository;


import com.medicine.medicine.domain.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}