package com.saritepe.lab.repository;

import com.saritepe.lab.model.entity.LabWorker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabWorkerRepository extends JpaRepository<LabWorker, Long> {
}
