package controller.Error;

import java.util.ResourceBundle.Control;

import au.edu.uts.ap.javafx.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Exceptions.ErrorModel;

public class ErrorController extends Controller<ErrorModel>{
    @FXML Label errorTxt;
    @FXML Label type;
     @FXML Button close;

    @FXML private void initialize(){
        String className = model.getException().getClass().getName();
        errorTxt.setText(model.getMessage());
        type.setText(className.substring((className.lastIndexOf(".") +1)));
        close.setOnAction(event -> handleClose());


    }

    @FXML private void handleClose(){
        stage.close();
    }
}

