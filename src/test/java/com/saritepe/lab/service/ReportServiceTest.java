package com.saritepe.lab.service;

import com.saritepe.lab.mapper.dto.ReportDTOMapper;
import com.saritepe.lab.mapper.entity.ReportMapper;
import com.saritepe.lab.model.dto.ReportDTO;
import com.saritepe.lab.model.dto.ReportLabWorkerDTO;
import com.saritepe.lab.model.entity.LabWorker;
import com.saritepe.lab.model.entity.Report;
import com.saritepe.lab.model.exception.ReportNotFoundException;
import com.saritepe.lab.repository.ReportRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        Mockito.when(labWorkerService.findByHospitalIdentityNumber("1234567")).thenReturn(reportLabWorkerDTO);
        Mockito.when(reportMapper.fromDTO(reportDTO)).thenReturn(report);
        Mockito.when(reportRepository.save(report)).thenReturn(report);
        Mockito.when(reportDTOMapper.fromReport(report)).thenReturn(reportDTO);

        ReportDTO resultReportDTO = service.save(reportDTO);


        Assert.assertEquals(resultReportDTO, reportDTO);

        Mockito.verify(labWorkerService).findByHospitalIdentityNumber("1234567");
        Mockito.verify(reportMapper).fromDTO(reportDTO);
        Mockito.verify(reportRepository).save(report);
        Mockito.verify(reportDTOMapper).fromReport(report);
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


        Mockito.when(labWorkerService.findByHospitalIdentityNumber("1234567")).thenReturn(reportLabWorkerDTO);
        Mockito.when(reportRepository.findById(1L)).thenReturn(Optional.of(report));
        Mockito.when(reportMapper.fromDTO(reportDTO)).thenReturn(report);
        Mockito.when(reportRepository.save(report)).thenReturn(report);
        Mockito.when(reportDTOMapper.fromReport(report)).thenReturn(reportDTO);

        ReportDTO finalReportDTO = service.update(reportDTO, 1L);

        Assert.assertEquals(finalReportDTO, reportDTO);

        Mockito.verify(labWorkerService).findByHospitalIdentityNumber("1234567");
        Mockito.verify(reportRepository).findById(1L);
        Mockito.verify(reportMapper).fromDTO(reportDTO);
        Mockito.verify(reportRepository).save(report);
        Mockito.verify(reportDTOMapper, times(2)).fromReport(report);
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

        Mockito.when(reportDTOMapper.fromReport(report)).thenReturn(reportDTO);
        Mockito.when(reportRepository.findById(1L)).thenReturn(Optional.of(report));

        ReportDTO resultReport = service.findById(1L);

        Assert.assertEquals(resultReport, reportDTO);

        Mockito.verify(reportDTOMapper).fromReport(report);
        Mockito.verify(reportRepository).findById(1L);

    }

    @Test
    public void testFindByID_whenIdNotExists_shouldReturnThrowReportNotFoundException() {

        Mockito.when(reportRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(ReportNotFoundException.class,
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

        Mockito.when(reportRepository.findAll()).thenReturn(reports);
        Mockito.when(reportDTOMapper.fromReport(report)).thenReturn(reportDTO);

        List<ReportDTO> resultReports = service.findAll();

        Assert.assertEquals(resultReports.get(0), reportDTO);

        Mockito.verify(reportRepository).findAll();
        Mockito.verify(reportDTOMapper).fromReport(report);

    }
}