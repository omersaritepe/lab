package com.saritepe.lab.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LabWorker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 7)
    @NotEmpty(message = "Lab Worker's name cannot be empty.")
    private String hospitalIdentityNumber;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "labWorker")
    private Set<Report> reports;

    public static final class LabWorkerBuilder {
        private Long id;

        private String hospitalIdentityNumber;
        private String firstName;
        private String lastName;
        private Set<Report> reports;

        private LabWorkerBuilder() {
        }

        public static LabWorkerBuilder aLabWorkerWith() {
            return new LabWorkerBuilder();
        }

        public LabWorkerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public LabWorkerBuilder hospitalIdentityNumber(String hospitalIdentityNumber) {
            this.hospitalIdentityNumber = hospitalIdentityNumber;
            return this;
        }

        public LabWorkerBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public LabWorkerBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public LabWorkerBuilder reports(Set<Report> reports) {
            this.reports = reports;
            return this;
        }

        public LabWorker build() {
            return new LabWorker(id, hospitalIdentityNumber, firstName, lastName, reports);
        }
    }
}
