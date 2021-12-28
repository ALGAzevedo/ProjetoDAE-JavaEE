package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators;

import java.util.HashSet;
import java.util.Set;

public class BiomedicalIndicatorQualitativeCreateDTO extends BiomedicalIndicatorCreateDTO<String> {
    private Set<String> possibleValues;

    public BiomedicalIndicatorQualitativeCreateDTO(String name, String unity, Set<String> possibleValues) {
        super(name, unity);

        this.possibleValues = possibleValues == null ? new HashSet<>() : possibleValues;
    }

    public BiomedicalIndicatorQualitativeCreateDTO() {
        this.possibleValues = new HashSet<>();
    }

    public Set<String> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(Set<String> possibleValues) {
        this.possibleValues = possibleValues;
    }
}
