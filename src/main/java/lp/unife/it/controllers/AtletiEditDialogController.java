package lp.unife.it.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lp.unife.it.DateUtil;
import lp.unife.it.models.Atleta;

public class AtletiEditDialogController 
{
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telephoneField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Atleta atleta;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) 
    {
        this.dialogStage = dialogStage;
    }

    public void setAtleta(Atleta atleta) 
    {
        this.atleta = atleta;
        firstNameField.setText(atleta.getNome());
        lastNameField.setText(atleta.getCognome());
        addressField.setText(atleta.getIndirizzo());
        emailField.setText(atleta.getEmail());
        telephoneField.setText(atleta.getTelefono());
        birthdayField.setText(DateUtil.format(atleta.getDataNascita()));
        birthdayField.setPromptText("dd.mm.yyyy");
    }

    public boolean isOkClicked() 
    {
        return okClicked;
    }

    @FXML
    private void handleOk() 
    {
        if (isInputValid()) 
        {
            atleta.setNome(firstNameField.getText());
            atleta.setCognome(lastNameField.getText());
            atleta.setIndirizzo(addressField.getText());
            atleta.setEmail(emailField.getText());
            atleta.setTelefono(telephoneField.getText());
            atleta.setDataNascita(DateUtil.parse(birthdayField.getText()));
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() 
    {
        dialogStage.close();
    }

    private boolean isInputValid() 
    {
        StringBuilder errorMessage = new StringBuilder();

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage.append("No valid first name!\n");
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage.append("No valid last name!\n");
        }
        if (addressField.getText() == null || addressField.getText().length() == 0) {
            errorMessage.append("No valid address!\n");
        }
        if (emailField.getText() == null || emailField.getText().length() == 0) {
            errorMessage.append("No valid email!\n");
        }
        if (telephoneField.getText() == null || telephoneField.getText().length() == 0) {
            errorMessage.append("No valid telephone!\n");
        }
        if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage.append("No valid birthday!\n");
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage.append("No valid birthday. Use the format dd.mm.yyyy!\n");
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
            return false;
        }
    }
}
