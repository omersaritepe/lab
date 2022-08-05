package com.saritepe.lab.mapper.entity;

import com.saritepe.lab.model.dto.ReportDTO;
import com.saritepe.lab.model.entity.Report;
import org.springframework.stereotype.Component;

@Component
public class ReportMapper {

    private final LabWorkerMapper labWorkerMapper;

    public ReportMapper(LabWorkerMapper labWorkerMapper) {
        this.labWorkerMapper = labWorkerMapper;
    }

    public Report fromDTO(ReportDTO reportDTO) {

        Report.ReportBuilder reportBuilder = Report.ReportBuilder.aReportWith()
                .id(reportDTO.getId())
                .fileNumber(reportDTO.getFileNumber())
                .patientFirstName(reportDTO.getPatientFirstName())
                .patientLastName(reportDTO.getPatientLastName())
                .patientIdentityNumber(reportDTO.getPatientIdentityNumber())
                .diagnosisTitle(reportDTO.getDiagnosisTitle())
                .diagnosisDetail(reportDTO.getDiagnosisDetail())
                .dateOfIssue(reportDTO.getDateOfIssue())
                .image(reportDTO.getImage());
        if (reportDTO.getLabWorker() != null) {
            reportBuilder.labWorker(labWorkerMapper.fromReportLabWorkerDTO(reportDTO.getLabWorker()));
        }

        return reportBuilder.build();
    }
}
