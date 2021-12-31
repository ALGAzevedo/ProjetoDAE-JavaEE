package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import org.modelmapper.ValidationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.QualitativeBiomedicalIndicatorMeasureDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.QuantitativeBiomedicalIndicatorMeasureDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.BiomedicalIndicatorsQualitativeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.BiomedicalIndicatorsQuantitativeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.PatientBiomedicalIndicatorBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQualitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQuantitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PatientBiomedicalIndicator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Stateless
public class PatientBean extends UserBean<Patient> {
    @EJB
    private BiomedicalIndicatorsQualitativeBean qualitativeBean;
    @EJB
    private BiomedicalIndicatorsQuantitativeBean quantitativeBean;

    @EJB
    private AuthBean authBean;

    @EJB
    private PatientBiomedicalIndicatorBean patientBiomedicalIndicatorBean;

    public PatientBean() {

    }
    @Override
    public void postCreate(Patient patient) {

        //authBean.generateToken();

    }

    public void addQuantitativeBiomedicalIndicator(String username, QuantitativeBiomedicalIndicatorMeasureDTO dto) throws MyEntityNotFoundException, MyIllegalArgumentException, MyConstraintViolationException {
        //patient exists?
        Patient patient = findOrFail(username);

        //Indicator exists?
        BiomedicalIndicatorsQuantitative quant = quantitativeBean.findOrFail(dto.getId());

        //Values in range?
        if(!quant.isValid(dto.getValue())) {
            throw  new MyIllegalArgumentException("value: should be between " + quant.getMin() + " and " + quant.getMax());
        }

        //Dto contains date?
        if(dto.getDate() == null)
            dto.setDate(LocalDateTime.now());

        try {
            patient.addQuantitativeBiomedicalIndicator(quant, dto.getValue(), dto.getDate(), dto.getDescription());
            update(patient);
        } catch (ConstraintViolationException ex) {
            throw new MyConstraintViolationException(ex);
        }

    }

    public void addQualitativeBiomedicalIndicator(String username, QualitativeBiomedicalIndicatorMeasureDTO dto) throws MyEntityNotFoundException, MyIllegalArgumentException, MyConstraintViolationException {
        //patient exists?
        Patient patient = findOrFail(username);

        //Indicator exists?
        BiomedicalIndicatorsQualitative qual = qualitativeBean.findOrFail(dto.getId());

        //Values is valid?
        if(!qual.isValid(dto.getValue())) {
            throw  new MyIllegalArgumentException("value: is invalid");
        }

        //Dto contains date?
        if(dto.getDate() == null)
            dto.setDate(LocalDateTime.now());

        try {
            patient.addQualitativeBiomedicalIndicator(qual, dto.getValue().toUpperCase(), dto.getDate(), dto.getDescription());
            update(patient);
        } catch (ConstraintViolationException ex) {
            throw new MyConstraintViolationException(ex);
        }

    }

    public void removePatientRegisters(String username, long measureId) throws MyEntityNotFoundException, MyConstraintViolationException {
        Patient patient = findOrFail(username);

        List<PatientBiomedicalIndicator> register = getPatientRegisters(username);
        PatientBiomedicalIndicator indicator = register.stream().filter(a -> a.getId() == measureId).collect(Collectors.toList()).get(0);
        if(indicator == null) {
            return;
        }

        patientBiomedicalIndicatorBean.destroy(indicator.getId());

        //this method isn't working!!
        //patient.removeBiomedicalIndicator(indicator);


    }


    //BIOMEDICAL REGISTERS
    public List<PatientBiomedicalIndicator> getPatientRegisters(String username) throws MyEntityNotFoundException {
        findOrFail(username);

        List<PatientBiomedicalIndicator> list = em.createNamedQuery("getBiomedicalRegisters").setParameter("user", username).getResultList();

        return list == null ? new LinkedList<PatientBiomedicalIndicator>() : list;
    }

    public PatientBiomedicalIndicator getPatientRegister(String username, Long id) throws MyEntityNotFoundException {
        findOrFail(username);

        return patientBiomedicalIndicatorBean.findOrFail(id);

    }

    public PatientBiomedicalIndicator<Long> editPatientRegistersQuantitative(String username, Long id, QuantitativeBiomedicalIndicatorMeasureDTO dto) throws MyEntityNotFoundException, MyConstraintViolationException {
        findOrFail(username);

        PatientBiomedicalIndicator ind = patientBiomedicalIndicatorBean.findOrFail(id);
        ind.setDate(dto.getDate());
        System.out.println(dto.getValue());
        ind.setDescription(dto.getDescription());
        ind.setValue(dto.getValue());
        return patientBiomedicalIndicatorBean.update(ind);


    }

    public PatientBiomedicalIndicator<String> editPatientRegistersQuanlitative(String username, Long id, QualitativeBiomedicalIndicatorMeasureDTO dto) throws MyEntityNotFoundException, MyConstraintViolationException {
        findOrFail(username);

        PatientBiomedicalIndicator ind = patientBiomedicalIndicatorBean.findOrFail(id);
        ind.setDate(dto.getDate());
        System.out.println(dto.getValue());
        ind.setDescription(dto.getDescription());
        ind.setValue(dto.getValue());
        return patientBiomedicalIndicatorBean.update(ind);
    }
}
