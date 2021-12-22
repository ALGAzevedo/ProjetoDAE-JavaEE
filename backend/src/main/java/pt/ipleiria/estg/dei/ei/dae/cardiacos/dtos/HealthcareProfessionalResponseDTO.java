package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

public class HealthcareProfessionalResponseDTO extends UserResponseDTO {
    private String institutional_email;
    private String institutional_phone;

    public String getInstitutional_email() {
        return institutional_email;
    }

    public void setInstitutional_email(String institutional_email) {
        this.institutional_email = institutional_email;
    }

    public String getInstitutional_phone() {
        return institutional_phone;
    }

    public void setInstitutional_phone(String institutional_phone) {
        this.institutional_phone = institutional_phone;
    }
}
