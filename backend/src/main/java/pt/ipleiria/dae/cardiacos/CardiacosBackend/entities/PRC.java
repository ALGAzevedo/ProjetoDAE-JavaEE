package pt.ipleiria.dae.cardiacos.CardiacosBackend.entities;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.common.constraint.Nullable;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(
        name = "prcs")
@NamedQueries({
        @NamedQuery(
                name = "getAllPrcs",
                query = "SELECT c FROM PRC c ORDER BY c.name" // JPQL
        )
})
public class PRC implements Serializable {
    @Id
    private int code;

    @NotNull
    private String name;

    @Nullable
    private String description;

    public PRC() {
    }

    public PRC(int code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
