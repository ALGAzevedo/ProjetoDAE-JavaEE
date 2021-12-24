package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities;

import io.smallrye.common.constraint.Nullable;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Country;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.Gender;
import pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum.MaritalStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(
        name = "PATIENTS"
)
@NamedQueries({
        @NamedQuery(
                name = "getAllPatients",
                query = "SELECT s FROM Patient s ORDER BY s.name" // JPQL
        )
})
public class Patient extends User{

        @Nullable
        @OneToMany(mappedBy = "patient", cascade = CascadeType.DETACH) //TODO: DETACH TO NOT REMOVE TREATMENTS
        private List<PRC> prcList;

        public Patient() {
                this.prcList = new LinkedList<PRC>();
        }

        public Patient(String name, String username, String email, Gender gender, LocalDate birthDate,
                       Country country, String social_security_number, String password, MaritalStatus maritalStatus,
                       String address, String city, String postal_code, String phone_number, String emergency_phone_number,
                       List<PRC> prcList) {
                super(name, username, email, gender, birthDate, country, social_security_number, password, maritalStatus, address, city, postal_code, phone_number, emergency_phone_number);
                this.prcList = new LinkedList<PRC>();
        }

        public List<PRC> getPrcList() {
                return prcList;
        }

        public void setPrcList(List<PRC> prcList) {
                this.prcList = prcList;
        }

        public void addPrc(PRC prc) {
                if(!this.prcList.contains(prc)) {
                        this.prcList.add(prc);
                }
        }
}
