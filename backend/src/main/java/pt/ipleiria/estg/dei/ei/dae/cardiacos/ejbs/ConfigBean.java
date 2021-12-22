package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;


import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Administrator;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

@Singleton // type and name for the EJB;
@Startup

public class ConfigBean {

    @EJB
    private AdministratorBean administratorBean;


    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");


    @PostConstruct
    public void populateDB() {
        try {
            administratorBean.create(new Administrator("João Ferreira", "admin1", "admin1@mail.pt", Gender.MALE,
                    new GregorianCalendar(1989, Calendar.MAY, 9).getTime(), Country.PORTUGAL,
                    "123456798101", "1234", MaritalStatus.SINGLE, "Rua Central n 2571", "Leiria",
                    "2420-208", "963768088", "963768088"));


        } catch (Exception e) {
        logger.log(Level.SEVERE, e.getMessage());
    }


    }
}
