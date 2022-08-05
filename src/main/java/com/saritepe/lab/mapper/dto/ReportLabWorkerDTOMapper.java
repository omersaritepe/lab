package com.saritepe.lab.mapper.dto;

import com.saritepe.lab.model.dto.LabWorkerDTO;
import com.saritepe.lab.model.dto.ReportLabWorkerDTO;
import com.saritepe.lab.model.entity.LabWorker;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ReportLabWorkerDTOMapper {
    public ReportLabWorkerDTO fromLabWorker(LabWorker labWorker) {
        return ReportLabWorkerDTO.ReportLabWorkerDTOBuilder.aReportLabWorkerDTOWith()
                .id(labWorker.getId())
                .hospitalIdentityNumber(labWorker.getHospitalIdentityNumber())
                .firstName(labWorker.getFirstName())
                .lastName(labWorker.getLastName())
                .build();
    }

    public ReportLabWorkerDTO fromLabWorkerDTO(LabWorkerDTO labWorkerDTO) {
        return ReportLabWorkerDTO.ReportLabWorkerDTOBuilder.aReportLabWorkerDTOWith()
                .id(labWorkerDTO.getId())
                .hospitalIdentityNumber(labWorkerDTO.getHospitalIdentityNumber())
                .firstName(labWorkerDTO.getFirstName())
                .lastName(labWorkerDTO.getLastName())
                .build();
    }
}
