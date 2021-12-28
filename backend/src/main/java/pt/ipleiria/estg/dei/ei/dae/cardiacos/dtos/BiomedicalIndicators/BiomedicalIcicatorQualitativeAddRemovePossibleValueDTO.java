package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators;

public class BiomedicalIcicatorQualitativeAddRemovePossibleValueDTO {
    private long indicatorID;
    private String newValue;

    public BiomedicalIcicatorQualitativeAddRemovePossibleValueDTO(long indicatorID, String newValue) {
        this.indicatorID = indicatorID;
        this.newValue = newValue;
    }

    public long getIndicatorID() {
        return indicatorID;
    }

    public void setIndicatorID(long indicatorID) {
        this.indicatorID = indicatorID;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }
}
