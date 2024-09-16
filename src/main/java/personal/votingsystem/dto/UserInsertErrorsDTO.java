package personal.votingsystem.dto;

/**
 * A DTO containing the potential error messages to be used in login page.
 *
 * @author Michail E. Koutrakis
 */
public class UserInsertErrorsDTO {
    private String usernameErrorMessage;
    private String emailErrorMessage;
    private String dateOfBirthErrorMessage;
    private String firstnameErrorMessage;
    private String lastnameErrorMessage;
    private String passwordErrorMessage;
    private String reEnteredPasswordErrorMessage;

    public UserInsertErrorsDTO() {}

    public UserInsertErrorsDTO(String usernameErrorMessage, String emailErrorMessage, String dateOfBirthErrorMessage,
                               String firstnameErrorMessage, String lastnameErrorMessage, String passwordErrorMessage,
                               String reEnteredPasswordErrorMessage) {
        this.usernameErrorMessage = usernameErrorMessage;
        this.emailErrorMessage = emailErrorMessage;
        this.dateOfBirthErrorMessage = dateOfBirthErrorMessage;
        this.firstnameErrorMessage = firstnameErrorMessage;
        this.lastnameErrorMessage = lastnameErrorMessage;
        this.passwordErrorMessage = passwordErrorMessage;
        this.reEnteredPasswordErrorMessage = reEnteredPasswordErrorMessage;
    }

    public String getUsernameErrorMessage() {
        return usernameErrorMessage;
    }

    public void setUsernameErrorMessage(String usernameErrorMessage) {
        this.usernameErrorMessage = usernameErrorMessage;
    }

    public String getEmailErrorMessage() {
        return emailErrorMessage;
    }

    public void setEmailErrorMessage(String emailErrorMessage) {
        this.emailErrorMessage = emailErrorMessage;
    }

    public String getDateOfBirthErrorMessage() {
        return dateOfBirthErrorMessage;
    }

    public void setDateOfBirthErrorMessage(String dateOfBirthErrorMessage) {
        this.dateOfBirthErrorMessage = dateOfBirthErrorMessage;
    }

    public String getFirstnameErrorMessage() {
        return firstnameErrorMessage;
    }

    public void setFirstnameErrorMessage(String firstnameErrorMessage) {
        this.firstnameErrorMessage = firstnameErrorMessage;
    }

    public String getLastnameErrorMessage() {
        return lastnameErrorMessage;
    }

    public void setLastnameErrorMessage(String lastnameErrorMessage) {
        this.lastnameErrorMessage = lastnameErrorMessage;
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage;
    }

    public void setPasswordErrorMessage(String passwordErrorMessage) {
        this.passwordErrorMessage = passwordErrorMessage;
    }

    public String getReEnteredPasswordErrorMessage() {
        return reEnteredPasswordErrorMessage;
    }

    public void setReEnteredPasswordErrorMessage(String reEnteredPasswordErrorMessage) {
        this.reEnteredPasswordErrorMessage = reEnteredPasswordErrorMessage;
    }

    @Override
    public String toString() {
        return "UserInsertErrorsDTO{" +
                "usernameErrorMessage='" + usernameErrorMessage + '\'' +
                ", emailErrorMessage='" + emailErrorMessage + '\'' +
                ", dateOfBirthErrorMessage='" + dateOfBirthErrorMessage + '\'' +
                ", firstnameErrorMessage='" + firstnameErrorMessage + '\'' +
                ", lastnameErrorMessage='" + lastnameErrorMessage + '\'' +
                ", passwordErrorMessage='" + passwordErrorMessage + '\'' +
                ", reEnteredPasswordErrorMessage='" + reEnteredPasswordErrorMessage + '\'' +
                '}';
    }
}
