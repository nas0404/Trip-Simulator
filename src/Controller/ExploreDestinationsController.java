package controller.Destinations;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import model.Destination;
import model.Destinations;
import model.Exceptions.ErrorModel;
import model.Administrator;
import model.Agency;

import java.io.IOException;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;


public class ExploreDestinationsController extends Controller<Agency> {
@FXML Button ViewAllDestinations;
@FXML Button FilterDestinations;
@FXML Button AddDestination;
@FXML Button RemoveDestination;
@FXML Button Exit;
@FXML private Label nameLabel;






public void initialize(){
    ViewAllDestinations.setOnAction(event -> handleViewDestinations());
    FilterDestinations.setOnAction(event -> handleFilterDestinations());
    AddDestination.setOnAction(event -> {
        try {
            handleAddDestination();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    });
    RemoveDestination.setOnAction(event -> handleRemoveDestination());
    Exit.setOnAction(event -> handleExit());
    Administrator logAdministrator = model.getLoggedInUser();
        if(logAdministrator != null){
            nameLabel.setText(logAdministrator.getName());
        }
}
@FXML public void handleViewDestinations(){
        Stage destStage = new Stage();
            destStage.getIcons().add(new Image("/image/destinations_icon.png"));
    try {
            ViewLoader.showStage(model.getDestinations(), "/view/Destinations/DisplayDestinationsView.fxml", "Display Destionations", destStage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
}
@FXML public void handleFilterDestinations(){
    Stage destStage = new Stage();
            destStage.getIcons().add(new Image("/image/destinations_icon.png"));   
    try {
            ViewLoader.showStage(model.getDestinations(), "/view/Destinations/DisplayFilteredDestinationsView.fxml", "Display Filtered Destination", destStage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
}
@FXML public void handleAddDestination() throws IOException{
    Stage destStage = new Stage();
            destStage.getIcons().add(new Image("/image/destinations_icon.png"));   
    try {
            ViewLoader.showStage(model.getDestinations(), "/view/Destinations/AddDestinationView.fxml", "Add Destination", destStage);
        } catch(IOException e){
            e.printStackTrace();
        }  
}
@FXML public void handleRemoveDestination(){
    Stage destStage = new Stage();
            destStage.getIcons().add(new Image("/image/destinations_icon.png"));   
    try {
            ViewLoader.showStage(model.getDestinations(), "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination", destStage);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
}
@FXML public void handleExit(){
    stage.close();
}
}
