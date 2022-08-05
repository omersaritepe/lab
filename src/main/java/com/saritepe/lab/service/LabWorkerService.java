package com.saritepe.lab.service;

import com.saritepe.lab.mapper.dto.LabWorkerDTOMapper;
import com.saritepe.lab.mapper.dto.ReportLabWorkerDTOMapper;
import com.saritepe.lab.mapper.entity.LabWorkerMapper;
import com.saritepe.lab.model.dto.LabWorkerDTO;
import com.saritepe.lab.model.dto.LabWorkerReportDTO;
import com.saritepe.lab.model.dto.ReportDTO;
import com.saritepe.lab.model.dto.ReportLabWorkerDTO;
import com.saritepe.lab.model.entity.LabWorker;
import com.saritepe.lab.model.exception.LabWorkerNotFoundException;
import com.saritepe.lab.repository.LabWorkerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LabWorkerService {

    /** Repository */
    private final LabWorkerRepository labWorkerRepository;

    /** Service */
    private final ReportService reportService;

    /** Mapper */
    private final LabWorkerMapper labWorkerMapper;
    private final LabWorkerDTOMapper labWorkerDTOMapper;
    private final ReportLabWorkerDTOMapper reportLabWorkerDTOMapper;

    public LabWorkerService(LabWorkerRepository labWorkerRepository,
                            ReportService reportService, LabWorkerMapper labWorkerMapper,
                            LabWorkerDTOMapper labWorkerDTOMapper,
                            ReportLabWorkerDTOMapper reportLabWorkerDTOMapper) {
        this.labWorkerRepository = labWorkerRepository;
        this.reportService = reportService;
        this.labWorkerMapper = labWorkerMapper;
        this.labWorkerDTOMapper = labWorkerDTOMapper;
        this.reportLabWorkerDTOMapper = reportLabWorkerDTOMapper;
    }

    public ReportLabWorkerDTO save(ReportLabWorkerDTO reportLabWorkerDTO) {
        return reportLabWorkerDTOMapper.fromLabWorker(labWorkerRepository.save(labWorkerMapper.fromReportLabWorkerDTO(reportLabWorkerDTO)));
    }

    public ReportLabWorkerDTO update(ReportLabWorkerDTO reportLabWorkerDTO, Long id) {
        ReportLabWorkerDTO resultReportLabWorkerDTO = ReportLabWorkerDTO.ReportLabWorkerDTOBuilder.aReportLabWorkerDTOWith()
                .id(id)
                .hospitalIdentityNumber(reportLabWorkerDTO.getHospitalIdentityNumber())
                .firstName(reportLabWorkerDTO.getFirstName())
                .lastName(reportLabWorkerDTO.getLastName())
                .build();

        return reportLabWorkerDTOMapper.fromLabWorker(labWorkerRepository.save(labWorkerMapper.fromReportLabWorkerDTO(reportLabWorkerDTO)));

    }

    public String deleteById(Long id) {
        LabWorkerDTO labWorkerDTO = findById(id);

        Set<LabWorkerReportDTO> labWorkerReportDTOS = labWorkerDTO.getReports();
        if (labWorkerReportDTOS != null) {
            labWorkerReportDTOS.forEach(labWorkerReportDTO -> reportService.update(
                            ReportDTO.ReportDTOBuilder.aReportDTOWith()
                                    .id(labWorkerReportDTO.getId())
                                    .fileNumber(labWorkerReportDTO.getFileNumber())
                                    .patientFirstName(labWorkerReportDTO.getPatientFirstName())
                                    .patientLastName(labWorkerReportDTO.getPatientLastName())
                                    .patientIdentityNumber(labWorkerReportDTO.getPatientIdentityNumber())
                                    .diagnosisTitle(labWorkerReportDTO.getDiagnosisTitle())
                                    .diagnosisDetail(labWorkerReportDTO.getDiagnosisDetail())
                                    .dateOfIssue(labWorkerReportDTO.getDateOfIssue())
                                    .image(labWorkerReportDTO.getImage())
                                    .labWorker(null)
                                    .build(), labWorkerReportDTO.getId()
                    )
            ); //spring context list bütün been list

        }

        labWorkerRepository.deleteById(id);
        return "Deletion successful";
    }

    public LabWorkerDTO findById(Long id) {
        return labWorkerDTOMapper.fromLabWorker(labWorkerRepository.findById(id)
                .orElseThrow(() -> new LabWorkerNotFoundException("LabWorker could not find by id: " + id)));
    }

    public List<LabWorkerDTO> findAll() {
        List<LabWorker> labWorkers = labWorkerRepository.findAll();
        return labWorkers.stream().map(labWorkerDTOMapper::fromLabWorker).collect(Collectors.toList());
    }

    public Page<LabWorkerDTO> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);
        Page<LabWorker> labWorkers = labWorkerRepository.findAll(pageable);
        return labWorkers.map(labWorkerDTOMapper::fromLabWorker);
    }
}
