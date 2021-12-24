package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions.MyEntityNotFoundException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;

@Singleton
@Startup
public class ConfigBean {

    @EJB
    private AdministratorBean administratorBean;



    @PostConstruct
    public void PopulateDB() throws MyConstraintViolationException, MyEntityExistsException, MyEntityNotFoundException {
        Administrator admin = new Administrator("Joao Ferreira", "JF_admin", "JF@mail.pt", Gender.MALE,
                LocalDate.of(1989, 5, 9), Country.PORTUGAL, "12345678912", "12345678",
                MaritalStatus.SINGLE, "Rua centra", "Leiria", "2420-208", "963768088",
                "963768088");
        Administrator createdAdmin =  administratorBean.create(admin);
    }
}

