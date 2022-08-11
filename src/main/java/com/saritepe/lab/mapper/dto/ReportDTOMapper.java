package com.saritepe.lab.mapper.dto;

import com.saritepe.lab.model.dto.ReportDTO;
import com.saritepe.lab.model.entity.Report;
import org.springframework.stereotype.Component;

@Component
public class ReportDTOMapper {
    
    private final ReportLabWorkerDTOMapper reportLabWorkerDTOMapper;

    public ReportDTOMapper(ReportLabWorkerDTOMapper reportLabWorkerDTOMapper) {
        this.reportLabWorkerDTOMapper = reportLabWorkerDTOMapper;
    }

    public ReportDTO fromReport(Report report) {

        ReportDTO.ReportDTOBuilder reportDTOBuilder = ReportDTO.ReportDTOBuilder.aReportDTOWith()
                .id(report.getId())
                .fileNumber(report.getFileNumber())
                .patientFirstName(report.getPatientFirstName())
                .patientLastName(report.getPatientLastName())
                .patientIdentityNumber(report.getPatientIdentityNumber())
                .diagnosisTitle(report.getDiagnosisTitle())
                .diagnosisDetail(report.getDiagnosisDetail())
                .dateOfIssue(report.getDateOfIssue())
                .image(report.getImage());

        if (report.getLabWorker() != null) {
            reportDTOBuilder.labWorker(reportLabWorkerDTOMapper.fromLabWorker(report.getLabWorker()));
        }

        return reportDTOBuilder.build();
    }
}
