package com.example.windows;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;

public class OccupancyEditorController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOK;

    @FXML
    private DatePicker dtpDateOccupied;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtCustomerNumber;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtEmployeeNumber;

    @FXML
    private TextField txtOccupancyNumber;

    @FXML
    private TextField txtPhoneUse;

    @FXML
    private TextField txtRateApplied;

    @FXML
    private TextField txtRoomDescription;

    @FXML
    private TextField txtRoomNumber;


    ObservableList data;

    @FXML
    public void initialize() {

        System.out.println("hello Init");

        dtpDateOccupied.setEditable(false);

        txtRateApplied.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("^[1-9]\\d*(\\.\\d+)?$")) {
                    txtRateApplied.setText(newValue.replaceAll("[^[1-9]\\d*(\\.\\d+)?$]", ""));
                }
            }

        });

        txtPhoneUse.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("^[1-9]\\d*(\\.\\d+)?$")) {
                    txtPhoneUse.setText(newValue.replaceAll("[^[1-9]\\d*(\\.\\d+)?$]", ""));
                }
            }

        });
    }

    ;


    public void setData(ObservableList inputList) {
        data = inputList;
    }


    @FXML
    void onClickCancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();

    }

    @FXML
    void onClickOK(ActionEvent event) throws NumberFormatException {

        try {
            if (dtpDateOccupied.getValue() instanceof LocalDate != true) {
                return;
            }
            ;
            Double rate = Double.parseDouble(txtRateApplied.getText());
            Double phone = Double.parseDouble(txtPhoneUse.getText());
            data.add(new Occupancy(txtOccupancyNumber.getText(), txtEmployeeNumber.getText(), dtpDateOccupied.getValue(), txtCustomerNumber.getText(), txtRoomNumber.getText(), rate, phone));

            txtOccupancyNumber.setText("");
            txtPhoneUse.setText("");
            txtRateApplied.setText("");
            txtCustomerName.setText("");
            txtCustomerNumber.setText("");
            txtEmployeeName.setText("");
            txtEmployeeNumber.setText("");
            txtRoomDescription.setText("");
            txtRoomNumber.setText("");
            dtpDateOccupied.setValue(null);
        } catch (NumberFormatException e) {

        }
    }

}
