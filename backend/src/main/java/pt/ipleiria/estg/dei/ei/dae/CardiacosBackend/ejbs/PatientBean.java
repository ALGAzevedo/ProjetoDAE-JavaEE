package pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.ejbs;

import pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.entities.Patient;

import javax.ejb.Stateless;

@Stateless
public class PatientBean extends BaseBean<Patient, Long> {
    public PatientBean() {
        super(Patient.class);
    }



}
