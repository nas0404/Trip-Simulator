package controller.Destinations;

import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Destination;
import model.Destinations;
import model.Flight;
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.ItemNotFoundException;

public class ModifyDestinationsController extends Controller<Destinations>{
    @FXML TextField Countrytf;
    @FXML TextField Nametf;
    @FXML Button addDestination;
    @FXML Button removeDestination;
    @FXML Button Exit;

    public Destinations getDestinationsModel(){
        return this.model;
    }
    @FXML private void initialize(){
      
         if(addDestination != null){
            addDestination.setOnAction(event -> handleAddDestination());
            BooleanBinding checkbuttonstate = Countrytf.textProperty().isEmpty().or(Nametf.textProperty().isEmpty());
            addDestination.disableProperty().bind(checkbuttonstate);
         }
       
       if(removeDestination != null){
        removeDestination.setOnAction(event -> handleRemoveDestination());
            BooleanBinding checkbuttonstate = Countrytf.textProperty().isEmpty().or(Nametf.textProperty().isEmpty());
            removeDestination.disableProperty().bind(checkbuttonstate);
         }
        Exit.setOnAction(event -> handleExit());
        
    }

   @FXML private void handleAddDestination(){
        String country = Countrytf.getText();
        String name = Nametf.getText();
        Destination newDestination = new Destination(name, country);
        try {
            getDestinationsModel().addDestination(newDestination);
            stage.close();
        } catch (DuplicateItemException e) {
                        ViewLoader.showErrorWindow(new ErrorModel(e, "Destination already exists"));

        }
        
    }@FXML private void handleRemoveDestination(){
    try {
        String country = Countrytf.getText();
        String name = Nametf.getText();
        Destination destinationToRemove = null;

        for (Destination dest : getDestinationsModel().getDestinations()) {
            if (dest.getName().equals(name) && dest.getCountry().equals(country)) {
                destinationToRemove = dest;
                break;
            }
        }

        if (destinationToRemove != null) {
            getDestinationsModel().removeDestination(destinationToRemove);
            stage.close();
        } 
        else{
            throw new ItemNotFoundException();
        }

    } catch (ItemNotFoundException e) {
        ViewLoader.showErrorWindow(new ErrorModel(e, "Destination not found"));
    }
}

 


@FXML public void   handleExit(){
    stage.close();
   }

}
