package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators;

import java.util.HashSet;

public class BIomedicalIdicatorUpdateDTO extends BiomedicalIndicatorGeneralResponseDTO{
    private String newType;
    private long previous;

    public BIomedicalIdicatorUpdateDTO(long id, String name, String unity, double min, double max, HashSet<String> possibleValues, String indicatorType, String oldType) {
        super(id, name, unity, min, max, possibleValues, indicatorType);
        this.newType = oldType;
        this.previous = id;
    }

    public BIomedicalIdicatorUpdateDTO() {
        super();
    }

    public String getNewType() {
        return newType;
    }

    public void setNewType(String newType) {
        this.newType = newType;
    }

    public long getPrevious() {
        return previous;
    }

    public void setPrevious(long previous) {
        this.previous = previous;
    }
}
