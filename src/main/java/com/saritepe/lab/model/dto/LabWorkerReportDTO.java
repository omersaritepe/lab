package com.saritepe.lab.model.dto;

import lombok.Data;
import lombok.Getter;

import javax.persistence.Transient;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LabWorkerReportDTO {

    private Long id;
    private int fileNumber;
    private String patientFirstName;
    private String patientLastName;
    private String patientIdentityNumber;
    private String diagnosisTitle;
    private String diagnosisDetail;
    private String dateOfIssue;
    private String image;

    @Transient
    public  String getReportImagePath() {
        if (image == null || id == null) return null;
        System.out.println("/report-images/" + id + "/" + image);
        return "/report-images/" + id + "/" + image;
    }


    public static final class LabWorkerReportDTOBuilder {
        private Long id;
        private int fileNumber;
        private String patientFirstName;
        private String patientLastName;
        private String patientIdentityNumber;
        private String diagnosisTitle;
        private String diagnosisDetail;
        private String dateOfIssue;
        private String image;

        private LabWorkerReportDTOBuilder() {
        }

        public static LabWorkerReportDTOBuilder aLabWorkerReportDTOWith() {
            return new LabWorkerReportDTOBuilder();
        }

        public LabWorkerReportDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LabWorkerReportDTOBuilder fileNumber(int fileNumber) {
            this.fileNumber = fileNumber;
            return this;
        }

        public LabWorkerReportDTOBuilder patientFirstName(String patientFirstName) {
            this.patientFirstName = patientFirstName;
            return this;
        }

        public LabWorkerReportDTOBuilder patientLastName(String patientLastName) {
            this.patientLastName = patientLastName;
            return this;
        }

        public LabWorkerReportDTOBuilder patientIdentityNumber(String patientIdentityNumber) {
            this.patientIdentityNumber = patientIdentityNumber;
            return this;
        }

        public LabWorkerReportDTOBuilder diagnosisTitle(String diagnosisTitle) {
            this.diagnosisTitle = diagnosisTitle;
            return this;
        }

        public LabWorkerReportDTOBuilder diagnosisDetail(String diagnosisDetail) {
            this.diagnosisDetail = diagnosisDetail;
            return this;
        }

        public LabWorkerReportDTOBuilder dateOfIssue(String dateOfIssue) {
            this.dateOfIssue = dateOfIssue;
            return this;
        }

        public LabWorkerReportDTOBuilder image(String image) {
            this.image = image;
            return this;
        }

        public LabWorkerReportDTO build() {
            LabWorkerReportDTO labWorkerReportDTO = new LabWorkerReportDTO();
            labWorkerReportDTO.image = this.image;
            labWorkerReportDTO.fileNumber = this.fileNumber;
            labWorkerReportDTO.patientLastName = this.patientLastName;
            labWorkerReportDTO.id = this.id;
            labWorkerReportDTO.dateOfIssue = this.dateOfIssue;
            labWorkerReportDTO.patientFirstName = this.patientFirstName;
            labWorkerReportDTO.diagnosisTitle = this.diagnosisTitle;
            labWorkerReportDTO.patientIdentityNumber = this.patientIdentityNumber;
            labWorkerReportDTO.diagnosisDetail = this.diagnosisDetail;
            return labWorkerReportDTO;
        }
    }
}
