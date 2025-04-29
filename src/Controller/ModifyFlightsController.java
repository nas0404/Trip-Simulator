package controller.Flights;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import model.Destination;
import model.Flight;
import model.Flights;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.ItemNotFoundException;

public class ModifyFlightsController extends Controller<Flights>{
    @FXML TextField Airlinetf;
    @FXML TextField FlightNumbertf;
    @FXML TextField Takeofftf;
    @FXML TextField Landingtf; 
    @FXML TextField Costtf;
    @FXML Button AddFlight;
    @FXML Button RemoveFlight;
    @FXML Button Exit;
    
    

    public Flights getFlightsModel(){
        return this.model;
    }
    
   @FXML  private void initialize(){

         if(AddFlight != null){
             AddFlight.setOnAction(event -> handleAddFlight());
            BooleanBinding checkbuttonstate = Airlinetf.textProperty().isEmpty().or(FlightNumbertf.textProperty().isEmpty()).or(Takeofftf.textProperty().isEmpty()).or(Landingtf.textProperty().isEmpty()).or(Costtf.textProperty().isEmpty());  
            AddFlight.disableProperty().bind(checkbuttonstate);
         }
       
       if(RemoveFlight != null){
        RemoveFlight.setOnAction(event -> handleRemoveFlight());
            BooleanBinding checkbuttonstate = (Takeofftf.textProperty().isEmpty()).or(Landingtf.textProperty().isEmpty());  
            RemoveFlight.disableProperty().bind(checkbuttonstate);
         }
        Exit.setOnAction(event -> handleExit());

        }
    
   @FXML private void handleAddFlight(){
   
    try {
    String airline = Airlinetf.getText();
        String flightno = FlightNumbertf.getText();
        int flightnum = Integer.parseInt(flightno);
         String takeoff = Takeofftf.getText();
          String landing = Landingtf.getText();
           String cost = Costtf.getText();
           double costdouble = Double.parseDouble(cost);
        Flight newflight = new Flight(airline, flightnum, takeoff, landing, costdouble);
  
            getFlightsModel().addFlight(newflight);
            stage.close();
        } catch (DuplicateItemException e) {
            ViewLoader.showErrorWindow(new ErrorModel(e, "This flight already exists"));
        }
        catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            ViewLoader.showErrorWindow(new ErrorModel(e, "Invalid Format"));

        
    }
}
    @FXML private void handleRemoveFlight(){
        try{
        String takeoff = Takeofftf.getText();
          String landing = Landingtf.getText();
        

           
               
            Flight flight =  getFlightsModel().getFlight(takeoff, landing);
            getFlightsModel().removeFlight(flight);
            stage.close();
                return;
                //System.out.print( getDestinationsModel().toString());
            } catch (ItemNotFoundException e) {
                // TODO Auto-generated catch block
                 ViewLoader.showErrorWindow(new ErrorModel(e, "This flight doesn't exist"));
            }
            
        }
       @FXML public void  handleExit(){
        stage.close();
       }

   @FXML  public boolean checkRemoveButton(){
        if(Takeofftf.getText().isEmpty()|| Landingtf.getText().isEmpty() ){
            return true;
        }
        else{
           return false;
        }
    }
     
    }
    
 



