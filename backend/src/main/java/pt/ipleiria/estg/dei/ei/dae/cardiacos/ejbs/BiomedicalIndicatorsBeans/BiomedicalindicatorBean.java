package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BiomedicalIndicatorGeneralResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BaseBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQualitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQuantitative;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class BiomedicalindicatorBean extends BaseBean<BiomedicalIndicator, Long> {
    @EJB
    private BiomedicalIndicatorsQualitativeBean qualitativeBean;
    @EJB
    private BiomedicalIndicatorsQuantitativeBean quantitativeBean;

    public LinkedList<BiomedicalIndicatorGeneralResponseDTO> getAll() {
        List<BiomedicalIndicatorsQualitative> qualitative = qualitativeBean.all();
        List<BiomedicalIndicatorsQuantitative> quantitative = quantitativeBean.all();

        LinkedList<BiomedicalIndicatorGeneralResponseDTO> listToReturn = new LinkedList<>();

        for (BiomedicalIndicatorsQuantitative indicator : quantitative) {
            listToReturn.add(new BiomedicalIndicatorGeneralResponseDTO(indicator.getId(), indicator.getName(), indicator.getUnity(), indicator.getMin(),
                    indicator.getMax(), null, "QUANTITATIVE"));

        }
        for (BiomedicalIndicatorsQualitative indicator : qualitative) {
            listToReturn.add(new BiomedicalIndicatorGeneralResponseDTO(indicator.getId(), indicator.getName(), indicator.getUnity(), Double.NaN,
                    Double.NaN, indicator.getPossibleValues(), "QUALITATIVE"));

        }
        return listToReturn;

    }

}

