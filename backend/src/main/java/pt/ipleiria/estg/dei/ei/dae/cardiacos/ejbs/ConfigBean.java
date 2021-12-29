package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.PRC;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Patient;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.TreatmentTypes.Diet;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.*;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.io.Console;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;

@Singleton
@Startup
public class ConfigBean {

    @EJB
    private AdministratorBean administratorBean;
    @EJB
    private PatientBean patientBean;
    @EJB
    private PRCBean prcBean;



    @PostConstruct
    public void PopulateDB() throws MyConstraintViolationException, MyEntityExistsException, MyEntityNotFoundException, MyUniqueConstraintViolationException, MyIllegalArgumentException {
        Administrator admin = new Administrator("Joao Ferreira", "JF_admin", "JF@mail.pt", Gender.MALE,
                LocalDate.of(1989, 5, 9), Country.PORTUGAL, "12345678912", "12345678",
                MaritalStatus.SINGLE, "Rua centra", "Leiria", "2420-208", "963768088",
                "963768088");
        Administrator createdAdmin =  administratorBean.create(admin);

        var patient1 = new Patient("Jorge Miguel", "jorge123", "jorge@mail.com",
                Gender.MALE, LocalDate.of(1988,5,3), Country.PORTUGAL, "22016759458",
                "password", MaritalStatus.SINGLE, "Rua do fim à vista", "Alcoba", "2444-244",
                "956842345", "967561234");
       patientBean.create(patient1);


        PRC prc = new PRC("PRC1", "Cardiac Recuperation Process", LocalDate.of(2021,12,24), LocalDate.of(2022,4,22), patient1);
        prcBean.create(prc);
    }
}

