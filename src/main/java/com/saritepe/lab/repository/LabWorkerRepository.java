package com.saritepe.lab.repository;

import com.saritepe.lab.model.entity.LabWorker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabWorkerRepository extends JpaRepository<LabWorker, Long> {

    LabWorker findByHospitalIdentityNumber(String hospitalIdentityNumber);
}
