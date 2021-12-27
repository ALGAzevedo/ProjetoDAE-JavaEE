package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators;

import java.util.HashSet;
import java.util.List;

public class BiomedicalIndicatorGeneralResponseDTO {
    private long id;
    private String name;
    private String unity;
    private double min;
    private double max;
    private HashSet<String> possibleValues;
    private String type;

    public BiomedicalIndicatorGeneralResponseDTO(long id, String name, String unity, double min, double max, HashSet<String> possibleValues, String type) {
        this.id = id;
        this.name = name;
        this.unity = unity;
        this.min = min;
        this.max = max;
        this.possibleValues = possibleValues;
        this.type = type;
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

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public HashSet<String> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(HashSet<String> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
