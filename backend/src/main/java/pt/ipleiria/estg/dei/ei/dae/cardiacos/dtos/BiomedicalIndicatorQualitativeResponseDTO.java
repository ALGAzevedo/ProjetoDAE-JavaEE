package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import java.util.HashSet;
import java.util.Set;

public class BiomedicalIndicatorQualitativeResponseDTO extends BiomedicalIndicatorResponseDTO<String> {
    private Set<String> possibleValues;

    public BiomedicalIndicatorQualitativeResponseDTO(long id, String name, String unity, Set<String> possibleValues) {
        super(id, name, unity);

        this.possibleValues = possibleValues == null ? new HashSet<>() : possibleValues;
    }

    public BiomedicalIndicatorQualitativeResponseDTO() {
        this.possibleValues = new HashSet<>();
    }

    public Set<String> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(Set<String> possibleValues) {
        this.possibleValues = possibleValues;
    }
}
