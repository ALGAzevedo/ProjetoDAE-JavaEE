package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators;

import java.time.LocalDate;

public class DocumentDTO {
    private Long id;
    private String filename;
    private String filepath;
    private LocalDate date;

    public DocumentDTO() {
    }

    public DocumentDTO(Long id, String filename, String filepath, LocalDate date) {
        this.id = id;
        this.filename = filename;
        this.filepath = filepath;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
