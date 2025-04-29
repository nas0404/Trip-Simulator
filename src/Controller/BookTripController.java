package controller.Trip;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import model.Destination;
import model.Destinations;
import model.Trip;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.InsufficientDestinationsException;

import java.io.IOException;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;


public class BookTripController extends Controller<Trip> {

@FXML Button AddDestination;
@FXML Button RemoveDestination;
@FXML Button AddConnectingFlight;
@FXML Button ViewTrip;
@FXML Button Exit;



@FXML
public void initialize(){
    ViewTrip.setOnAction(event -> viewTrip());
    AddConnectingFlight.setOnAction(event -> handleAddConnectingFlights());
    AddDestination.setOnAction(event -> handleAddDestination());
    RemoveDestination.setOnAction(event -> handleRemoveDestination());
    Exit.setOnAction(event -> handleExit());
}
public Trip getTripModel(){
    return this.model;
}
@FXML public void handleAddDestination(){
    try {
        Stage deststage = new Stage();
            deststage.getIcons().add(new Image("/image/destinations_icon.png"));

        ViewLoader.showStage(getTripModel().getDestinations(), "/view/Destinations/AddDestinationView.fxml", "Add Destination", deststage);
    } 
    
    catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
@FXML public void handleRemoveDestination(){
    try {
                Stage deststage = new Stage();
            deststage.getIcons().add(new Image("/image/destinations_icon.png"));
        ViewLoader.showStage(getTripModel().getDestinations(), "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination", deststage);
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
@FXML public void handleAddConnectingFlights(){
         try {
        getTripModel().addConnectingFlights();
        
    } catch (InsufficientDestinationsException e) {
        // TODO Auto-generated catch block
        ViewLoader.showErrorWindow(new ErrorModel(e, "Insufficient flights"));
    }
    catch(DuplicateItemException e){
        ViewLoader.showErrorWindow((new ErrorModel(e, "This destination is duplicated")));
    }
    }
   
       
    

@FXML public void viewTrip(){
        try {
            Stage tripstage = new Stage();
            tripstage.getIcons().add(new Image("/image/trip_icon.png"));
            ViewLoader.showStage(getTripModel(), "/view/Trip/DisplayTripView.fxml", "Destination", tripstage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
    }
@FXML public void handleExit(){
    stage.close();
}
}
