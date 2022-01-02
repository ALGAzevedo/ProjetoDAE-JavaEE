package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BaseBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQualitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQuantitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyIllegalArgumentException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BiomedicalIndicatorsQuantitativeBean extends BaseBean<BiomedicalIndicatorsQuantitative, Long> {
    @EJB
    BiomedicalindicatorBean indicatorBean;

    @Override
    public void preCreate(BiomedicalIndicatorsQuantitative entity) throws MyEntityExistsException {
        //2 CASES WHEN WE CREATE
        //1-create create
        //2-We are creating new Indicator because its a new version, its an update from qualitative to quantitative
        //We need to preserve history, so we cant just overwrite old indicator


        //if its create?
        if(entity.getPrevious() == null) {
            //there is already an indicator with this name?
            List<BiomedicalIndicator> indicatorsWithSameName = indicatorBean.FindWithNameWithoutTrashed(entity.getName());
            if(!indicatorsWithSameName.isEmpty()) {
                throw new MyEntityExistsException("There is already an entity with existing name");
            }

        }

        entity.setIndicatorType("QUANTITATIVE");


    }

    @Override
    public void preUpdate(BiomedicalIndicatorsQuantitative entity) throws MyEntityNotFoundException, MyIllegalArgumentException {
        //name cant be update, its like a key for this indicator
        BiomedicalIndicatorsQuantitative ind = findOrFail(entity.getId());
        if(!ind.getName().equals(entity.getName())) {
            throw new MyIllegalArgumentException("Name can´t change");
        }


    }

}
