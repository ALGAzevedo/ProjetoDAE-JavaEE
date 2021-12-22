package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.HealthcareProfessional;

import javax.ejb.Stateless;

@Stateless
public class HealthcareProfissionalBean extends BaseBean<HealthcareProfessional, Long> {
    public HealthcareProfissionalBean() {
        super(HealthcareProfessional.class);
    }
}
