package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import javax.persistence.*;
import java.util.*;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllBiomedicalIndicatorsQualitatives",
                query = "SELECT s FROM BiomedicalIndicatorsQualitative s ORDER BY s.name" // JPQL
        )
})
public class BiomedicalIndicatorsQualitative extends BiomedicalIndicator<String>{
    @CollectionTable(name="QualitativePossibleValues")
    @ElementCollection(fetch = FetchType.LAZY)
    private Set<String> possibleValues;

    public BiomedicalIndicatorsQualitative()  {
        this.possibleValues = new HashSet<>();
    }

    public BiomedicalIndicatorsQualitative(String name) {
        super(name);
        this.possibleValues = new HashSet<>();
    }

    public BiomedicalIndicatorsQualitative(String name, String unity) {
        super(name, unity);
        this.possibleValues = new HashSet<>();;
    }
    public BiomedicalIndicatorsQualitative(String name, String unity, HashSet<String> values) {
        super(name, unity);
        this.possibleValues = values;
    }
    public BiomedicalIndicatorsQualitative(String name,HashSet<String> values) {
        super(name);
        this.possibleValues = values;
    }

    public HashSet<String> getPossibleValues() {
        return new HashSet<>(possibleValues);
    }

    public void setPossibleValues(HashSet<String> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public void addNewValue(String value) {
        if (!value.equals(""))
            this.possibleValues.add(value.toUpperCase());
    }
    public void removeVal(String value)
    {
        this.possibleValues.remove(value);
    }

    //TODO: ESTES METODOS DEVEM ESTAR AQUI OU NO BEAN?
    public boolean containsValue(String value) {
        return possibleValues.contains(value.toUpperCase());
    }

    public boolean isValid(String value) {
        return containsValue(value);
    }
}
