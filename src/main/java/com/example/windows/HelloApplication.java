package com.example.windows;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReferenceArray;


public class HelloApplication extends Application {

    ObservableList<Customer> dataCustomers = FXCollections.observableArrayList(
            new Customer("100752", "Caroline", "Lomey", "301-652-0700", "Albert Lomey", "301-412-5055")
    );

    ObservableList<Employee> dataEmployees = FXCollections.observableArrayList(
            new Employee("22958", "Andrew", "Laskin", "General Manager")
    );

    ObservableList<Room> dataRooms = FXCollections.observableArrayList(
            new Room("104", "Bedroom", "Queen", 75.85, "Occupied")
    );

    ObservableList<Occupancy> dataOccupancies = FXCollections.observableArrayList(
            new Occupancy("100001", "24095", LocalDate.of(2014, 6, 16), "100752", "106", null, null)
    );

    ObservableList<Payment> dataPayments = FXCollections.observableArrayList(
            new Payment("100001", "28405", LocalDate.of(2014, 6, 20), "100752", LocalDate.of(2014,6,16), LocalDate.of(2014,6,20), 4,75.85,0.0,createSubTotal(75.85, 0.0),7.7 ,createTaxAmount(7.7, 75.85),createAmountPaid(createTaxAmount(7.7, 75.85),75.85))
    );




    @Override
    public void start(Stage stage) throws IOException {
        TilePane root = new TilePane();
        double gap = 20;
        double stageWidth = 600;
        double stageFullWidth = stageWidth + gap * 3;
        double stageHeight = 300;
        double stageFullHeight = stageHeight + gap * 4;
        root.setAlignment(Pos.CENTER);
        root.setHgap(gap);
        root.setVgap(gap);
        root.setPrefColumns(2);
        root.setPrefRows(3);
        root.setMinWidth(300);
        root.setMaxWidth(300);
        root.setPrefWidth(300);

        Font fontBig = new Font(30);

        double buttonWidth = stageWidth / 2;
        double buttonHeight = stageHeight / 3;

        Button btnCustomers = new Button("Customers...");
        btnCustomers.setFont(fontBig);
        btnCustomers.setPrefWidth(buttonWidth);
        btnCustomers.setPrefHeight(buttonHeight);

        Button btnOccupancies = new Button("Occupancies...");
        btnOccupancies.setFont(fontBig);
        btnOccupancies.setPrefWidth(buttonWidth);
        btnOccupancies.setPrefHeight(buttonHeight);


        Button btnRooms = new Button("Rooms...");
        btnRooms.setFont(fontBig);
        btnRooms.setPrefWidth(buttonWidth);
        btnRooms.setPrefHeight(buttonHeight);


        Button btnPayments = new Button("Payments...");
        btnPayments.setFont(fontBig);
        btnPayments.setPrefWidth(buttonWidth);
        btnPayments.setPrefHeight(buttonHeight);


        Button btnEmployees = new Button("Employees...");
        btnEmployees.setFont(fontBig);
        btnEmployees.setPrefWidth(buttonWidth);
        btnEmployees.setPrefHeight(buttonHeight);

        Button btnClose = new Button("Close");
        btnClose.setFont(fontBig);
        btnClose.setPrefWidth(buttonWidth);
        btnClose.setPrefHeight(buttonHeight);


        root.getChildren().addAll(btnCustomers, btnOccupancies, btnRooms, btnPayments, btnEmployees, btnClose);

        Stage stageCustomerEditor = createCustomerEditorWindow();

        Stage stageCustomers = createCustomersWindow(stageCustomerEditor);

        btnCustomers.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        stageCustomers.show();
                    }
                }
        );


        Stage stageEmployeeEditor = createEmployeeEditorWindow();

        Stage stageEmployeeRecords = createEmployeeRecordsWindow(stageEmployeeEditor);

        Stage stageRoomsEditor = createRoomEditor();

        Stage stageRoomWindow = createRoomsWindow(stageRoomsEditor);

        Stage stageOccupancyEditor = createOccupancyEditor();

        Stage stageOccupancyWindow = createOccupancyWindow(stageOccupancyEditor);

        Stage stagePaymentEditor =createPaymentEditor();

        Stage stagePaymentWindow = createPaymentWindow(stagePaymentEditor);

        btnPayments.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stagePaymentWindow.show();
            }
        });

        btnRooms.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stageRoomWindow.show();
            }
        });

        btnEmployees.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stageEmployeeRecords.show();
            }
        });


        btnOccupancies.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stageOccupancyWindow.show();
            }
        });

        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });


        Scene scene = new Scene(root, stageFullWidth, stageFullHeight);
        stage.setTitle("Ceil Inn");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private Double createSubTotal(Double amtCharged, Double phoneUse){
        Double subTotal = Double.sum(amtCharged.doubleValue(),phoneUse.doubleValue());
        return subTotal;
    }

    private Double createTaxAmount(Double taxRate, Double subTotal){
        Double taxAmount = Double.valueOf(subTotal.doubleValue() / taxRate.doubleValue());
        return taxAmount;
    }

    private Double createAmountPaid(Double taxAmount, Double subTotal){
        Double AmountPaid = Double.sum(subTotal.doubleValue(), taxAmount.doubleValue());
        return AmountPaid;
    }

    private void addTableColumn(String text, double width, TableView Parent, PropertyValueFactory factory, String Id) {
        TableColumn column = new TableColumn(text);
        column.setStyle("-fx-font: 7 Roboto");
        column.setPrefWidth(width);
        column.setCellValueFactory(factory);
        Parent.getColumns().add(column);
        column.setId(Id);
    }

    private void addTableColumn(String text, double width, TableView Parent, PropertyValueFactory factory, String Id, Boolean a) {
        TableColumn column = new TableColumn(text);
        column.setPrefWidth(width);
        column.setCellValueFactory(factory);
        if (a) {
            column.setStyle("-fx-alignment: CENTER-RIGHT;" +
                    "-fx-font-weight: normal;" +
                    "-fx-font: 7 Roboto");


        }
        Parent.getColumns().add(column);
        column.setId(Id);
    }

    private Label createLabel(String text, String id) {
        Label label = new Label(text);
        label.setId(id);
        return label;
    }

    private TextField createTextField(double width, String id) {
        TextField textField = new TextField();
        textField.setPrefWidth(width);
        textField.setMaxWidth(width);
        textField.setId(id);
        return textField;
    }

    private Stage createCustomersWindow(Stage stageCustomerEditor) {
        //customer
        VBox rootCustomers = new VBox();
        TableView lvwCustomers = new TableView();

        PropertyValueFactory facAccountNumber = new PropertyValueFactory<Customer, String>("AccountNumber");
        addTableColumn("Account #", 70, lvwCustomers, facAccountNumber, "colAccountNumber");
        PropertyValueFactory facFirstName = new PropertyValueFactory<Customer, String>("FirstName");
        addTableColumn("First Name", 65, lvwCustomers, facFirstName, "colFirstName");
        PropertyValueFactory facLastName = new PropertyValueFactory<Customer, String>("FirstName");
        addTableColumn("Last Name", 65, lvwCustomers, facLastName, "colLastName");
        PropertyValueFactory facPhoneNumber = new PropertyValueFactory<Customer, String>("FirstName");
        addTableColumn("Phone Number", 65, lvwCustomers, facPhoneNumber, "colPhoneNumber");
        PropertyValueFactory facEmergencyName = new PropertyValueFactory<Customer, String>("FirstName");
        addTableColumn("Emergency Name", 65, lvwCustomers, facEmergencyName, "colEmergencyName");
        PropertyValueFactory facEmergencyPhone = new PropertyValueFactory<Customer, String>("FirstName");
        addTableColumn("Emergency Number", 65, lvwCustomers, facEmergencyPhone, "colEmergencyNumber");


        lvwCustomers.setItems(dataCustomers);

        HBox conButtons = new HBox();
        Button btnNewCustomer = new Button("New Customer...");
        Button btnCloseCustomer = new Button("Close");


        conButtons.getChildren().addAll(btnNewCustomer, btnCloseCustomer);
        conButtons.setAlignment(Pos.CENTER_RIGHT);
        conButtons.setSpacing(10);

        rootCustomers.getChildren().addAll(lvwCustomers, conButtons);
        rootCustomers.setPadding(new Insets(20));
        rootCustomers.setSpacing(20);
        Scene sceneCustomers = new Scene(rootCustomers);

        Stage stageCustomers = new Stage();
        stageCustomers.setScene(sceneCustomers);
        stageCustomers.setTitle("Ceil Inn - Customer Records");

        btnCloseCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stageCustomers.close();
            }
        });

        btnNewCustomer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stageCustomerEditor.show();
            }
        });
        stageCustomers.setResizable(false);

        return stageCustomers;
    }

    private Stage createCustomerEditorWindow() {
        Stage stageCustomerEditor = new Stage();

        GridPane rootCustomerEditor = new GridPane();
        Scene sceneCustomerEditor = new Scene(rootCustomerEditor);
        stageCustomerEditor.setScene(sceneCustomerEditor);
        stageCustomerEditor.setTitle("Ceil inn - Customer Editor");
        Label lblAccountNumber = createLabel("Account Number", "lblAccountNumber");
        rootCustomerEditor.add(lblAccountNumber, 0, 0);
        Label lblFirstName = createLabel("First Name", "lblFirstName");
        rootCustomerEditor.add(lblFirstName, 0, 1);
        Label lblLastName = createLabel("Last Name", "lblLastName");
        rootCustomerEditor.add(lblLastName, 0, 2);
        Label lblPhoneNumber = createLabel("Phone Number", "lblPhoneNumber");
        rootCustomerEditor.add(lblPhoneNumber, 0, 3);
        Label lblEmergencyName = createLabel("Emergency Name", "lblEmergencyName");
        rootCustomerEditor.add(lblEmergencyName, 0, 4);
        Label lblEmergencyPhone = createLabel("Emergency Phone", "lblEmergencyPhone");
        rootCustomerEditor.add(lblEmergencyPhone, 0, 5);


        ArrayList<TextField> list = new ArrayList();
        TextField txtAccountNumber = createTextField(100, "txtAccountNumber");
        rootCustomerEditor.add(txtAccountNumber, 1, 0);
        list.add(txtAccountNumber);
        TextField txtFirstName = createTextField(120, "txtFirstName");
        rootCustomerEditor.add(txtFirstName, 1, 1);
        list.add(txtFirstName);
        TextField txtLastName = createTextField(120, "txtLastName");
        rootCustomerEditor.add(txtLastName, 1, 2);
        list.add(txtLastName);
        TextField txtPhoneNumber = createTextField(120, "txtPhoneNumber");
        rootCustomerEditor.add(txtPhoneNumber, 1, 3);
        list.add(txtPhoneNumber);
        TextField txtEmergencyName = createTextField(200, "txtEmergencyName");
        rootCustomerEditor.add(txtEmergencyName, 1, 4);
        list.add(txtEmergencyName);
        TextField txtEmergencyPhone = createTextField(200, "txtEmergencyPhone");
        rootCustomerEditor.add(txtEmergencyPhone, 1, 5);
        list.add(txtEmergencyPhone);

        HBox conButtons = new HBox();

        Button btnOK = new Button("OK");
        Button btnCancel = new Button("Cancel");
        conButtons.getChildren().addAll(btnOK, btnCancel);
        conButtons.setAlignment(Pos.CENTER_RIGHT);
        conButtons.setSpacing(10);
        rootCustomerEditor.add(conButtons, 1, 6);


        rootCustomerEditor.setHgap(10);
        rootCustomerEditor.setVgap(10);
        rootCustomerEditor.setPadding(new Insets(20));

        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                list.forEach(txt -> {
                    if (txt.getText() == null) {
                        return;
                    }
                });

                dataCustomers.add(
                        new Customer(
                                txtAccountNumber.getText(),
                                txtFirstName.getText(),
                                txtLastName.getText(),
                                txtPhoneNumber.getText(),
                                txtEmergencyName.getText(),
                                txtEmergencyPhone.getText()
                        )
                );

                list.forEach(txt -> {
                    txt.setText("");
                });
            }
        });

        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stageCustomerEditor.close();
            }
        });

        stageCustomerEditor.setResizable(false);
        return stageCustomerEditor;
    }

    private Stage createEmployeeEditorWindow() {
        GridPane root = new GridPane();


        Label lblEmployeeNumber = createLabel("Employee Number", "lblEmployeeNumber");
        root.add(lblEmployeeNumber, 0, 0);
        Label lblFirstName = createLabel("First Name", "lblFirstName");
        root.add(lblFirstName, 0, 1);
        Label lblLastName = createLabel("Last Name", "lblLastName");
        root.add(lblLastName, 0, 2);
        Label lblTitle = createLabel("Title", "lblTitle");
        root.add(lblTitle, 0, 3);
        ArrayList<TextField> list = new ArrayList();
        TextField txtEmployeeNumber = createTextField(100, "txtEmployeeNumber");
        list.add(txtEmployeeNumber);
        TextField txtFirstName = createTextField(120, "txtFirstName");
        list.add(txtFirstName);
        TextField txtLastName = createTextField(120, "txtLastName");
        list.add(txtLastName);
        TextField txtTitle = createTextField(200, "txtTitle");
        list.add(txtTitle);

        root.add(txtEmployeeNumber, 1, 0);
        root.add(txtFirstName, 1, 1);
        root.add(txtLastName, 1, 2);
        root.add(txtTitle, 1, 3);

        HBox conButton = new HBox();
        conButton.setSpacing(10);
        Button btnOK = new Button("OK");
        Button btnCancel = new Button("Cancel");
        conButton.getChildren().addAll(btnOK, btnCancel);

        conButton.setAlignment(Pos.CENTER_RIGHT);
        root.add(conButton, 1, 4);


        Scene sceneEmployeeEditor = new Scene(root);
        Stage stageEmployeeEditor = new Stage();
        stageEmployeeEditor.setScene(sceneEmployeeEditor);
        stageEmployeeEditor.setTitle("Ceil Inn - Employee Editor");

        root.setPadding(new Insets(20));
        root.setVgap(10);
        root.setHgap(10);
        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                list.forEach(txt -> {
                    if (txt.getText() == null) {
                        return;
                    }
                });
                dataEmployees.add(new Employee(txtEmployeeNumber.getText(), txtFirstName.getText(), txtLastName.getText(), txtTitle.getText()));
                list.forEach(txt -> {
                    txt.setText("");
                });
            }
        });
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stageEmployeeEditor.close();
            }
        });
        stageEmployeeEditor.setResizable(false);
        return stageEmployeeEditor;
    }

    private Stage createEmployeeRecordsWindow(Stage employeeEditor) {

        VBox root = new VBox();

        root.setSpacing(10);
        root.setPadding(new Insets(20));

        TableView lvwEmployees = new TableView();

        PropertyValueFactory facAccountNumber = new PropertyValueFactory<Employee, String>("EmployeeNumber");
        addTableColumn("Account #", 70, lvwEmployees, facAccountNumber, "colEmployeeNumber");
        PropertyValueFactory facFirstName = new PropertyValueFactory<Employee, String>("FirstName");
        addTableColumn("First Name", 80, lvwEmployees, facFirstName, "colFirstName");
        PropertyValueFactory facLastName = new PropertyValueFactory<Employee, String>("LastName");
        addTableColumn("Last Name", 80, lvwEmployees, facLastName, "colLastName");
        PropertyValueFactory facTitle = new PropertyValueFactory<Employee, String>("Title");
        addTableColumn("Title", 120, lvwEmployees, facTitle, "colTitle");
        lvwEmployees.setItems(dataEmployees);


        HBox conButtons = new HBox();
        Button btnNewEmployee = new Button("New Employee...");
        Button btnClose = new Button("Close");

        Scene sceneEmployeeRecords = new Scene(root);
        Stage stageEmployeeRecords = new Stage();
        stageEmployeeRecords.setScene(sceneEmployeeRecords);

        btnNewEmployee.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                employeeEditor.show();
            }
        });
        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stageEmployeeRecords.close();
            }
        });
        conButtons.setAlignment(Pos.CENTER_RIGHT);
        conButtons.setSpacing(10);
        conButtons.getChildren().addAll(btnNewEmployee, btnClose);
        root.getChildren().addAll(lvwEmployees, conButtons);
        stageEmployeeRecords.setResizable(false);
        return stageEmployeeRecords;
    }

    private Stage createRoomEditor() {

        GridPane rootRoomEditor = new GridPane();

        rootRoomEditor.setVgap(10);
        rootRoomEditor.setHgap(10);
        rootRoomEditor.setPadding(new Insets(20));
        Label lblRoomNumber = createLabel("Room Number:", "lblRoomNumber");
        rootRoomEditor.add(lblRoomNumber, 0, 0);
        Label lblRoomTypes = createLabel("Room Type:", "lblRoomTypes");
        rootRoomEditor.add(lblRoomTypes, 0, 1);
        Label lblBedTypes = createLabel("Bed Type:", "lblBedTypes");
        rootRoomEditor.add(lblBedTypes, 0, 2);
        Label lblRate = createLabel("Rate:", "lblRate");
        rootRoomEditor.add(lblRate, 0, 3);
        Label lblStatus = createLabel("Status:", "lblStatus");
        rootRoomEditor.add(lblStatus, 0, 4);


        ArrayList<Control> list = new ArrayList();
        TextField txtRoomNumber = createTextField(40, "txtRoomNumber");
        rootRoomEditor.add(txtRoomNumber, 1, 0);
        list.add(txtRoomNumber);

        ObservableList<String> listRoomTypes = FXCollections.observableArrayList("Bedroom", "Conference Room", "Other");
        ComboBox cbxRoomTypes = new ComboBox(listRoomTypes);
        rootRoomEditor.add(cbxRoomTypes, 1, 1);
        cbxRoomTypes.setPrefWidth(150);
        list.add(cbxRoomTypes);
        ObservableList<String> listBedTypes = FXCollections.observableArrayList("King", "Queen");
        ComboBox cbxBedTypes = new ComboBox(listBedTypes);
        rootRoomEditor.add(cbxBedTypes, 1, 2);
        cbxBedTypes.setPrefWidth(200);
        list.add(cbxBedTypes);

        TextField txtRate = createTextField(50, "txtRate");
        rootRoomEditor.add(txtRate, 1, 3);
        list.add(txtRate);


        ObservableList<String> listStatuses = FXCollections.observableArrayList("Other", "Available", "Occupied");
        ComboBox cbxOccupanciesStatus = new ComboBox(listStatuses);
        rootRoomEditor.add(cbxOccupanciesStatus, 1, 4);
        list.add(cbxOccupanciesStatus);


        HBox conButton = new HBox();
        conButton.setSpacing(10);
        Button btnOK = new Button("OK");
        Button btnCancel = new Button("Cancel");
        conButton.getChildren().addAll(btnOK, btnCancel);

        conButton.setAlignment(Pos.CENTER_RIGHT);
        rootRoomEditor.add(conButton, 1, 5);

        Scene sceneRoomEditor = new Scene(rootRoomEditor);
        Stage stageRoomEditor = new Stage();
        stageRoomEditor.setTitle("Ceil Inn - Room Editor");
        stageRoomEditor.setScene(sceneRoomEditor);

        btnOK.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                list.forEach(control -> {
                    String text;
                    if (control instanceof TextField) {
                        text = ((TextField) control).getText();

                    } else {
                        text = ((ComboBox) control).getValue().toString();
                    }

                    if (text == null || text == "") {
                        return;
                    }
                });

                try {
                    Double rate = Double.parseDouble(txtRate.getText());
                    Double.parseDouble(txtRate.getText());
                    dataRooms.add(new Room(txtRoomNumber.getText(), cbxRoomTypes.getValue().toString(), cbxBedTypes.getValue().toString(), rate, cbxOccupanciesStatus.getValue().toString()));


                    list.forEach(control -> {
                        String text;
                        if (control instanceof TextField) {
                            ((TextField) control).setText("");

                        } else {
                            ((ComboBox) control).setValue("");
                        }
                    });
                } catch (NumberFormatException e) {

                    System.err.println("Not Parse-able String");
                    return;


                }


//                dataEmployees.add(new Employee(txtEmployeeNumber.getText(), txtFirstName.getText(), txtLastName.getText(), txtTitle.getText()));
//                list.forEach(txt -> {
//                    txt.setText("");
//                });
            }
        });
        btnCancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stageRoomEditor.close();
            }
        });
        stageRoomEditor.setResizable(false);

        return stageRoomEditor;
    }

    private Stage createRoomsWindow(Stage stageRoomEditor) {
        VBox rootRoomsWindow = new VBox();

        TableView lvwRooms = new TableView();

        PropertyValueFactory facRoomNumber = new PropertyValueFactory<Room, String>("RoomNumber");
        addTableColumn("Room #", 80, lvwRooms, facRoomNumber, "colRoomNumber");
        PropertyValueFactory facRoomType = new PropertyValueFactory<Room, String>("RoomType");
        addTableColumn("Room Type", 100, lvwRooms, facRoomType, "colRoomType");
        PropertyValueFactory facBedType = new PropertyValueFactory<Room, String>("BedType");
        addTableColumn("Bed Type", 80, lvwRooms, facBedType, "colBedType");
        PropertyValueFactory facRate = new PropertyValueFactory<Room, Double>("Rate");
        addTableColumn("Rate", 60, lvwRooms, facRate, "colRate");
        PropertyValueFactory facStatus = new PropertyValueFactory<Room, String>("Status");
        addTableColumn("Status", 65, lvwRooms, facStatus, "colStatus");

        lvwRooms.setItems(dataRooms);
        HBox conButtons = new HBox();
        Button btnNewRoom = new Button("New Room...");
        Button btnClose = new Button("Close");


        Scene sceneRoomsWindow = new Scene(rootRoomsWindow);
        Stage stageRoomsWindow = new Stage();

        btnNewRoom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stageRoomEditor.show();
            }
        });
        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stageRoomsWindow.close();
            }
        });
        conButtons.setAlignment(Pos.CENTER_RIGHT);
        conButtons.setSpacing(10);
        conButtons.getChildren().addAll(btnNewRoom, btnClose);
        rootRoomsWindow.setSpacing(10);
        rootRoomsWindow.setPadding(new Insets(20));

        rootRoomsWindow.getChildren().addAll(lvwRooms, conButtons);
        stageRoomsWindow.setScene(sceneRoomsWindow);
        stageRoomsWindow.setResizable(false);
        return stageRoomsWindow;
    }


    private Stage createOccupancyEditor() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("OccupancyEditor.fxml"));

        Parent rootOccupancyEditor = fxmlLoader.load();
        OccupancyEditorController controller =
                fxmlLoader.<OccupancyEditorController>getController();

        controller.setData(dataOccupancies);

        Scene sceneOccupancyEditor = new Scene(rootOccupancyEditor);
        Stage stageOccupancyEditor = new Stage();
        stageOccupancyEditor.setScene(sceneOccupancyEditor);
        stageOccupancyEditor.setTitle("Ceil Inn - Occupancy Editor");
        stageOccupancyEditor.setResizable(false);
        return stageOccupancyEditor;
    }

    private Stage createOccupancyWindow(Stage stageOccupancyEditor) throws IOException {

        VBox rootOccupancyWindow = new VBox();

        TableView lvwOccupancies = new TableView();

        PropertyValueFactory facOccupancyNumber = new PropertyValueFactory<Occupancy, String>("OccupancyNumber");
        addTableColumn("Occupancy #", 80, lvwOccupancies, facOccupancyNumber, "colOccupancyNumber");

        PropertyValueFactory facProcessedBy = new PropertyValueFactory<Occupancy, String>("ProcessedBy");
        addTableColumn("Processed By", 140, lvwOccupancies, facProcessedBy, "ProcessedBy");

        PropertyValueFactory facDateOccupied = new PropertyValueFactory<Occupancy, LocalDate>("DateOccupied");
        addTableColumn("Date Occupied", 150, lvwOccupancies, facDateOccupied, "colOccupancyNumber");

        PropertyValueFactory facProcessedFor = new PropertyValueFactory<Occupancy, String>("ProcessedFor");
        addTableColumn("Processed For", 140, lvwOccupancies, facProcessedFor, "ProcessedFor");

        PropertyValueFactory facRoomOccupied = new PropertyValueFactory<Occupancy, String>("RoomOccupied");
        addTableColumn("Room Occupied", 180, lvwOccupancies, facRoomOccupied, "RoomOccupied");

        PropertyValueFactory facRateApplied = new PropertyValueFactory<Occupancy, Double>("RateApplied");
        addTableColumn("Rate Applied", 80, lvwOccupancies, facRateApplied, "RateApplied", true);

        PropertyValueFactory facPhoneUse = new PropertyValueFactory<Occupancy, Double>("PhoneUse");
        addTableColumn("Phone Use", 65, lvwOccupancies, facPhoneUse, "PhoneUse", true);


        Stage stageOccupancyWindow = new Stage();


        lvwOccupancies.setItems(dataOccupancies);
        HBox conButtons = new HBox();
        Button btnNewRoom = new Button("New Room...");
        Button btnClose = new Button("Close");

        Scene sceneRoomsWindow = new Scene(rootOccupancyWindow);
        Stage stageRoomsWindow = new Stage();


        btnNewRoom.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stageOccupancyEditor.show();
            }
        });
        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stageOccupancyWindow.close();
            }
        });
        conButtons.setAlignment(Pos.CENTER_RIGHT);
        conButtons.setSpacing(10);
        conButtons.getChildren().addAll(btnNewRoom, btnClose);
        rootOccupancyWindow.setSpacing(10);
        rootOccupancyWindow.setPadding(new Insets(20));

        rootOccupancyWindow.getChildren().addAll(lvwOccupancies, conButtons);

        stageOccupancyWindow.setScene(sceneRoomsWindow);
        stageOccupancyWindow.setResizable(false);
        return stageOccupancyWindow;
    }

    private Stage createPaymentEditor() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PaymentEditor.fxml"));

        Parent rootPaymentEditor = fxmlLoader.load();
        PaymentEditorController controller =
                fxmlLoader.<PaymentEditorController>getController();


        System.out.println(controller);
        controller.Init(dataPayments);

        Scene scenePaymentEditor = new Scene(rootPaymentEditor);
        Stage stagePaymentEditor = new Stage();
        stagePaymentEditor.setScene(scenePaymentEditor);
        stagePaymentEditor.setTitle("Ceil Inn - Occupancy Editor");
        stagePaymentEditor.setResizable(false);

        return stagePaymentEditor;
    }

    private Stage createPaymentWindow(Stage stagePaymentEditor) throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PaymentWindow.fxml"));

        Parent rootOccupancyWindow = fxmlLoader.load();
        PaymentWindowController controller =
                fxmlLoader.<PaymentWindowController>getController();


        System.out.println(controller);
        controller.Init(dataPayments, stagePaymentEditor);

        Scene sceneOccupancyWindow = new Scene(rootOccupancyWindow);
        Stage stageOccupancyWindow = new Stage();
        stageOccupancyWindow.setScene(sceneOccupancyWindow);
        stageOccupancyWindow.setTitle("Ceil Inn - Occupancies");
        stageOccupancyWindow.setResizable(false);




        return stageOccupancyWindow;


    }


    public static void main(String[] args) {
        launch();
    }
}