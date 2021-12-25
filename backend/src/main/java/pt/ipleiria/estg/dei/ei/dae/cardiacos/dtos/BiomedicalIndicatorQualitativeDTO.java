package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorMeasure;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class BiomedicalIndicatorQualitativeDTO extends BiomedicalIndicatorCreateDTO<String> {
    private Set<String> possibleValues;

    public BiomedicalIndicatorQualitativeDTO(String name, String unity, Set<String> possibleValues) {
        super(name, unity);

        this.possibleValues = possibleValues == null ? new HashSet<>() : possibleValues;
    }

    public BiomedicalIndicatorQualitativeDTO() {
        this.possibleValues = new HashSet<>();
    }

    public Set<String> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(Set<String> possibleValues) {
        this.possibleValues = possibleValues;
    }
}
