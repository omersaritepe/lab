package com.saritepe.lab.service;

import com.saritepe.lab.mapper.dto.ReportDTOMapper;
import com.saritepe.lab.mapper.entity.ReportMapper;
import com.saritepe.lab.model.dto.ReportDTO;
import com.saritepe.lab.model.dto.ReportLabWorkerDTO;
import com.saritepe.lab.model.entity.LabWorker;
import com.saritepe.lab.model.entity.Report;
import com.saritepe.lab.model.exception.ReportNotFoundException;
import com.saritepe.lab.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportServiceTest {

    private ReportService service;
    private ReportRepository reportRepository;

    private LabWorkerService labWorkerService;
    private ReportMapper reportMapper;
    private ReportDTOMapper reportDTOMapper;

    @BeforeEach
    void setUp() {
        reportRepository = mock(ReportRepository.class);
        labWorkerService = mock(LabWorkerService.class);
        reportMapper = mock(ReportMapper.class);
        reportDTOMapper = mock(ReportDTOMapper.class);
        service = new ReportService(reportRepository, labWorkerService, reportMapper, reportDTOMapper);

    }

    @Test
    public void testSave_whenReportDTOExists_shouldReturnReportDTO() {
        ReportLabWorkerDTO reportLabWorkerDTO = ReportLabWorkerDTO.ReportLabWorkerDTOBuilder.aReportLabWorkerDTOWith()
                .id(1L)
                .hospitalIdentityNumber("1234567")
                .firstName("first")
                .lastName("last")
                .build();

        LabWorker labWorker = LabWorker.LabWorkerBuilder.aLabWorkerWith()
                .id(1L)
                .hospitalIdentityNumber("1234567")
                .firstName("first")
                .lastName("last")
                .build();

        ReportDTO reportDTO = ReportDTO.ReportDTOBuilder.aReportDTOWith()
                .id(1L)
                .fileNumber("100")
                .patientIdentityNumber("10000000000")
                .patientFirstName("First")
                .patientLastName("Last")
                .diagnosisTitle("Title")
                .diagnosisDetail("Detail")
                .dateOfIssue("2022-07-30")
                .image("abc.jpg")
                .labWorker(reportLabWorkerDTO)
                .build();

        Report report = Report.ReportBuilder.aReportWith()
                .id(1L)
                .fileNumber("100")
                .patientIdentityNumber("10000000000")
                .patientFirstName("First")
                .patientLastName("Last")
                .diagnosisTitle("Title")
                .diagnosisDetail("Detail")
                .dateOfIssue("2022-07-30")
                .image("abc.jpg")
                .labWorker(labWorker)
                .build();

        when(labWorkerService.findByHospitalIdentityNumber("1234567")).thenReturn(reportLabWorkerDTO);
        when(reportMapper.fromDTO(reportDTO)).thenReturn(report);
        when(reportRepository.save(report)).thenReturn(report);
        when(reportDTOMapper.fromReport(report)).thenReturn(reportDTO);

        ReportDTO resultReportDTO = service.save(reportDTO);


        assertEquals(resultReportDTO, reportDTO);

        verify(labWorkerService).findByHospitalIdentityNumber("1234567");
        verify(reportMapper).fromDTO(reportDTO);
        verify(reportRepository).save(report);
        verify(reportDTOMapper).fromReport(report);
    }

    @Test
    public void testUpdate_whenReportDTOAndIdExists_shouldReturnReportDTO() {

        ReportLabWorkerDTO reportLabWorkerDTO = ReportLabWorkerDTO.ReportLabWorkerDTOBuilder.aReportLabWorkerDTOWith()
                .id(1L)
                .hospitalIdentityNumber("1234567")
                .firstName("first")
                .lastName("last")
                .build();

        LabWorker labWorker = LabWorker.LabWorkerBuilder.aLabWorkerWith()
                .id(1L)
                .hospitalIdentityNumber("1234567")
                .firstName("first")
                .lastName("last")
                .build();

        ReportDTO reportDTO = ReportDTO.ReportDTOBuilder.aReportDTOWith()
                .id(1L)
                .fileNumber("100")
                .patientIdentityNumber("10000000000")
                .patientFirstName("First")
                .patientLastName("Last")
                .diagnosisTitle("Title")
                .diagnosisDetail("Detail")
                .dateOfIssue("2022-07-30")
                .image("abc.jpg")
                .labWorker(reportLabWorkerDTO)
                .build();

        Report report = Report.ReportBuilder.aReportWith()
                .id(1L)
                .fileNumber("100")
                .patientIdentityNumber("10000000000")
                .patientFirstName("First")
                .patientLastName("Last")
                .diagnosisTitle("Title")
                .diagnosisDetail("Detail")
                .dateOfIssue("2022-07-30")
                .image("abc.jpg")
                .labWorker(labWorker)
                .build();

        ReportDTO resultReportDTO = ReportDTO.ReportDTOBuilder.aReportDTOWith()
                .id(1L)
                .fileNumber("100")
                .patientIdentityNumber("10000000000")
                .patientFirstName("First")
                .patientLastName("Last")
                .diagnosisTitle("Title")
                .diagnosisDetail("Detail")
                .dateOfIssue("2022-07-30")
                .image("abc.jpg")
                .labWorker(reportLabWorkerDTO)
                .build();


        when(labWorkerService.findByHospitalIdentityNumber("1234567")).thenReturn(reportLabWorkerDTO);
        when(reportRepository.findById(1L)).thenReturn(Optional.of(report));
        when(reportMapper.fromDTO(reportDTO)).thenReturn(report);
        when(reportRepository.save(report)).thenReturn(report);
        when(reportDTOMapper.fromReport(report)).thenReturn(reportDTO);

        ReportDTO finalReportDTO = service.update(reportDTO, 1L);

        assertEquals(finalReportDTO, reportDTO);

        verify(labWorkerService).findByHospitalIdentityNumber("1234567");
        verify(reportRepository).findById(1L);
        verify(reportMapper).fromDTO(reportDTO);
        verify(reportRepository).save(report);
        verify(reportDTOMapper, times(2)).fromReport(report);
    }

    @Test
    public void testFindByID_whenIdExists_shouldReturnReportDTO() {

        ReportLabWorkerDTO reportLabWorkerDTO = ReportLabWorkerDTO.ReportLabWorkerDTOBuilder.aReportLabWorkerDTOWith()
                .id(1L)
                .hospitalIdentityNumber("1234567")
                .firstName("first")
                .lastName("last")
                .build();

        LabWorker labWorker = LabWorker.LabWorkerBuilder.aLabWorkerWith()
                .id(1L)
                .hospitalIdentityNumber("1234567")
                .firstName("first")
                .lastName("last")
                .build();

        ReportDTO reportDTO = ReportDTO.ReportDTOBuilder.aReportDTOWith()
                .id(1L)
                .fileNumber("100")
                .patientIdentityNumber("10000000000")
                .patientFirstName("First")
                .patientLastName("Last")
                .diagnosisTitle("Title")
                .diagnosisDetail("Detail")
                .dateOfIssue("2022-07-30")
                .image("abc.jpg")
                .labWorker(reportLabWorkerDTO)
                .build();

        Report report = Report.ReportBuilder.aReportWith()
                .id(1L)
                .fileNumber("100")
                .patientIdentityNumber("10000000000")
                .patientFirstName("First")
                .patientLastName("Last")
                .diagnosisTitle("Title")
                .diagnosisDetail("Detail")
                .dateOfIssue("2022-07-30")
                .image("abc.jpg")
                .labWorker(labWorker)
                .build();

        when(reportDTOMapper.fromReport(report)).thenReturn(reportDTO);
        when(reportRepository.findById(1L)).thenReturn(Optional.of(report));

        ReportDTO resultReport = service.findById(1L);

        assertEquals(resultReport, reportDTO);

        verify(reportDTOMapper).fromReport(report);
        verify(reportRepository).findById(1L);

    }

    @Test
    public void testFindByID_whenIdNotExists_shouldReturnThrowReportNotFoundException() {

        when(reportRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ReportNotFoundException.class,
                () -> service.findById(1L));
    }

    @Test
    public void testFindAll_shouldReturnReportDTOList() {

        ReportLabWorkerDTO reportLabWorkerDTO = ReportLabWorkerDTO.ReportLabWorkerDTOBuilder.aReportLabWorkerDTOWith()
                .id(1L)
                .hospitalIdentityNumber("1234567")
                .firstName("first")
                .lastName("last")
                .build();

        LabWorker labWorker = LabWorker.LabWorkerBuilder.aLabWorkerWith()
                .id(1L)
                .hospitalIdentityNumber("1234567")
                .firstName("first")
                .lastName("last")
                .build();

        ReportDTO reportDTO = ReportDTO.ReportDTOBuilder.aReportDTOWith()
                .id(1L)
                .fileNumber("100")
                .patientIdentityNumber("10000000000")
                .patientFirstName("First")
                .patientLastName("Last")
                .diagnosisTitle("Title")
                .diagnosisDetail("Detail")
                .dateOfIssue("2022-07-30")
                .image("abc.jpg")
                .labWorker(reportLabWorkerDTO)
                .build();

        Report report = Report.ReportBuilder.aReportWith()
                .id(1L)
                .fileNumber("100")
                .patientIdentityNumber("10000000000")
                .patientFirstName("First")
                .patientLastName("Last")
                .diagnosisTitle("Title")
                .diagnosisDetail("Detail")
                .dateOfIssue("2022-07-30")
                .image("abc.jpg")
                .labWorker(labWorker)
                .build();

        ArrayList<Report> reports = new ArrayList<>();
        reports.add(report);

        when(reportRepository.findAll()).thenReturn(reports);
        when(reportDTOMapper.fromReport(report)).thenReturn(reportDTO);

        List<ReportDTO> resultReports = service.findAll();

        assertEquals(resultReports.get(0), reportDTO);

        verify(reportRepository).findAll();
        verify(reportDTOMapper).fromReport(report);

    }
}