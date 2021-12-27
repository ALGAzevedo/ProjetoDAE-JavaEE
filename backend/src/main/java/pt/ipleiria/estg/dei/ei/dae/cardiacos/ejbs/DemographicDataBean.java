package pt.ipleiria.estg.dei.ei.dae.cardiacos.ejbs;

import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import javax.ejb.Stateless;
import java.util.Arrays;

@Stateless
public class DemographicDataBean {
    public static String[] getEnums(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }

    public  static String[] getCountries() {
        return getEnums(Country.class);
    }

    public static String[] getMartitalStatus() {
        return getEnums(MaritalStatus.class);
    }

    public static String[] getGenders() {
        return getEnums(Gender.class);
    }

    public static String[][] getAll() {
        String[] countries = getCountries();
        String[] maritalStatus = getMartitalStatus();
        String[] genders = getGenders();

        String[][] data = {countries, maritalStatus, genders};

        return data;
    }



}
