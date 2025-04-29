package controller.Trip;
import java.io.IOException;
import java.util.InputMismatchException;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Destination;
import model.Destinations;
import model.Flight;
import model.Flights;
import model.Itinery;
import model.Trip;
import model.Exceptions.ErrorModel;

public class DisplayTripController extends Controller<Trip>  {
@FXML ListView<Itinery> list;
@FXML Button viewIndividiualButton;
@FXML Button ExitButton;
public Flight flight;
private Trip getTripModel(){
    return this.model;
}

@FXML private void initialize(){
    
    ExitButton.setOnAction(event -> handleExit());
    viewIndividiualButton.setOnAction(event -> handleIndividiualButton());

       
    
    list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    list.setItems(getTripModel().getItinery());
    for(Itinery i: getTripModel().getItinery()) {
        System.out.println(i);
    }
     list.setCellFactory(ListView -> new ListCell<Itinery>(){
        
        protected void updateItem(Itinery item, boolean empty){
            super.updateItem(item, empty);
            if(empty || item == null){
                setText(null);
            }
            else{
                setText(item.toString());
            }
        }
    });
    Label placeholder = new Label();
   placeholder.setText("Nothing yet");
   list.setPlaceholder(placeholder);
    
   ObservableList<Itinery> itinery = getTripModel().getItinery();
        list.setItems(itinery);
}
       
@FXML public void handleIndividiualButton(){
           try{
            Destinations dest = getTripModel().getDestinations();
                                    Flights flight = getTripModel().getFlights();

            ObservableList<Itinery> selectedItineries = list.getSelectionModel().getSelectedItems();
            if(selectedItineries.isEmpty()){
              InputMismatchException madeException = new InputMismatchException("You've selected nothing");
                ErrorModel error = new ErrorModel(madeException, madeException.getMessage());
                ViewLoader.showErrorWindow(error);
            }
                   
            for(Itinery items : selectedItineries){
                
                // if (1dest && destinationModelIsSelected) {
                //     Exception customException = new Exception("BothModelSelectionException");
                //     ErrorModel error = new ErrorModel(customException, "Please select either a flight or a destination, not both.");
                //     ViewLoader.showErrorWindow(error);
                // }
                if(items instanceof  Destination && !(items instanceof Flight)){
                    try{
                        Stage DestStage = new Stage();
                        DestStage.getIcons().add(new Image("/image/destinations_icon.png"));
                        ViewLoader.showStage(dest, "/view/Destinations/DisplayDestinationsView.fxml", "View Destination", DestStage);
                    }
                    catch(IOException e){
                        e.printStackTrace();
                    }
                    

                }
                else if(items instanceof  Flight && !(items instanceof Destination)){
                    try{
                        Stage FliStage = new Stage();
                        FliStage.getIcons().add(new Image("/image/flights_icon.png"));
                        ViewLoader.showStage(flight, "/view/Flights/DisplayFlightsView.fxml", "View Destination", FliStage);
                    }                                   
                    catch(IOException e){
                        e.printStackTrace();
                    }
                    

                }
                
                
                
            }
            
        }catch(InputMismatchException e) {
            ViewLoader.showErrorWindow(new ErrorModel(e, " sdfds"));
           }
            
        } 
        
           
    

@FXML private void handleExit(){
    stage.close();
}
}
