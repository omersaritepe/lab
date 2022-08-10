package com.saritepe.lab.controller;

import com.saritepe.lab.model.dto.ReportDTO;
import com.saritepe.lab.service.ReportService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reports")
    public String findAll(Model model) {
        return findPaginated(1, 5, "fileNumber", "asc", "", model);
    }


    /** Save */
    @GetMapping("/reports/save")
    public String save(Model model) {
        model.addAttribute("reportDTO", new ReportDTO());
        return "saveReport";
    }

    @PostMapping("/reports/s")
    public String saveReport(@Valid @ModelAttribute("reportDTO") ReportDTO reportDTO,
                             BindingResult result,
                             @RequestParam("file") MultipartFile file) throws IOException {
        if (result.hasErrors()) {
            return "saveReport";
        }

        if (file.isEmpty()) {
            reportService.save(reportDTO);
            return "redirect:/reports";
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        reportDTO = ReportDTO.ReportDTOBuilder.aReportDTOWith()
                .id(reportDTO.getId())
                .fileNumber(reportDTO.getFileNumber())
                .patientFirstName(reportDTO.getPatientFirstName())
                .patientLastName(reportDTO.getPatientLastName())
                .patientIdentityNumber(reportDTO.getPatientIdentityNumber())
                .diagnosisTitle(reportDTO.getDiagnosisTitle())
                .diagnosisDetail(reportDTO.getDiagnosisDetail())
                .dateOfIssue(reportDTO.getDateOfIssue())
                .image(fileName)
                .labWorker(reportDTO.getLabWorker())
                .build();

        ReportDTO savedReportDTO = reportService.save(reportDTO);

        String uploadDir = "./report-images/" + savedReportDTO.getId();
        // String uploadDir = "./src/main/resources/static/report-images/" + savedReportDTO.getId();

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not saved uploaded file: " + fileName);
        }


        return "redirect:/reports";
    }

    /** Update */
    @GetMapping("/reports/update/{id}")
    public String updateReportForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("reportDTO", reportService.findById(id));
        return "updateReport";
    }

    @PostMapping("/reports/{id}")
    public String updateReport(@Valid @ModelAttribute("reportDTO") ReportDTO reportDTO,
                               @PathVariable("id") Long id,
                               BindingResult result,
                               @RequestParam("file") MultipartFile file) throws IOException {
        if (result.hasErrors()) {
            return "updateReport";
        }
        if (file.isEmpty()) {
            reportService.update(reportDTO, id);
            return "redirect:/reports";
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        reportDTO = ReportDTO.ReportDTOBuilder.aReportDTOWith()
                .id(reportDTO.getId())
                .fileNumber(reportDTO.getFileNumber())
                .patientFirstName(reportDTO.getPatientFirstName())
                .patientLastName(reportDTO.getPatientLastName())
                .patientIdentityNumber(reportDTO.getPatientIdentityNumber())
                .diagnosisTitle(reportDTO.getDiagnosisTitle())
                .diagnosisDetail(reportDTO.getDiagnosisDetail())
                .dateOfIssue(reportDTO.getDateOfIssue())
                .image(fileName)
                .labWorker(reportDTO.getLabWorker())
                .build();

        ReportDTO savedReportDTO = reportService.save(reportDTO);

        String uploadDir = "./report-images/" + savedReportDTO.getId();
        // String uploadDir = "./src/main/resources/static/report-images/" + savedReportDTO.getId();

        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()){
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not saved uploaded file: " + fileName);
        }


        return "redirect:/reports";

    }

    /** Delete */
    @GetMapping("/reports/delete/{id}")
    public String deleteReport(@PathVariable("id") Long id) throws IOException {
        reportService.deleteById(id);
/*
        String deleteDir = "./report-images/" + id;
        Path deletePath = Paths.get(deleteDir);
        Files.delete(deletePath);
 */
        return "redirect:/reports";
    }

    /** INFO */
    @GetMapping("/reports/get/{id}")
    public String info(Model model, @PathVariable("id") Long id) {

        ReportDTO reportDTO = reportService.findById(id);
        model.addAttribute("reportDTO", reportDTO);
        return "infoReport";
    }

    @GetMapping("/reports/page/{pageNo}/{pageSize}")
    public String findPaginated(@PathVariable("pageNo") int pageNo
            ,@PathVariable("pageSize") int pageSize
            ,@RequestParam("sortField") String sortField
            ,@RequestParam("sortDirection") String sortDirection
            ,@RequestParam("keyword") String keyword
            ,Model model) {

        Page<ReportDTO> page = reportService.findPaginated(pageNo,  pageSize, sortField, sortDirection, keyword);
        List<ReportDTO> reportDTOS = page.getContent();

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("reportDTOS", reportDTOS);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "asc" : "desc");

        model.addAttribute("keyword", keyword);

        return "reports";
    }
}