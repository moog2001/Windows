package com.example.windows;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.time.LocalDate;

public class PaymentEditorController {

    @FXML
    private Button btnCalculate;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnOK;

    @FXML
    private DatePicker dtpFirstDateOccupied;

    @FXML
    private DatePicker dtpLastDateOccupied;

    @FXML
    private DatePicker dtpPaymentDate;

    @FXML
    private TextField txtAmountCharged;

    @FXML
    private TextField txtAmountPaid;

    @FXML
    private TextField txtEmployeeName;

    @FXML
    private TextField txtEmployeeNumber;

    @FXML
    private TextField txtOccupancyDetails;

    @FXML
    private TextField txtOccupancyNumber;

    @FXML
    private TextField txtPhoneUse;

    @FXML
    private TextField txtReceiptNumber;

    @FXML
    private TextField txtTotalNights;

    @FXML
    private TextField txtSubTotal;

    @FXML
    private TextField txtTaxAmount;

    @FXML
    private TextField txtTaxRate;

    private ObservableList<Payment> Data;

    @FXML
    void btnClickCalculate(ActionEvent event) {

        try {
            Double subTotal = Double.parseDouble(txtSubTotal.getText());
            Double taxRate = Double.parseDouble(txtTaxRate.getText());

            Double taxAmount = createTaxAmount(taxRate, subTotal);
            Double TotalAmount = createAmountPaid(taxAmount, subTotal);


            txtTaxAmount.setText(String.format("%.2f", taxAmount));
            txtAmountPaid.setText(String.format("%.2f", TotalAmount));

        } catch (NumberFormatException e) {
            return;
        }

    }

    private Double createSubTotal(Double amtCharged, Double phoneUse) {
        Double subTotal = Double.sum(amtCharged.doubleValue(), phoneUse.doubleValue());
        return subTotal;
    }

    private Double createTaxAmount(Double taxRate, Double subTotal) {
        Double taxAmount = Double.valueOf(subTotal.doubleValue() / taxRate.doubleValue());
        return taxAmount;
    }

    private Double createAmountPaid(Double taxAmount, Double subTotal) {
        Double AmountPaid = Double.sum(subTotal.doubleValue(), taxAmount.doubleValue());
        return AmountPaid;
    }


    @FXML
    void btnClickCancel(ActionEvent event) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnClickOK(ActionEvent event) {

        try {
            Integer totalNights = Integer.getInteger(txtTotalNights.getText());
            Double amountCharged = Double.parseDouble(txtAmountCharged.getText());
            Double phoneUse = Double.parseDouble(txtPhoneUse.getText());
            Double taxRate = Double.parseDouble(txtTaxRate.getText());
            Double subTotal = Double.parseDouble(txtSubTotal.getText());
            Double taxAmount = Double.parseDouble(txtTaxAmount.getText());
            Double amountPaid = Double.parseDouble(txtAmountPaid.getText());


            Data.add(
                    new Payment(txtReceiptNumber.getText(),
                            txtEmployeeNumber.getText(),
                            dtpPaymentDate.getValue(),
                            txtOccupancyNumber.getText(),
                            dtpFirstDateOccupied.getValue(),
                            dtpLastDateOccupied.getValue(),
                            totalNights,
                            amountCharged,
                            phoneUse,
                            subTotal,
                            taxRate,
                            taxAmount,
                            amountPaid
            ));
        } catch (NumberFormatException e) {
            return;
        }

    }

    @FXML
    public void initialize() {

        StringProperty amountCharged = txtAmountCharged.textProperty();


        StringProperty phoneUse = txtPhoneUse.textProperty();
        DoubleProperty subTotal = new SimpleDoubleProperty();

        makeTextFieldNumber(txtAmountCharged);
        makeTextFieldNumber(txtPhoneUse);
        makeTextFieldNumber(txtTaxRate);

        dtpPaymentDate.setEditable(false);
        dtpFirstDateOccupied.setEditable(false);
        dtpLastDateOccupied.setEditable(false);
        txtAmountPaid.setEditable(false);
        txtTaxAmount.setEditable(false);
        txtSubTotal.setEditable(false);

        try {
            subTotal.bind(Bindings.createDoubleBinding(() -> {
                return Double.sum(Double.parseDouble(amountCharged.get()), Double.parseDouble(phoneUse.get()));
            }, amountCharged, phoneUse));

            txtSubTotal.textProperty().bind(subTotal.asString());


        } catch (NumberFormatException e) {
            return;
        }
//        txtSubTotal.textProperty().bindBidirectional(((Property)subTotal.getBean()));

    }

    public void Init(ObservableList<Payment> data) {
        Data = data;
    }

    private void makeTextFieldNumber(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("^[1-9]\\d*(\\.\\d+)?$")) {
                    textField.setText(newValue.replaceAll("[^[1-9]\\d*(\\.\\d+)?$]", ""));
                }
            }
        });

    }

}
