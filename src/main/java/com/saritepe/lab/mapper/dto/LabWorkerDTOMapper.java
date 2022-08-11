package com.saritepe.lab.mapper.dto;

import com.saritepe.lab.model.dto.LabWorkerDTO;
import com.saritepe.lab.model.dto.LabWorkerReportDTO;
import com.saritepe.lab.model.entity.LabWorker;
import com.saritepe.lab.model.entity.Report;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LabWorkerDTOMapper {

    private final LabWorkerReportDTOMapper labWorkerReportDTOMapper;

    public LabWorkerDTOMapper(LabWorkerReportDTOMapper labWorkerReportDTOMapper) {
        this.labWorkerReportDTOMapper = labWorkerReportDTOMapper;
    }

    public LabWorkerDTO fromLabWorker(LabWorker labWorker) {

        Set<Report> reports = labWorker.getReports();
        Set<LabWorkerReportDTO> labWorkerReportDTOS = reports.stream().map(
                labWorkerReportDTOMapper::fromReport
        ).collect(Collectors.toSet());

        return LabWorkerDTO.LabWorkerDTOBuilder.aLabWorkerDTOWith()
                .id(labWorker.getId())
                .hospitalIdentityNumber(labWorker.getHospitalIdentityNumber())
                .firstName(labWorker.getFirstName())
                .lastName(labWorker.getLastName())
                .reports(labWorkerReportDTOS)
                .build();
    }
}
