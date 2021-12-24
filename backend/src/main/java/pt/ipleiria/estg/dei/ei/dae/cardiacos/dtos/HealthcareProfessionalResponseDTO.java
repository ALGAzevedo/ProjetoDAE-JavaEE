package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

public class HealthcareProfessionalResponseDTO extends UserResponseDTO {
    private String institutionalEmail;
    private String institutionalPhone;

    public String getInstitutionalEmail() {
        return institutionalEmail;
    }

    public void setInstitutionalEmail(String institutionalEmail) {
        this.institutionalEmail = institutionalEmail;
    }

    public String getInstitutionalPhone() {
        return institutionalPhone;
    }

    public void setInstitutionalPhone(String institutionalPhone) {
        this.institutionalPhone = institutionalPhone;
    }
}
