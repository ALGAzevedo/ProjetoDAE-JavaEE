package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorMeasure;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;

import java.util.LinkedList;

public class BiomedicalIndicatorResponseDTO<T> implements DTO{
    private String name;
    private String unity;
    private LinkedList<BiomedicalIndicatorMeasure<T>> values;
    private Patient patient;

    public BiomedicalIndicatorResponseDTO(String name, String unity, LinkedList<BiomedicalIndicatorMeasure<T>> values, Patient patient) {
        this.name = name;
        this.unity = unity;
        this.values = values;
        this.patient = patient;
    }

    public BiomedicalIndicatorResponseDTO() {
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

    public LinkedList<BiomedicalIndicatorMeasure<T>> getValues() {
        return values;
    }

    public void setValues(LinkedList<BiomedicalIndicatorMeasure<T>> values) {
        this.values = values;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
