package com.saritepe.lab.controller;

import com.saritepe.lab.model.dto.ReportDTO;
import com.saritepe.lab.service.ReportService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reports")
    public String findAll(Model model) {
        return findPaginated(1, 5, "patientFirstName", "asc", "", model);
    }


    /** Save */
    @GetMapping("/reports/save")
    public String save(Model model) {
        model.addAttribute("reportDTO", new ReportDTO());
        return "saveReport";
    }

    @PostMapping("/reports/s")
    public String saveReport(@ModelAttribute("reportDTO") ReportDTO reportDTO) {
        reportService.save(reportDTO);
        return "redirect:/reports";
    }

    /** Update */
    @GetMapping("/reports/update/{id}")
    public String updateReportForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("reportDTO", reportService.findById(id));
        return "updateReport";
    }

    @PostMapping("/reports/{id}")
    public String updateReport(@ModelAttribute("reportDTO") ReportDTO reportDTO, @PathVariable("id") Long id) {
        reportService.update(reportDTO, id);
        return "redirect:/reports";
    }

    /** Delete */
    @GetMapping("/reports/delete/{id}")
    public String deleteReport(@PathVariable("id") Long id) {
        reportService.deleteById(id);
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