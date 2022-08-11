package com.saritepe.lab.service;

import com.saritepe.lab.mapper.dto.LabWorkerDTOMapper;
import com.saritepe.lab.mapper.dto.ReportLabWorkerDTOMapper;
import com.saritepe.lab.mapper.entity.LabWorkerMapper;
import com.saritepe.lab.model.dto.LabWorkerDTO;
import com.saritepe.lab.model.dto.ReportDTO;
import com.saritepe.lab.model.dto.ReportLabWorkerDTO;
import com.saritepe.lab.model.entity.LabWorker;
import com.saritepe.lab.model.entity.Report;
import com.saritepe.lab.model.exception.LabWorkerNotFoundException;
import com.saritepe.lab.repository.LabWorkerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class LabWorkerServiceTest {

    private LabWorkerRepository labWorkerRepository;

    private LabWorkerService labWorkerService;
    private ReportService reportService;

    private LabWorkerMapper labWorkerMapper;
    private LabWorkerDTOMapper labWorkerDTOMapper;
    private ReportLabWorkerDTOMapper reportLabWorkerDTOMapper;

    @BeforeEach
    void setUp() {
        labWorkerRepository = mock(LabWorkerRepository.class);
        reportService = mock(ReportService.class);
        labWorkerMapper = mock(LabWorkerMapper.class);
        labWorkerDTOMapper = mock(LabWorkerDTOMapper.class);
        reportLabWorkerDTOMapper = mock(ReportLabWorkerDTOMapper.class);
        labWorkerService = new LabWorkerService(
                labWorkerRepository,
                reportService,
                labWorkerMapper,
                labWorkerDTOMapper,
                reportLabWorkerDTOMapper);
    }

    @Test
    public void testSave_whenReportLabWorkerDTOExists_shouldReturnReportLabWorkerDTO() {
        LabWorker labWorker = LabWorker.LabWorkerBuilder.aLabWorkerWith()
                .id(1L)
                .firstName("first")
                .lastName("last")
                .hospitalIdentityNumber("1234567")
                .build();

        ReportLabWorkerDTO reportLabWorkerDTO = ReportLabWorkerDTO.ReportLabWorkerDTOBuilder.aReportLabWorkerDTOWith()
                .id(1L)
                .firstName("first")
                .lastName("last")
                .hospitalIdentityNumber("1234567")
                .build();

        Mockito.when(labWorkerMapper.fromReportLabWorkerDTO(reportLabWorkerDTO)).thenReturn(labWorker);
        Mockito.when(labWorkerRepository.save(labWorker)).thenReturn(labWorker);
        Mockito.when(reportLabWorkerDTOMapper.fromLabWorker(labWorker)).thenReturn(reportLabWorkerDTO);

        ReportLabWorkerDTO resultReportLabWorkerDTO = labWorkerService.save(reportLabWorkerDTO);

        Assert.assertEquals(resultReportLabWorkerDTO, reportLabWorkerDTO);

        Mockito.verify(labWorkerMapper).fromReportLabWorkerDTO(reportLabWorkerDTO);
        Mockito.verify(labWorkerRepository).save(labWorker);
        Mockito.verify(reportLabWorkerDTOMapper).fromLabWorker(labWorker);
    }

    @Test
    public void testUpdate_whenReportLabWorkerDTOAndIdExists_shouldReturnReportLabWorkerDTO() {
        LabWorker labWorker = LabWorker.LabWorkerBuilder.aLabWorkerWith()
                .id(1L)
                .firstName("first")
                .lastName("last")
                .hospitalIdentityNumber("1234567")
                .build();

        ReportLabWorkerDTO reportLabWorkerDTO = ReportLabWorkerDTO.ReportLabWorkerDTOBuilder.aReportLabWorkerDTOWith()
                .id(1L)
                .firstName("first")
                .lastName("last")
                .hospitalIdentityNumber("1234567")
                .build();

        Mockito.when(labWorkerMapper.fromReportLabWorkerDTO(reportLabWorkerDTO)).thenReturn(labWorker);
        Mockito.when(labWorkerRepository.save(labWorker)).thenReturn(labWorker);
        Mockito.when(reportLabWorkerDTOMapper.fromLabWorker(labWorker)).thenReturn(reportLabWorkerDTO);

        ReportLabWorkerDTO resultReportLabWorkerDTO = labWorkerService.update(reportLabWorkerDTO, 1L);

        Assert.assertEquals(resultReportLabWorkerDTO, reportLabWorkerDTO);

        Mockito.verify(labWorkerMapper).fromReportLabWorkerDTO(reportLabWorkerDTO);
        Mockito.verify(labWorkerRepository).save(labWorker);
        Mockito.verify(reportLabWorkerDTOMapper).fromLabWorker(labWorker);
    }

    @Test
    public void testFindById_IdExists_shouldReturnLabWorkerDTO() {
        LabWorker labWorker = LabWorker.LabWorkerBuilder.aLabWorkerWith()
                .id(1L)
                .firstName("first")
                .lastName("last")
                .hospitalIdentityNumber("1234567")
                .build();

        LabWorkerDTO labWorkerDTO = LabWorkerDTO.LabWorkerDTOBuilder.aLabWorkerDTOWith()
                .id(1L)
                .firstName("first")
                .lastName("last")
                .hospitalIdentityNumber("1234567")
                .build();

        ReportLabWorkerDTO reportLabWorkerDTO = ReportLabWorkerDTO.ReportLabWorkerDTOBuilder.aReportLabWorkerDTOWith()
                .id(1L)
                .firstName("first")
                .lastName("last")
                .hospitalIdentityNumber("1234567")
                .build();

        Mockito.when(labWorkerDTOMapper.fromLabWorker(labWorker)).thenReturn(labWorkerDTO);
        Mockito.when(labWorkerRepository.findById(1L)).thenReturn(Optional.of(labWorker));

        LabWorkerDTO resultRabWorkerDTO = labWorkerService.findById(1L);

        Assert.assertEquals(resultRabWorkerDTO, labWorkerDTO);

        Mockito.verify(labWorkerDTOMapper).fromLabWorker(labWorker);
        Mockito.verify(labWorkerRepository).findById(1L);
    }

    @Test
    public void testFindById_IdNotExists_shouldReturnThrowLabWorkerNotFoundException() {

        Mockito.when(labWorkerRepository.findById(1L)).thenReturn(Optional.empty());

        Assertions.assertThrows(LabWorkerNotFoundException.class,
                () -> labWorkerService.findById(1L));
    }

    @Test
    public void testFindAll_shouldReturnReportDTOList() {

        LabWorker labWorker = LabWorker.LabWorkerBuilder.aLabWorkerWith()
                .id(1L)
                .firstName("first")
                .lastName("last")
                .hospitalIdentityNumber("1234567")
                .build();

        LabWorkerDTO labWorkerDTO = LabWorkerDTO.LabWorkerDTOBuilder.aLabWorkerDTOWith()
                .id(1L)
                .firstName("first")
                .lastName("last")
                .hospitalIdentityNumber("1234567")
                .build();

        ArrayList<LabWorker> labWorkers = new ArrayList<>();
        labWorkers.add(labWorker);

        Mockito.when(labWorkerRepository.findAll()).thenReturn(labWorkers);
        Mockito.when(labWorkerDTOMapper.fromLabWorker(labWorker)).thenReturn(labWorkerDTO);

        List<LabWorkerDTO> resultLabWorkers = labWorkerService.findAll();

        Assert.assertEquals(resultLabWorkers.get(0), labWorkerDTO);

        Mockito.verify(labWorkerRepository).findAll();
        Mockito.verify(labWorkerDTOMapper).fromLabWorker(labWorker);
    }

    @Test
    public void testFindByHospitalIdentityNumber_hospitalIDExists_shouldReturnReportLabWorkerDTO() {
        LabWorker labWorker = LabWorker.LabWorkerBuilder.aLabWorkerWith()
                .id(1L)
                .firstName("first")
                .lastName("last")
                .hospitalIdentityNumber("1234567")
                .build();

        ReportLabWorkerDTO reportLabWorkerDTO = ReportLabWorkerDTO.ReportLabWorkerDTOBuilder.aReportLabWorkerDTOWith()
                .id(1L)
                .firstName("first")
                .lastName("last")
                .hospitalIdentityNumber("1234567")
                .build();

        when(reportLabWorkerDTOMapper.fromLabWorker(labWorker)).thenReturn(reportLabWorkerDTO);
        when(labWorkerRepository.findByHospitalIdentityNumber("1234567")).thenReturn(labWorker);

        ReportLabWorkerDTO resultReportLabWorkerDTO = labWorkerService.findByHospitalIdentityNumber("1234567");

        assertEquals(resultReportLabWorkerDTO, reportLabWorkerDTO);

        verify(reportLabWorkerDTOMapper).fromLabWorker(labWorker);
        verify(labWorkerRepository).findByHospitalIdentityNumber("1234567");
    }
}