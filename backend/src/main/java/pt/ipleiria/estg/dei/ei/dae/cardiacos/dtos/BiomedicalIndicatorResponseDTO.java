package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

public class BiomedicalIndicatorResponseDTO<T> implements DTO{
    private long id;
    private String name;
    private String unity;

    public BiomedicalIndicatorResponseDTO(long id, String name, String unity) {
        this.id = id;
        this.name = name;
        this.unity = unity;

    }

    public BiomedicalIndicatorResponseDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

}
