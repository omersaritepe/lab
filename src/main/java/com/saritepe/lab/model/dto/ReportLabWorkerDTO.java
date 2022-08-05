package com.saritepe.lab.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportLabWorkerDTO {
    private Long id;
    private String hospitalIdentityNumber;
    private String firstName;
    private String lastName;


    public static final class ReportLabWorkerDTOBuilder {
        private Long id;
        private String hospitalIdentityNumber;
        private String firstName;
        private String lastName;

        private ReportLabWorkerDTOBuilder() {
        }

        public static ReportLabWorkerDTOBuilder aReportLabWorkerDTOWith() {
            return new ReportLabWorkerDTOBuilder();
        }

        public ReportLabWorkerDTOBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ReportLabWorkerDTOBuilder hospitalIdentityNumber(String hospitalIdentityNumber) {
            this.hospitalIdentityNumber = hospitalIdentityNumber;
            return this;
        }

        public ReportLabWorkerDTOBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public ReportLabWorkerDTOBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public ReportLabWorkerDTO build() {
            ReportLabWorkerDTO reportLabWorkerDTO = new ReportLabWorkerDTO();
            reportLabWorkerDTO.firstName = this.firstName;
            reportLabWorkerDTO.lastName = this.lastName;
            reportLabWorkerDTO.hospitalIdentityNumber = this.hospitalIdentityNumber;
            reportLabWorkerDTO.id = this.id;
            return reportLabWorkerDTO;
        }
    }
}
