package com.saritepe.lab.service;

import com.saritepe.lab.mapper.dto.ReportDTOMapper;
import com.saritepe.lab.mapper.entity.ReportMapper;
import com.saritepe.lab.model.dto.ReportDTO;
import com.saritepe.lab.model.dto.ReportLabWorkerDTO;
import com.saritepe.lab.model.entity.Report;
import com.saritepe.lab.model.exception.ReportNotFoundException;
import com.saritepe.lab.repository.ReportRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportService {

    /**
     * Repository
     */
    private final ReportRepository reportRepository;

    /**
     * Service
     */
    private final LabWorkerService labWorkerService;

    /**
     * Mapper
     */
    private final ReportMapper reportMapper;
    private final ReportDTOMapper reportDTOMapper;

    public ReportService(ReportRepository reportRepository,
                         @Lazy LabWorkerService labWorkerService,
                         ReportMapper reportMapper,
                         ReportDTOMapper reportDTOMapper) {
        this.reportRepository = reportRepository;
        this.labWorkerService = labWorkerService;
        this.reportMapper = reportMapper;
        this.reportDTOMapper = reportDTOMapper;
    }

    public ReportDTO save(ReportDTO reportDTO) {

        ReportLabWorkerDTO reportLabWorkerDTO = labWorkerService.findByHospitalIdentityNumber(
                reportDTO.getLabWorker()
                        .getHospitalIdentityNumber()
        );

        reportDTO = ReportDTO.ReportDTOBuilder.aReportDTOWith()
                .id(reportDTO.getId())
                .fileNumber(reportDTO.getFileNumber())
                .patientFirstName(reportDTO.getPatientFirstName())
                .patientLastName(reportDTO.getPatientLastName())
                .patientIdentityNumber(reportDTO.getPatientIdentityNumber())
                .diagnosisTitle(reportDTO.getDiagnosisTitle())
                .diagnosisDetail(reportDTO.getDiagnosisDetail())
                .dateOfIssue(reportDTO.getDateOfIssue())
                .image(reportDTO.getImage())
                .labWorker(reportLabWorkerDTO)
                .build();

        return reportDTOMapper.fromReport(
                reportRepository.save(
                        reportMapper.fromDTO(reportDTO)
                )
        );
    }

    public ReportDTO update(ReportDTO reportDTO, Long id) {
        
        ReportLabWorkerDTO reportLabWorkerDTO = null;
        if (reportDTO.getLabWorker() != null) {
            reportLabWorkerDTO = labWorkerService.findByHospitalIdentityNumber(
                    reportDTO.getLabWorker()
                            .getHospitalIdentityNumber()
            );
        }

        ReportDTO oldReportDTO = this.findById(id);

        ReportDTO resultReportDTO = ReportDTO.ReportDTOBuilder.aReportDTOWith()
                .id(id)
                .fileNumber(reportDTO.getFileNumber())
                .patientFirstName(reportDTO.getPatientFirstName())
                .patientLastName(reportDTO.getPatientLastName())
                .patientIdentityNumber(reportDTO.getPatientIdentityNumber())
                .diagnosisTitle(reportDTO.getDiagnosisTitle())
                .diagnosisDetail(reportDTO.getDiagnosisDetail())
                .dateOfIssue(reportDTO.getDateOfIssue())
                .image(oldReportDTO.getImage())
                .labWorker(reportLabWorkerDTO)
                .build();

        return reportDTOMapper.fromReport(
                reportRepository.save(
                        reportMapper.fromDTO(resultReportDTO)
                )
        );
    }

    public ReportDTO deleteById(Long id) {
        ReportDTO reportDTO = findById(id);

        update(ReportDTO.ReportDTOBuilder.aReportDTOWith()
                .labWorker(null)
                .build(), id);

        reportRepository.deleteById(id);
        return reportDTO;
    }

    public ReportDTO findById(Long id) {

        return reportDTOMapper.fromReport(
                reportRepository.findById(id)
                        .orElseThrow(
                        () -> new ReportNotFoundException("Report could not find by id: " + id)
                )
        );
    }

    public List<ReportDTO> findAll() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream()
                .map(reportDTOMapper::fromReport)
                .collect(Collectors.toList());
    }

    public Page<ReportDTO> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection, String keyword) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        if (keyword != null) {

            keyword = keyword.toLowerCase();

            Page<Report> filteredReports = reportRepository.filterByPatientIdentityNumber(keyword, pageable);
            return filteredReports.map(reportDTOMapper::fromReport);
        }

        Page<Report> reports = reportRepository.findAll(pageable);
        return reports.map(reportDTOMapper::fromReport);
    }
}
