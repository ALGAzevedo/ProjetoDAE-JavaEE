package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import javax.persistence.*;

@Entity
@NamedQuery(name = "getPatientDocuments",
        query = "SELECT doc FROM Document doc WHERE doc.patient.username = :username")
public class Document extends BaseEntity{
    private String filename;
    private String filepath;
    @ManyToOne
    private Patient patient;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Document() {
    }

    public Document(String filename, String filepath, Patient patient) {
        this.filename = filename;
        this.filepath = filepath;
        this.patient = patient;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
