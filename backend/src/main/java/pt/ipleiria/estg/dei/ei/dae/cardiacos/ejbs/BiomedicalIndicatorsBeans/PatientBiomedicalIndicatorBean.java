package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BiomedicalIndicatorsBeans;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.BaseBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PatientBiomedicalIndicator;

import javax.ejb.Stateless;

@Stateless
public class PatientBiomedicalIndicatorBean extends BaseBean<PatientBiomedicalIndicator, Long> {

    public PatientBiomedicalIndicatorBean() {
    }
}
