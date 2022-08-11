package com.saritepe.lab.model.dto;

import lombok.Data;

import javax.persistence.Transient;

@Data
public class LabWorkerReportDTO {

    private Long id;
    private String fileNumber;
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
        return "/report-images/" + id + "/" + image;
    }


    public static final class LabWorkerReportDTOBuilder {
        private Long id;
        private String fileNumber;
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

        public LabWorkerReportDTOBuilder fileNumber(String fileNumber) {
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
            labWorkerReportDTO.setId(id);
            labWorkerReportDTO.setFileNumber(fileNumber);
            labWorkerReportDTO.setPatientFirstName(patientFirstName);
            labWorkerReportDTO.setPatientLastName(patientLastName);
            labWorkerReportDTO.setPatientIdentityNumber(patientIdentityNumber);
            labWorkerReportDTO.setDiagnosisTitle(diagnosisTitle);
            labWorkerReportDTO.setDiagnosisDetail(diagnosisDetail);
            labWorkerReportDTO.setDateOfIssue(dateOfIssue);
            labWorkerReportDTO.setImage(image);
            return labWorkerReportDTO;
        }
    }
}
