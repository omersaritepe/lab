package com.saritepe.lab.model.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class ReportLabWorkerDTO {
    private Long id;
    @NotEmpty(message = "Lab Worker hospital ID cannot be empty.")
    @Length(min = 7, max = 7, message = "Lab Worker's hospital ID length must be 7 characters.")
    private String hospitalIdentityNumber;
    @NotEmpty(message = "Lab Worker name cannot be empty.")
    private String firstName;
    @NotEmpty(message = "Lab Worker last name cannot be empty.")
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
