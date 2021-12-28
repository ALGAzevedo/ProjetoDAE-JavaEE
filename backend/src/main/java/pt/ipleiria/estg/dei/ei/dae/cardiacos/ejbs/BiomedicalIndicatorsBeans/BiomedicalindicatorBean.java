package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans;

import org.modelmapper.ModelMapper;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BIomedicalIdicatorUpdateDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos.BiomedicalIndicators.BiomedicalIndicatorGeneralResponseDTO;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BaseBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQualitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.BiomedicalIndicatorsQuantitative;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.*;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.utils.EntityMapper;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
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

    public BiomedicalIndicatorGeneralResponseDTO changeTypeOfIndicator(Long id, BIomedicalIdicatorUpdateDTO dto) throws MyIllegalArgumentException, MyEntityNotFoundException, MyConstraintViolationException, MyEntityExistsException, MyUniqueConstraintViolationException {
        //verify of its really the same old/new
        if(dto.getId() != id) {
            throw new MyIllegalArgumentException("Id's doesn't match");
        }
        if(dto.getIndicatorType().equals(dto.getNewType())) {
            throw new MyIllegalArgumentException("Old and new indicators type are equal, no change needed");
        }
        ModelMapper mapper = new ModelMapper();
        BiomedicalIndicator indicator = findOrFail(dto.getId());


        BiomedicalIndicator newIndicator;
        //new type == qualitative?
        if(dto.getNewType().equalsIgnoreCase("QUALITATIVE")) {
            BiomedicalIndicatorsQualitative temp = new BiomedicalIndicatorsQualitative(dto.getName(), dto.getPossibleValues());
            temp.setPrevious(indicator);
            newIndicator = qualitativeBean.create(temp);
        }
        else if(dto.getNewType().equalsIgnoreCase("QUANTITATIVE")) {
            BiomedicalIndicatorsQuantitative quant = new BiomedicalIndicatorsQuantitative(dto.getName(),
                    dto.getUnity(), dto.getMin(), dto.getMax());
            quant.setPrevious(indicator);
            newIndicator = quantitativeBean.create(quant);
        }
        else
            throw new MyIllegalArgumentException("Invalid new type");

        //idnicator should be "soft deleted"
        indicator.setDeletedAt(LocalDate.now());
        update(indicator);

        return mapper.map(newIndicator, BiomedicalIndicatorGeneralResponseDTO.class);



    }



}

