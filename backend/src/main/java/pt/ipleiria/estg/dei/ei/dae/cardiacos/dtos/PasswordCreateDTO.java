package pt.ipleiria.estg.dei.ei.dae.cardiacos.dtos;

public class PasswordCreateDTO implements DTO{
    private String password;
    private String confirmPassword;

    public PasswordCreateDTO() {
    }

    public PasswordCreateDTO(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
