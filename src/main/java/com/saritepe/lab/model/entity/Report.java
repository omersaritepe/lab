package com.saritepe.lab.model.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Column(nullable = false, unique = true)
    private int fileNumber;
    private String patientFirstName;
    private String patientLastName;
    private String patientIdentityNumber;
    private String diagnosisTitle;
    private String diagnosisDetail;

    private String dateOfIssue;

    private String image;

    @ManyToOne
    @JoinColumn(name = "lab_worker_id")
    private LabWorker labWorker;


    public static final class ReportBuilder {
        private Long id;
        private @NotEmpty int fileNumber;
        private String patientFirstName;
        private String patientLastName;
        private String patientIdentityNumber;
        private String diagnosisTitle;
        private String diagnosisDetail;
        private String dateOfIssue;
        private String image;
        private LabWorker labWorker;

        private ReportBuilder() {
        }

        public static ReportBuilder aReportWith() {
            return new ReportBuilder();
        }

        public ReportBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public ReportBuilder fileNumber(int fileNumber) {
            this.fileNumber = fileNumber;
            return this;
        }

        public ReportBuilder patientFirstName(String patientFirstName) {
            this.patientFirstName = patientFirstName;
            return this;
        }

        public ReportBuilder patientLastName(String patientLastName) {
            this.patientLastName = patientLastName;
            return this;
        }

        public ReportBuilder patientIdentityNumber(String patientIdentityNumber) {
            this.patientIdentityNumber = patientIdentityNumber;
            return this;
        }

        public ReportBuilder diagnosisTitle(String diagnosisTitle) {
            this.diagnosisTitle = diagnosisTitle;
            return this;
        }

        public ReportBuilder diagnosisDetail(String diagnosisDetail) {
            this.diagnosisDetail = diagnosisDetail;
            return this;
        }

        public ReportBuilder dateOfIssue(String dateOfIssue) {
            this.dateOfIssue = dateOfIssue;
            return this;
        }

        public ReportBuilder image(String image) {
            this.image = image;
            return this;
        }

        public ReportBuilder labWorker(LabWorker labWorker) {
            this.labWorker = labWorker;
            return this;
        }

        public Report build() {
            return new Report(id, fileNumber, patientFirstName, patientLastName, patientIdentityNumber, diagnosisTitle, diagnosisDetail, dateOfIssue, image, labWorker);
        }
    }
}
