package com.saritepe.lab.model.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class LabWorkerReportDTO {

    private Long id;
    private int fileNumber;
    private String patientFirstName;
    private String patientLastName;
    private String patientIdentityNumber;
    private String diagnosisTitle;
    private String diagnosisDetail;
    private String dateOfIssue;
    private byte[] image;


    public static final class LabWorkerReportDTOBuilder {
        private Long id;
        private int fileNumber;
        private String patientFirstName;
        private String patientLastName;
        private String patientIdentityNumber;
        private String diagnosisTitle;
        private String diagnosisDetail;
        private String dateOfIssue;
        private byte[] image;

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

        public LabWorkerReportDTOBuilder image(byte[] image) {
            this.image = image;
            return this;
        }

        public LabWorkerReportDTO build() {
            LabWorkerReportDTO labWorkerReportDTO = new LabWorkerReportDTO();
            labWorkerReportDTO.fileNumber = this.fileNumber;
            labWorkerReportDTO.image = this.image;
            labWorkerReportDTO.diagnosisDetail = this.diagnosisDetail;
            labWorkerReportDTO.patientLastName = this.patientLastName;
            labWorkerReportDTO.id = this.id;
            labWorkerReportDTO.patientFirstName = this.patientFirstName;
            labWorkerReportDTO.patientIdentityNumber = this.patientIdentityNumber;
            labWorkerReportDTO.dateOfIssue = this.dateOfIssue;
            labWorkerReportDTO.diagnosisTitle = this.diagnosisTitle;
            return labWorkerReportDTO;
        }
    }
}
