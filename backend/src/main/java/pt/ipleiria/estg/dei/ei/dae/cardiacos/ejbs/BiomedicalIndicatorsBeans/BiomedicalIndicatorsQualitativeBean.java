package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIcicatorQualitativeAddRemovePossibleValueDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BaseBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQualitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyUniqueConstraintViolationException;

import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Stateless
public class BiomedicalIndicatorsQualitativeBean extends BaseBean<BiomedicalIndicatorsQualitative,Long> {
    public BiomedicalIndicatorsQualitativeBean() {
    }

    @Override
    public void preCreate(BiomedicalIndicatorsQualitative entity) throws MyEntityExistsException, MyUniqueConstraintViolationException {
        //Transform all values in set to be in uppercase
        HashSet<String> possibleValuesUpper = new HashSet<>();
        for (String possibleValue : entity.getPossibleValues()) {
            //Transfor value in upperCase
            possibleValuesUpper.add(possibleValue.toUpperCase());
        }

        entity.setPossibleValues(possibleValuesUpper);
    }


    public BiomedicalIndicatorsQualitative addNewBiomedicalIndicatorsQualitativePossibleValue(Long id,
            BiomedicalIcicatorQualitativeAddRemovePossibleValueDTO dto) throws MyEntityNotFoundException, MyIllegalArgumentException, MyUniqueConstraintViolationException {

        //verify if indicator exists
        BiomedicalIndicatorsQualitative indicator = findOrFail(id);

        //if id from url != url in dto
        if(id != dto.getIndicatorID())
            throw new MyIllegalArgumentException("Id's doesn't match");

        if(indicator.containsValue(dto.getNewValue()))
            throw new MyUniqueConstraintViolationException("Values should be unique");
        indicator.addNewValue(dto.getNewValue());
        em.merge(indicator);

        return findOrFail(dto.getIndicatorID());
    }

    public BiomedicalIndicatorsQualitative removeBiomedicalIndicatorsQualitativePossibleValue(Long id,
            BiomedicalIcicatorQualitativeAddRemovePossibleValueDTO dto) throws MyEntityNotFoundException, MyIllegalArgumentException {

        //verify if indicator exists
        BiomedicalIndicatorsQualitative indicator = findOrFail(id);

        //if id from url != url in dto
        if(id != dto.getIndicatorID())
            throw new MyIllegalArgumentException("Id's doesn't match");

        indicator.removeVal(dto.getNewValue());
        em.merge(indicator);

        return findOrFail(dto.getIndicatorID());
    }
}
