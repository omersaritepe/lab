package com.saritepe.lab.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Lob;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class ReportDTO {
    private Long id;
    private int fileNumber;
    private String patientFirstName;
    private String patientLastName;
    private String patientIdentityNumber;
    private String diagnosisTitle;
    private String diagnosisDetail;
    private String dateOfIssue;
    @Lob
    private byte[] image;

    private ReportLabWorkerDTO labWorker;

    public String getNbr(){

        return "Nbr";
    }


    public static final class ReportDTOBuilder {
        private Long id;
        private int fileNumber;
        private String patientFirstName;
        private String patientLastName;
        private String patientIdentityNumber;
        private String diagnosisTitle;
        private String diagnosisDetail;
        private String dateOfIssue;
        private byte[] image;
        private ReportLabWorkerDTO labWorker;

        private ReportDTOBuilder() {
        }

        public static ReportDTOBuilder aReportDTOWith() {
            return new ReportDTOBuilder();
        }

        public ReportDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ReportDTOBuilder fileNumber(int fileNumber) {
            this.fileNumber = fileNumber;
            return this;
        }

        public ReportDTOBuilder patientFirstName(String patientFirstName) {
            this.patientFirstName = patientFirstName;
            return this;
        }

        public ReportDTOBuilder patientLastName(String patientLastName) {
            this.patientLastName = patientLastName;
            return this;
        }

        public ReportDTOBuilder patientIdentityNumber(String patientIdentityNumber) {
            this.patientIdentityNumber = patientIdentityNumber;
            return this;
        }

        public ReportDTOBuilder diagnosisTitle(String diagnosisTitle) {
            this.diagnosisTitle = diagnosisTitle;
            return this;
        }

        public ReportDTOBuilder diagnosisDetail(String diagnosisDetail) {
            this.diagnosisDetail = diagnosisDetail;
            return this;
        }

        public ReportDTOBuilder dateOfIssue(String dateOfIssue) {
            this.dateOfIssue = dateOfIssue;
            return this;
        }

        public ReportDTOBuilder image(byte[] image) {
            this.image = image;
            return this;
        }

        public ReportDTOBuilder labWorker(ReportLabWorkerDTO labWorker) {
            this.labWorker = labWorker;
            return this;
        }

        public ReportDTO build() {
            ReportDTO reportDTO = new ReportDTO();
            reportDTO.patientIdentityNumber = this.patientIdentityNumber;
            reportDTO.image = this.image;
            reportDTO.patientLastName = this.patientLastName;
            reportDTO.fileNumber = this.fileNumber;
            reportDTO.id = this.id;
            reportDTO.diagnosisDetail = this.diagnosisDetail;
            reportDTO.patientFirstName = this.patientFirstName;
            reportDTO.dateOfIssue = this.dateOfIssue;
            reportDTO.diagnosisTitle = this.diagnosisTitle;
            reportDTO.labWorker = this.labWorker;
            return reportDTO;
        }
    }
}
