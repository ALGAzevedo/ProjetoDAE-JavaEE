package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import io.smallrye.common.constraint.NotNull;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorMeasure;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;

import javax.persistence.*;
import java.util.LinkedList;

public class BiomedicalIndicatorCreateDTO<T> implements DTO{
    private String name;
    private String unity;

    public BiomedicalIndicatorCreateDTO(String name, String unity) {
        this.name = name;
        this.unity = unity;

    }

    public BiomedicalIndicatorCreateDTO() {
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
