package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans;

import org.modelmapper.ModelMapper;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BIomedicalIdicatorUpdateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BiomedicalIndicatorGeneralResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BaseBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQualitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQuantitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PatientBiomedicalIndicator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.*;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.utils.EntityMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class BiomedicalindicatorBean extends BaseBean<BiomedicalIndicator, Long> {
    @EJB
    private BiomedicalIndicatorsQualitativeBean qualitativeBean;
    @EJB
    private BiomedicalIndicatorsQuantitativeBean quantitativeBean;


    public BiomedicalIndicator changeTypeOfIndicator(Long id, BIomedicalIdicatorUpdateDTO dto) throws MyIllegalArgumentException, MyEntityNotFoundException, MyConstraintViolationException, MyEntityExistsException, MyUniqueConstraintViolationException {
        //verify of its really the same old/new
        if(dto.getId() != id) {
            throw new MyIllegalArgumentException("Id's doesn't match");
        }
        if(dto.getIndicatorType().equals(dto.getNewType())) {
            throw new MyIllegalArgumentException("Old and new indicators type are equal, no change needed");
        }
        ModelMapper mapper = new ModelMapper();
        BiomedicalIndicator indicator = findOrFail(dto.getId());

        //is it soft deleted already?
        if(indicator.getDeletedAt() != null) {
            throw new EntityNotFoundException("Entity doens't exist");
        }

        if(!dto.getName().equalsIgnoreCase(indicator.getName())) {
            throw new MyIllegalArgumentException("Unable to change name of indicator");
        }



        //get type
        String type = indicator.getIndicatorType();


        BiomedicalIndicator newIndicator;
        //new type == qualitative? old type is really quantitative?
        if(dto.getNewType().equalsIgnoreCase("QUALITATIVE") &&
                type.equalsIgnoreCase("QUANTITATIVE")) {

            BiomedicalIndicatorsQualitative temp = new BiomedicalIndicatorsQualitative(dto.getName(), dto.getPossibleValues());
            temp.setPrevious(indicator);
            newIndicator = qualitativeBean.create(temp);
        }
        //new type == quantitative? old type == quantitative?
        else if(dto.getNewType().equalsIgnoreCase("QUANTITATIVE") &&
                type.equalsIgnoreCase("QUALITATIVE")) {

            BiomedicalIndicatorsQuantitative quant = new BiomedicalIndicatorsQuantitative(dto.getName(),
                    dto.getUnity(), dto.getMin(), dto.getMax());
            quant.setPrevious(indicator);
            newIndicator = quantitativeBean.create(quant);
        }
        else
            throw new MyIllegalArgumentException("Invalid Change of types");

        //idnicator should be "soft deleted"
        indicator.setDeletedAt(LocalDate.now());
        update(indicator);

        return newIndicator;

    }

    public List FindWithName(String name) {

        Query query = em.createNamedQuery("FindWithName");
        query.setParameter("name", name);

        return query.getResultList();

    }

    public List FindWithNameWithoutTrashed(String name) {

        Query query = em.createNamedQuery("FindWithNameWithoutTrashed");
        query.setParameter("name", name);

        return query.getResultList();

    }

    @Override
    public void destroy(Long primaryKey) throws MyEntityNotFoundException, MyConstraintViolationException {
        var entity = findOrFail(primaryKey);
        preDestroy(entity);
        //if biomedicalIndicator has values associated to him we just soft delete it
        if(entity.getPatientIndicatorValues().isEmpty()) {
            em.remove(entity);
        }else {
            System.out.println("values");
            LinkedList<PatientBiomedicalIndicator<?>> patInds = entity.getPatientIndicatorValues();
            for (PatientBiomedicalIndicator<?> patInd : patInds) {
                System.out.println("val->" + patInd.getValue());
            }
            entity.setDeletedAt(LocalDate.now());
            update(entity);
        }

        postDestroy(entity);
    }




}

