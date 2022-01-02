package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import org.modelmapper.ValidationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.QualitativeBiomedicalIndicatorMeasureDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.QuantitativeBiomedicalIndicatorMeasureDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.BiomedicalIndicatorsQualitativeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.BiomedicalIndicatorsQuantitativeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.Email.EmailBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Auth;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQualitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQuantitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.UUID;

@Stateless
public class PatientBean extends UserBean<Patient> {
    @EJB
    private BiomedicalIndicatorsQualitativeBean qualitativeBean;
    @EJB
    private BiomedicalIndicatorsQuantitativeBean quantitativeBean;

    @EJB
    private AuthBean authBean;

    @EJB
    private EmailBean emailBean;

    public PatientBean() {

    }
//    @Override
//    public void postCreate(Patient patient) throws MyConstraintViolationException, MyEntityNotFoundException, MyEntityExistsException, MyUniqueConstraintViolationException, MyIllegalArgumentException {
//
//       // String token = authBean.generateToken();
//        //authBean.create(new Auth(patient.getUsername(),token));
//
//        //TODO Send email
//        //String confirmationLink = "http://localhost:8081/confirm?token=" + token;
//        //emailBean.send(patient.getEmail(), "Confirm your email",patient.getName(), confirmationLink);
//
//     }

    public void addQuantitativeBiomedicalIndicator(String username, QuantitativeBiomedicalIndicatorMeasureDTO dto) throws MyEntityNotFoundException, MyIllegalArgumentException, MyConstraintViolationException {
        //patient exists?
        Patient patient = findOrFail(username);

        //Indicator exists?
        BiomedicalIndicatorsQuantitative quant = quantitativeBean.findOrFail(dto.getId());

        //Values in range?
        if(!quant.isValid(dto.getValue())) {
            throw  new MyIllegalArgumentException("Value should be between " + quant.getMin() + " and " + quant.getMax());
        }

        //Dto contains date?
        if(dto.getDate() == null)
            dto.setDate(LocalDate.now());

        try {
            patient.addQuantitativeBiomedicalIndicator(quant, dto.getValue(), dto.getDate());
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
            throw  new MyIllegalArgumentException("Value is invalid");
        }

        //Dto contains date?
        if(dto.getDate() == null)
            dto.setDate(LocalDate.now());

        try {
            patient.addQualitativeBiomedicalIndicator(qual, dto.getValue(), dto.getDate());
            update(patient);
        } catch (ConstraintViolationException ex) {
            throw new MyConstraintViolationException(ex);
        }

    }







}
