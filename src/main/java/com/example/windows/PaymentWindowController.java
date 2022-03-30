package com.example.windows;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.time.LocalDate;

public class PaymentWindowController {

    @FXML
    private Button btnClose;

    @FXML
    private Button btnNewPayment;

    @FXML
    private TableColumn<?, ?> colAmtCharged;

    @FXML
    private TableColumn<?, ?> colFirstDayOccupied;

    @FXML
    private TableColumn<?, ?> colLastDayOccupied;

    @FXML
    private TableColumn<?, ?> colPaymentDate;

    @FXML
    private TableColumn<?, ?> colPhoneUse;

    @FXML
    private TableColumn<?, ?> colProcessedBy;

    @FXML
    private TableColumn<?, ?> colProcessedFor;

    @FXML
    private TableColumn<?, ?> colReceiptNumber;

    @FXML
    private TableColumn<?, ?> colSubTotal;

    @FXML
    private TableColumn<?, ?> colTaxAmount;

    @FXML
    private TableColumn<?, ?> colTaxRate;

    @FXML
    private TableColumn<?, ?> colTotalAmountPaid;

    @FXML
    private TableColumn<?, ?> colTotalNights;

    @FXML
    private TableView<Payment> lvwPayments;


    @FXML
    public void initialize() {


         System.out.println("Hello Init2");
        PropertyValueFactory facAmtCharged = new PropertyValueFactory<Payment, Double>("AmountCharged");
        colAmtCharged.setCellValueFactory(facAmtCharged);

        PropertyValueFactory facPaymentDate = new PropertyValueFactory<Payment, LocalDate>("PaymentDate");
        colPaymentDate.setCellValueFactory(facPaymentDate);

        PropertyValueFactory  facFirstDayOccupied= new PropertyValueFactory<Payment,LocalDate>("FirstDayOccupied");
        colFirstDayOccupied.setCellValueFactory(facFirstDayOccupied);

        PropertyValueFactory facLastDayOccupied = new PropertyValueFactory<Payment, LocalDate>("LastDayOccupied");
        colLastDayOccupied.setCellValueFactory(facLastDayOccupied);

        PropertyValueFactory facTotalNights = new PropertyValueFactory<Payment, Integer>("TotalNights");
        colTotalNights.setCellValueFactory(facTotalNights);

        PropertyValueFactory facPhoneUse = new PropertyValueFactory<Payment, Double>("PhoneUse");
        colPhoneUse.setCellValueFactory(facPhoneUse);

        PropertyValueFactory facProcessedBy = new PropertyValueFactory<Payment, String>("ProcessedBy");
        colProcessedBy.setCellValueFactory(facProcessedBy);

        PropertyValueFactory facProcessedFor = new PropertyValueFactory<Payment, Double>("ProcessedFor");
        colProcessedFor.setCellValueFactory(facProcessedFor);

        PropertyValueFactory facSubTotal = new PropertyValueFactory<Payment, Double>("SubTotal");
        colSubTotal.setCellValueFactory(facSubTotal);

        PropertyValueFactory facReceiptNumber = new PropertyValueFactory<Payment, Double>("ReceiptNumber");
        colReceiptNumber.setCellValueFactory(facReceiptNumber);

        PropertyValueFactory facTaxAmount = new PropertyValueFactory<Payment, Double>("TaxAmount");
        colTaxAmount.setCellValueFactory(facTaxAmount);

        PropertyValueFactory facTotalAmountPaid = new PropertyValueFactory<Payment, Double>("TotalAmountPaid");
        colTotalAmountPaid.setCellValueFactory(facTotalAmountPaid);

        PropertyValueFactory facAmountCharged = new PropertyValueFactory<Payment, Double>("AmountCharged");
        colAmtCharged.setCellValueFactory(facAmountCharged);

        PropertyValueFactory facTaxRate = new PropertyValueFactory<Payment, Double>("TaxRate");
        colTaxRate.setCellValueFactory(facTaxRate);

        System.out.println("okay!");


    }

    private Stage ChildStage;

    public void Init(ObservableList<Payment> data, Stage childStage){

        System.out.println(data.get(0).ProcessedBy);

        lvwPayments.setItems(data);

        ChildStage = childStage;
    }

    @FXML
    void onClickClose(ActionEvent event) {
        Stage stage = (Stage) btnClose.getScene().getWindow();
        stage.close();
    }

    @FXML
    void onClickNewPayment(ActionEvent event) {

        ChildStage.show();

    }

}
