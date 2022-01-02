package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs.TreatmentBeans.BehaviourBean;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.*;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Behaviour;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.time.LocalDate;

@Singleton
@Startup
public class ConfigBean {

    @EJB
    private AdministratorBean administratorBean;
    @EJB
    private PatientBean patientBean;
    @EJB
    private PRCBean prcBean;
    @EJB
    private HealthcareProfissionalBean healthcareProfissionalBean;
    @EJB
    private BehaviourBean behaviourBean;


    @PostConstruct
    public void PopulateDB() throws MyConstraintViolationException, MyEntityExistsException, MyEntityNotFoundException, MyUniqueConstraintViolationException, MyIllegalArgumentException {
        Administrator admin = new Administrator("Joao Ferreira", "JF_admin", "JF@mail.pt", Gender.MALE,
                LocalDate.of(1989, 5, 9), Country.PORTUGAL, "12345678912",
                MaritalStatus.SINGLE, "Rua centra", "Leiria", "2420-208", "963768088",
                "963768088");
        Administrator createdAdmin = administratorBean.create(admin);

        var patient1 = new Patient("Jorge Miguel", "jorge123", "jorge@mail.com",
                Gender.MALE, LocalDate.of(1988,5,3), Country.PORTUGAL, "22016759458", MaritalStatus.SINGLE, "Rua do fim à vista", "Alcoba", "2444-244",
                "956842345", "967561234");
        patientBean.create(patient1);


        PRC prc = new PRC("PRC1", "Cardiac Recuperation Process", LocalDate.of(2021, 12, 24), LocalDate.of(2022, 4, 22), patient1);
        prcBean.create(prc);
        HealthcareProfessional healthcareProfissional = new HealthcareProfessional("Andre", "andre123", "andre@mail.com",
                Gender.MALE, LocalDate.of(1988, 5, 3), Country.PORTUGAL, "22016751358",
                "password", MaritalStatus.SINGLE, "Rua do fim à vista", "Alcoba", "2444-244",
                "956842345", "967561234", "andremedico@mail.com", "926373737");
        healthcareProfissionalBean.create(healthcareProfissional);

        Behaviour behaviour = new Behaviour("Batatas", "behaviours", "Regulate your levels of stress", LocalDate.of(2021, 12, 24), LocalDate.of(2022, 4, 22), healthcareProfissional, prc);
        behaviourBean.create(behaviour);
    }
}

