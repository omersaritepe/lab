package com.saritepe.lab.controller;

import com.saritepe.lab.mapper.dto.ReportLabWorkerDTOMapper;
import com.saritepe.lab.model.dto.LabWorkerDTO;
import com.saritepe.lab.model.dto.ReportLabWorkerDTO;
import com.saritepe.lab.service.LabWorkerService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class LabWorkerController {

    private final LabWorkerService labWorkerService;
    private final ReportLabWorkerDTOMapper reportLabWorkerDTOMapper;

    public LabWorkerController(LabWorkerService labWorkerService, ReportLabWorkerDTOMapper reportLabWorkerDTOMapper) {
        this.labWorkerService = labWorkerService;
        this.reportLabWorkerDTOMapper = reportLabWorkerDTOMapper;
    }

    @GetMapping("/labWorkers")
    public String findAll(Model model) {
        return findPaginated(1, 5, "hospitalIdentityNumber", "asc" , model);
    }

    /** Save */
    @GetMapping("/labWorkers/save")
    public String save(Model model) {

        ReportLabWorkerDTO reportLabWorkerDTO = new ReportLabWorkerDTO();
        model.addAttribute("reportLabWorkerDTO", reportLabWorkerDTO);
        return "saveLabWorker";
    }

    @PostMapping("/labWorkers")
    public String saveLabWorker(@Valid @ModelAttribute("reportLabWorkerDTO") ReportLabWorkerDTO reportLabWorkerDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "saveLabWorker";
        }
        labWorkerService.save(reportLabWorkerDTO);
        return "redirect:/labWorkers";
    }


    /** Update */
    @GetMapping("/labWorkers/update/{id}")
    public String update(Model model, @PathVariable("id") Long id) {

        ReportLabWorkerDTO reportLabWorkerDTO = reportLabWorkerDTOMapper.fromLabWorkerDTO(labWorkerService.findById(id));
        model.addAttribute("reportLabWorkerDTO", reportLabWorkerDTO);
        return "updateLabWorker";
    }

    @PostMapping("/labWorkers/{id}")
    public String saveLabWorker(@PathVariable("id") Long id,
                                @Valid @ModelAttribute("reportLabWorkerDTO") ReportLabWorkerDTO reportLabWorkerDTO,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "updateLabWorker";
        }
        labWorkerService.update(reportLabWorkerDTO, id);
        return "redirect:/labWorkers";
    }

    /** Delete */
    @GetMapping("/labWorkers/delete/{id}")
    public String delete(Model model, @PathVariable("id") Long id) {

        labWorkerService.deleteById(id);
        return "redirect:/labWorkers";
    }

    /** INFO */
    @GetMapping("/labWorkers/get/{id}")
    public String info(Model model, @PathVariable("id") Long id) {

        LabWorkerDTO labWorkerDTO = labWorkerService.findById(id);
        model.addAttribute("labWorkerDTO", labWorkerDTO);
        return "infoLabWorker";
    }

    /** Page */
    @GetMapping("/labWorkers/page/{pageNo}/{pageSize}")
    public String findPaginated(@PathVariable("pageNo") int pageNo
            ,@PathVariable("pageSize") int pageSize
            ,@RequestParam("sortField") String sortField
            ,@RequestParam("sortDirection") String sortDirection
            ,Model model) {

        Page<LabWorkerDTO> page = labWorkerService.findPaginated(pageNo, pageSize, sortField, sortDirection);
        List<LabWorkerDTO> labWorkerDTOS = page.getContent();

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalElements", page.getTotalElements());
        model.addAttribute("listLabWorkers", labWorkerDTOS);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "asc" : "dsc");

        return "labWorkers";
    }

}
