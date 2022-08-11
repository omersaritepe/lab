package com.saritepe.lab.repository;

import com.saritepe.lab.model.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("select report from Report report where " +
            "concat(lower(report.patientFirstName), lower(report.patientLastName), report.patientIdentityNumber" +
            ", lower(report.labWorker.firstName) , lower(report.labWorker.lastName) )" +
            "like %?1%")
    Page<Report> filterByPatientIdentityNumber(String keyword, Pageable pageable);
}
