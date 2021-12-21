package pt.ipleiria.estg.dei.ei.dae.cardiacos.entities.Enum;

public enum MaritalStatus {
    SINGLE("Single"),
    MARRIED("Married"),
    WIDOWED("Widowed"),
    DIVORSED("Divorced"),
    SEPARATED("Separated");


    private String status;

    MaritalStatus(String status) {
        this.status = status;
    }

    public String getUrl() {
        return status;
    }
}
