package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BaseBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQualitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQuantitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class BiomedicalIndicatorsQuantitativeBean extends BaseBean<BiomedicalIndicatorsQuantitative, Long> {
    @EJB
    BiomedicalindicatorBean indicatorBean;

    @Override
    public void preCreate(BiomedicalIndicatorsQuantitative entity) throws MyEntityExistsException {
        entity.setIndicatorType("QUANTITATIVE");

        //we need to make sure name is unique, we only can reuse name if new entity is an update from an older version
        //id so we need to check name are not changing
        List<BiomedicalIndicator> previousIndicators = indicatorBean.FindWithName(entity.getName());

        if(!previousIndicators.isEmpty() && (entity.getPrevious() != previousIndicators.get(0))) {
            //it means that we are trying to assign existing name to a not related new entity
            throw new MyEntityExistsException("There is already an entity with existing name");
        }
    }

}
