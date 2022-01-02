package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ValidationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.QualitativeBiomedicalIndicatorMeasureDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.QuantitativeBiomedicalIndicatorMeasureDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.BiomedicalIndicatorsQualitativeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.BiomedicalIndicatorsQuantitativeBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.Email.EmailBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQualitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQuantitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans.PatientBiomedicalIndicatorBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.*;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;
import javax.persistence.Query;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MultivaluedMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;



import javax.ejb.EJB;
import javax.ejb.Stateless;



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

    @EJB
    private PatientBiomedicalIndicatorBean patientBiomedicalIndicatorBean;

    public PatientBean() {

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
        if(!(isQualitativeInRange(dto.getValue(), qual))) {
            throw new MyIllegalArgumentException("value: invalid");
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

    public void removePatientRegisters(String username, long measureId) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        Patient patient = findOrFail(username);

        List<PatientBiomedicalIndicator> register = em.createNamedQuery("getBiomedicalRegisters").setParameter("user", username).getResultList();
        PatientBiomedicalIndicator indicator = register.stream().filter(a -> a.getId() == measureId).collect(Collectors.toList()).get(0);
        if(indicator == null) {
            return;
        }

        patientBiomedicalIndicatorBean.destroy(indicator.getId());

        //this method isn't working!!
        //patient.removeBiomedicalIndicator(indicator);


    }


    //BIOMEDICAL REGISTERS
    public List<PatientBiomedicalIndicator> getPatientRegisters(String username, MultivaluedMap<String, String> queryParams) throws MyEntityNotFoundException {
        findOrFail(username);


        return filterListIndicators(queryParams, username);
    }

    public PatientBiomedicalIndicator getPatientRegister(String username, Long id) throws MyEntityNotFoundException {
        findOrFail(username);

        return patientBiomedicalIndicatorBean.findOrFail(id);

    }

    public PatientBiomedicalIndicator<Long> editPatientRegistersQuantitative(String username, Long id, QuantitativeBiomedicalIndicatorMeasureDTO dto) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        findOrFail(username);

        PatientBiomedicalIndicator ind = patientBiomedicalIndicatorBean.findOrFail(id);

        if(!(ind.getIndicator() instanceof BiomedicalIndicatorsQuantitative) )
            throw new MyEntityNotFoundException("");

        if(!(isQuantitativeInRange(dto.getValue(), (BiomedicalIndicatorsQuantitative) ind.getIndicator()))) {
            throw new MyIllegalArgumentException("value: invalid");
        }


        ind.setDate(dto.getDate());
        ind.setDescription(dto.getDescription());
        ind.setValue(dto.getValue());
        return patientBiomedicalIndicatorBean.update(ind);


    }

    public PatientBiomedicalIndicator<String> editPatientRegistersQuanlitative(String username, Long id, QualitativeBiomedicalIndicatorMeasureDTO dto) throws MyEntityNotFoundException, MyConstraintViolationException, MyIllegalArgumentException {
        findOrFail(username);

        PatientBiomedicalIndicator ind = patientBiomedicalIndicatorBean.findOrFail(id);
        if(!(ind.getIndicator() instanceof BiomedicalIndicatorsQualitative) )
            throw new MyEntityNotFoundException("");

        if(!(isQualitativeInRange(dto.getValue(), (BiomedicalIndicatorsQualitative) ind.getIndicator()))) {
            throw new MyIllegalArgumentException("value: invalid");
        }

        ind.setDate(dto.getDate());
        ind.setDescription(dto.getDescription());
        ind.setValue(dto.getValue());
        return patientBiomedicalIndicatorBean.update(ind);
    }

    public List<PatientBiomedicalIndicator> getAllPatientRegisters() {
        return em.createNamedQuery("getAllPatientBiomedicalIndicators").getResultList();

    }


    private boolean isQualitativeInRange(String val, BiomedicalIndicatorsQualitative ind) {
        return ind.isValid(val);

    }

    private boolean isQuantitativeInRange(Double val, BiomedicalIndicatorsQuantitative ind) {
        return ind.isValid(val);
    }



    public List<PatientBiomedicalIndicator> filterListIndicators(MultivaluedMap<String, String> queryParams, String username) {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        StringBuilder queryBuilder = new StringBuilder();
        List<String> whereCause = new ArrayList<String>();
        queryBuilder.append("SELECT s FROM PatientBiomedicalIndicator s INNER JOIN s.patient p INNER JOIN s.indicator i");

        if(!username.isEmpty()) {
            whereCause.add("UPPER(p.username) =  UPPER(:patientUsername)");
            paramaterMap.put("patientUsername", username);
        }
        else if(queryParams.containsKey("patientUsername")) {
            whereCause.add("UPPER(p.username) =  UPPER(:patientUsername)");
            paramaterMap.put("patientUsername", queryParams.get("patientUsername").get(0));
        }
        if(queryParams.containsKey("patientName")) {
            whereCause.add("UPPER(p.name) LIKE  UPPER(:patientName)");
            paramaterMap.put("patientName", "%"+queryParams.get("patientName").get(0)+"%");
        }

        if(queryParams.containsKey("indicator")) {
            whereCause.add("UPPER(i.name) LIKE  UPPER(:indicator)");
            paramaterMap.put("indicator", "%"+queryParams.get("indicator").get(0)+"%");
        }
        if(queryParams.containsKey("type")) {
            whereCause.add("UPPER(i.indicatorType) LIKE  UPPER(:indicatorType)");
            paramaterMap.put("indicatorType", "%"+queryParams.get("type").get(0)+"%");
        }

        if(queryParams.containsKey("startDate")) {
            whereCause.add("s.date >= :startDate");
            DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd[ [HH][:mm][:ss][.SSS]]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                    .toFormatter();

            LocalDateTime dt = LocalDateTime.parse(queryParams.get("startDate").get(0), df);
            paramaterMap.put("startDate", dt);
        }

        if(queryParams.containsKey("endDate")) {
            whereCause.add("s.date <= :endDate");
            DateTimeFormatter df = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd[ [HH][:mm][:ss][.SSS]]")
                    .parseDefaulting(ChronoField.HOUR_OF_DAY, 23)
                    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
                    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 59)
                    .toFormatter();

            LocalDateTime dt = LocalDateTime.parse(queryParams.get("endDate").get(0), df);
            paramaterMap.put("endDate", dt);
        }
        if(!queryParams.isEmpty() || !username.isEmpty())
            queryBuilder.append(" where " + StringUtils.join(whereCause, " and "));

        Query jpaQuery = em.createQuery(queryBuilder.toString());


        for(String key :paramaterMap.keySet()) {
            jpaQuery.setParameter(key, paramaterMap.get(key));
        }

        return jpaQuery.getResultList();

    }

}
