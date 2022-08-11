package com.saritepe.lab.model.dto;

import lombok.Data;

import java.util.Set;

@Data
public class LabWorkerDTO {
    private Long id;
    private String hospitalIdentityNumber;
    private String firstName;
    private String lastName;
    private Set<LabWorkerReportDTO> reports;

    public static final class LabWorkerDTOBuilder {
        private Long id;
        private String hospitalIdentityNumber;
        private String firstName;
        private String lastName;
        private Set<LabWorkerReportDTO> reports;

        private LabWorkerDTOBuilder() {
        }

        public static LabWorkerDTOBuilder aLabWorkerDTOWith() {
            return new LabWorkerDTOBuilder();
        }

        public LabWorkerDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LabWorkerDTOBuilder hospitalIdentityNumber(String hospitalIdentityNumber) {
            this.hospitalIdentityNumber = hospitalIdentityNumber;
            return this;
        }

        public LabWorkerDTOBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public LabWorkerDTOBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public LabWorkerDTOBuilder reports(Set<LabWorkerReportDTO> reports) {
            this.reports = reports;
            return this;
        }

        public LabWorkerDTO build() {
            LabWorkerDTO labWorkerDTO = new LabWorkerDTO();
            labWorkerDTO.reports = this.reports;
            labWorkerDTO.hospitalIdentityNumber = this.hospitalIdentityNumber;
            labWorkerDTO.firstName = this.firstName;
            labWorkerDTO.lastName = this.lastName;
            labWorkerDTO.id = this.id;
            return labWorkerDTO;
        }
    }
}
