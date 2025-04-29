package controller;

import java.io.IOException;


import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import model.Administrator;
import model.Agency;
import model.Destination;
import model.Destinations;
import model.Flight;
import model.Trip;
import model.Flights;
public class AgencyController extends Controller<Agency> {
 public Flights flights;
 public Agency agency;
 public Destinations dest;
 public Agency getAdministrator(){
    return this.model;
}

    @FXML Button ExploreFlights;
    @FXML Button ExploreDestinations;
    @FXML Button BookTrip;
    @FXML Button Exit;
    @FXML private Label nameLabel;

    public void initialize(){
        ExploreFlights.setOnAction(event -> {
            try {
                ExploreFlights();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
         ExploreDestinations.setOnAction(event -> {
            try {
                ExploreDestinations();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        BookTrip.setOnAction(event -> {
            try {
                handleBookTrip();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        Exit.setOnAction(event -> handleExit());
         
  
        Administrator logAdministrator = model.getLoggedInUser();
        if(logAdministrator != null){
            nameLabel.setText(logAdministrator.getName());
        }
    }
    
        
    
    // public void ExploreDestinations() throws IOException{
    //     ViewLoader.showStage(flight, "/view/ExploreFlightsView.fxml", "Flight", new Stage());
    // }
   @FXML public void ExploreFlights() throws IOException{
    // Flight flight = new Flight(null, 0, null, null, 0 );
        try {
            Stage fliStage = new Stage();
            fliStage.getIcons().add(new Image("/image/flights_icon.png"));
            ViewLoader.showStage(getAdministrator(), "/view/Flights/ExploreFlightsView.fxml", "Explore Flights", fliStage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML public void ExploreDestinations() throws IOException{
          Stage destStage = new Stage();
            destStage.getIcons().add(new Image("/image/destinations_icon.png"));
        try {
            ViewLoader.showStage(getAdministrator(), "/view/Destinations/ExploreDestinationsView.fxml", "Explore Destinations", destStage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    @FXML public void handleBookTrip()throws IOException{
        Stage tripStage = new Stage();
            tripStage.getIcons().add(new Image("/image/trip_icon.png"));

        try {
            ViewLoader.showStage(new Trip(model), "/view/Trip/BookTripView.fxml", "Book a Trip",  tripStage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }
    public void handleExit(){
        Platform.exit();
    }
}




