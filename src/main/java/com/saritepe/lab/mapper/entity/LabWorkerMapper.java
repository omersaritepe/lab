package com.saritepe.lab.mapper.entity;

import com.saritepe.lab.model.dto.ReportLabWorkerDTO;
import com.saritepe.lab.model.entity.LabWorker;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LabWorkerMapper {
    public LabWorker fromReportLabWorkerDTO(ReportLabWorkerDTO reportLabWorkerDTO) {
        return LabWorker.LabWorkerBuilder.aLabWorkerWith()
                .id(reportLabWorkerDTO.getId())
                .hospitalIdentityNumber(reportLabWorkerDTO.getHospitalIdentityNumber())
                .firstName(reportLabWorkerDTO.getFirstName())
                .lastName(reportLabWorkerDTO.getLastName())
                .build();
    }
}
