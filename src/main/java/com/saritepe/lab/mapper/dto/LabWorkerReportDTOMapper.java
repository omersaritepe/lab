package com.saritepe.lab.mapper.dto;

import com.saritepe.lab.model.dto.LabWorkerReportDTO;
import com.saritepe.lab.model.entity.Report;
import org.springframework.stereotype.Component;

@Component
public class LabWorkerReportDTOMapper {
    public LabWorkerReportDTO fromReport(Report report) {
        return LabWorkerReportDTO.LabWorkerReportDTOBuilder.aLabWorkerReportDTOWith()
                .id(report.getId())
                .fileNumber(report.getFileNumber())
                .patientFirstName(report.getPatientFirstName())
                .patientLastName(report.getPatientLastName())
                .patientIdentityNumber(report.getPatientIdentityNumber())
                .diagnosisTitle(report.getDiagnosisTitle())
                .diagnosisDetail(report.getDiagnosisDetail())
                .dateOfIssue(report.getDateOfIssue())
                .image(report.getImage())
                .build();
    }
}
