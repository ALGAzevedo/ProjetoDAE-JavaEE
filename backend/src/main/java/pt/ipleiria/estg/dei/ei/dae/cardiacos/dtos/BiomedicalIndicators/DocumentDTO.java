package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators;

public class DocumentDTO {
    private Long id;
    private String filename;
    private String filepath;

    public DocumentDTO() {
    }

    public DocumentDTO(Long id, String filename, String filepath) {
        this.id = id;
        this.filename = filename;
        this.filepath = filepath;
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
