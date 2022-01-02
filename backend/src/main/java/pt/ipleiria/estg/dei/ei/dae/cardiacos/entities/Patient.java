package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import io.smallrye.common.constraint.Nullable;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllPatients",
                query = "SELECT s FROM Patient s ORDER BY s.name" // JPQL
        ),
        @NamedQuery(
                name = "getBiomedicalRegisters",
                query = "SELECT s.biomedicalRegisters FROM Patient s WHERE s.username = :user" // JPQL
        )
})
public class Patient extends User{

        @Nullable
        @OneToMany(mappedBy = "patient", cascade = CascadeType.DETACH)
        private List<PRC> prcList;

        @OneToMany(mappedBy = "patient", cascade = CascadeType.PERSIST)
        private List<PatientBiomedicalIndicator<?>> biomedicalRegisters;

        @OneToMany(mappedBy = "patient", cascade = CascadeType.REMOVE)
        private List<Document> documents;


        public Patient() {
                this.prcList = new LinkedList<PRC>();
        }

        public Patient(String name, String username, String email, Gender gender, LocalDate birthDate,
                       Country country, String socialSecurityNumber, MaritalStatus maritalStatus,
                       String address, String city, String postalCode, String phoneNumber, String emergencyPhoneNumber) {
                super(name, username, email, gender, birthDate, country, socialSecurityNumber, maritalStatus, address, city, postalCode, phoneNumber, emergencyPhoneNumber);
                this.prcList = new LinkedList<PRC>();
                this.biomedicalRegisters = new LinkedList<>();
        }
        public Patient(String name, String username, String email, Gender gender, LocalDate birthDate,
                       Country country, String socialSecurityNumber, MaritalStatus maritalStatus,
                       String address, String city, String postalCode, String phoneNumber, String emergencyPhoneNumber,
                       List<PRC> prcList) {
                super(name, username, email, gender, birthDate, country, socialSecurityNumber, maritalStatus, address, city, postalCode, phoneNumber, emergencyPhoneNumber);
                this.prcList = prcList;
                this.biomedicalRegisters = new LinkedList<>();
        }
        public List<Document> getDocuments() {
                return documents;
        }

        public void setDocuments(List<Document> documents) {
                this.documents = documents;
        }

        public List<PRC> getPrcList() {
                return prcList;
        }

        public void setPrcList(List<PRC> prcList) {
                this.prcList = prcList;
        }

        public void addPrc(PRC prc) {
                if(!this.prcList.contains(prc)) {
                        this.prcList.add(prc);
                }
        }

        public void addQuantitativeBiomedicalIndicator(BiomedicalIndicatorsQuantitative indicator, double value, LocalDateTime date, String description) {
                biomedicalRegisters.add(new PatientBiomedicalIndicator<Double>(value, date, this, indicator, description));


        }

        public void addQualitativeBiomedicalIndicator(BiomedicalIndicatorsQualitative indicator, String value, LocalDateTime date, String description) {

                biomedicalRegisters.add(new PatientBiomedicalIndicator<String>(value, date, this, indicator, description));

        }

        public void removeBiomedicalIndicator(PatientBiomedicalIndicator indicator) {
                //returns always false !!! getID always returns null
                System.out.println(this.biomedicalRegisters.contains(indicator));
                this.biomedicalRegisters.remove(indicator);

        }

        public void addDocument(Document doc) {
                if(!documents.contains(doc)) {
                        documents.add(doc);
                }
        }

        public void removeDocument(Document doc) {
                documents.remove(doc);
        }
}
