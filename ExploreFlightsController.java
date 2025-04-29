package controller.Flights;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import model.Flights;
import model.Exceptions.ErrorModel;
import model.Exceptions.InvalidCredentialsException;
import model.Administrator;
import model.Agency;
import model.Flight;

import java.io.IOException;

import com.sun.media.sound.InvalidFormatException;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;


public class ExploreFlightsController extends Controller<Agency> {

@FXML Button ViewAllFlights;
@FXML Button FilterFlights;
@FXML Button AddFlight;
@FXML Button RemoveFlight;
@FXML Button Exit;
@FXML Label nameLabel;




@FXML
public void initialize(){
    ViewAllFlights.setOnAction(event -> handleViewFlights());
     FilterFlights.setOnAction(event -> handleFilterFlights());
      AddFlight.setOnAction(event -> {

            handleAddFlight();
        
    });
       RemoveFlight.setOnAction(event -> handleRemoveFlight());
       Exit.setOnAction((event -> handleExit()));
       Administrator logAdministrator = model.getLoggedInUser();
        if(logAdministrator != null){
            nameLabel.setText(logAdministrator.getName());
        }
}
@FXML public void handleViewFlights(){
    try {
        Stage Flistage = new Stage();
             Flistage.getIcons().add(new Image("/image/flights_icon.png"));
            ViewLoader.showStage(model.getFlights(),"/view/Flights/DisplayFlightsView.fxml", "Display Flights",  Flistage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

}
@FXML public void handleFilterFlights(){
    Stage fliStage = new Stage();
            fliStage.getIcons().add(new Image("/image/flights_icon.png"));    
    try {
            ViewLoader.showStage(model.getFlights(), "/view/Flights/DisplayFilteredFlightsView.fxml", "Display Filtered Flights", fliStage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
}
@FXML public void handleAddFlight(){
    try{
    Stage fliStage = new Stage();
            fliStage.getIcons().add(new Image("/image/flights_icon.png"));
        
            ViewLoader.showStage(model.getFlights(), "/view/Flights/AddFlightView.fxml", "Add Flights", fliStage);

        } 
       catch (IOException e) {
            e.printStackTrace();
        } 

    }

@FXML public void handleRemoveFlight(){
    try{
       Stage fliStage = new Stage();
            fliStage.getIcons().add(new Image("/image/flights_icon.png"));
    
            ViewLoader.showStage(model.getFlights(), "/view/Flights/RemoveFlightView.fxml", "Remove Flights", fliStage);
        } catch (IOException e) {
            e.printStackTrace();
        } 
}
@FXML public void handleExit(){
    stage.close();
}
}

