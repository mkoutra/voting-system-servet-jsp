package personal.votingsystem.dto;

/**
 * DTO used to transfer possible errors of password change.
 *
 * @author Michail E. Koutrakis
 */
public class ChangePasswordErrorsDTO {
    private String currentPasswordError;
    private String newPasswordError;
    private String reEnteredPasswordError;

    public ChangePasswordErrorsDTO() {}

    public ChangePasswordErrorsDTO(String currentPasswordError, String newPasswordError, String reEnteredPasswordError) {
        this.currentPasswordError = currentPasswordError;
        this.newPasswordError = newPasswordError;
        this.reEnteredPasswordError = reEnteredPasswordError;
    }

    public String getCurrentPasswordError() {
        return currentPasswordError;
    }

    public void setCurrentPasswordError(String currentPasswordError) {
        this.currentPasswordError = currentPasswordError;
    }

    public String getNewPasswordError() {
        return newPasswordError;
    }

    public void setNewPasswordError(String newPasswordError) {
        this.newPasswordError = newPasswordError;
    }

    public String getReEnteredPasswordError() {
        return reEnteredPasswordError;
    }

    public void setReEnteredPasswordError(String reEnteredPasswordError) {
        this.reEnteredPasswordError = reEnteredPasswordError;
    }

    @Override
    public String toString() {
        return "ChangePasswordErrorsDTO{" +
                "currentPasswordError='" + currentPasswordError + '\'' +
                ", newPasswordError='" + newPasswordError + '\'' +
                ", reEnteredPasswordError='" + reEnteredPasswordError + '\'' +
                '}';
    }
}
