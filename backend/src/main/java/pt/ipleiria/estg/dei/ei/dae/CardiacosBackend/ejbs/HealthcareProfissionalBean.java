package pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.ejbs;

import pt.ipleiria.estg.dei.ei.dae.CardiacosBackend.entities.HealthcareProfessional;

import javax.ejb.Stateless;

@Stateless
public class HealthcareProfissionalBean extends BaseBean<HealthcareProfessional, Long> {
    public HealthcareProfissionalBean(Class<HealthcareProfessional> entityClass) {
        super(entityClass);
    }
}
