package controller.Flights;
import model.Agency;
import model.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.text.*;
import javafx.stage.Stage;
import au.edu.uts.ap.javafx.Controller;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import au.edu.uts.ap.javafx.ViewLoader;

import java.io.IOException;
import java.util.List;

import javax.management.openmbean.OpenMBeanConstructorInfoSupport;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import model.Flights;
import model.Flight;

public class DisplayFlightsController extends Controller<Flights> {
   @FXML private TableView<Flight> tableView;
   @FXML private TableColumn<Flight, String> airline;
   @FXML private TableColumn<Flight, String> flightno;
   @FXML private TableColumn<Flight, String> takeoff;
   @FXML private TableColumn<Flight, String> landing;
   @FXML private TableColumn<Flight, String> cost;
@FXML private Label country;
@FXML private TextField countrytf;

public Flights getFlightsModel(){
    return model;
}
    // Initialize method that you can use to set up the ListView
    @FXML
    private void initialize() {

        
        // displayFlightsLV.setItems(model.getFlights());
        // displayFlightsLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

     airline.setCellValueFactory(cellData -> cellData.getValue().airlineProperty());
     flightno.setCellValueFactory(cellData -> cellData.getValue().flightNumberProperty().asString());
     takeoff.setCellValueFactory(cellData -> cellData.getValue().takeoffProperty());
    landing.setCellValueFactory(cellData -> cellData.getValue().landingProperty());
     cost.setCellValueFactory(cellData -> cellData.getValue().costProperty().asString());
        
    //ObservableList<Flight> flightslist = FXCollections.observableArrayList(getFlightsModel().getFlights());
    tableView.setItems(getFlightsModel().getFlights());
    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
   if(countrytf != null){
    countrytf.textProperty().addListener((observable, oldValue, newValue)->{
        ObservableList<Flight> filtflight = getFlightsModel().getFilteredFlights(newValue);
        tableView.setItems(filtflight);
    });
    //getFlightsModel().getFlights() .addListener((x,y,z) -> tableView.setItems(getFlightsModel().getFlights()));
   } 
   
   Label placeholder = new Label();
   placeholder.setText("No Flights found");
   tableView.setPlaceholder(placeholder);
   
    }
     // Initialize method that you can use to set up the ListView

    @FXML public void handleClose(){
        stage.close();
    }
}


