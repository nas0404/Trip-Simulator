package controller.Destinations;
import model.Agency;
import model.Destination;
import model.Destinations;
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

public class DisplayDestinationsController extends Controller<Destinations> {
   @FXML private TableView<Destination> tableView;
   @FXML private TableColumn<Destination, String> countrytf;
   @FXML private TableColumn<Destination, String> nametf;
@FXML private TextField Destinationtf;

public Destinations getDestinationsModel(){
    return this.model;
}

    // Initialize method that you can use to set up the ListView
    @FXML
    private void initialize() {

        // displayFlightsLV.setItems(model.getFlights());
        // displayFlightsLV.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    countrytf.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
     nametf.setCellValueFactory(cellData -> cellData.getValue().nameProperty());

    tableView.setItems(getDestinationsModel().getDestinations());
    tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    if(Destinationtf !=null){
        Destinationtf.textProperty().addListener((observable, oldValue, newValue)->{
        ObservableList<Destination> filtDestinations = getDestinationsModel().getFilteredDestinations(newValue);
        tableView.setItems(filtDestinations);
    });
    }
    Label placeholder = new Label();
   placeholder.setText("No countries found");
   tableView.setPlaceholder(placeholder);
    }
    @FXML public void handleClose(){
        stage.close();
    }
}


