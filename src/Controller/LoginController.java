package controller;

import java.io.IOException;



import au.edu.uts.ap.javafx.Controller;
import au.edu.uts.ap.javafx.ViewLoader;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.control.PasswordField;
import model.Administrator;
import model.Administrators;
import model.Agency;
import model.Exceptions.ErrorModel;
import model.Exceptions.InvalidCredentialsException;
public class LoginController extends Controller<Agency> {
    
    public Administrators admin = new Administrators();

    public Agency getAdministrator(){
        return this.model;
    }
    @FXML private TextField Usernametf;
    @FXML private PasswordField Passwordtf;
    @FXML private Button Login;
    @FXML private Button Exit;

    public void initialize(){
        Usernametf.setText("");
        Passwordtf.setText("");
        admin.insertDummyData();
        Login.setOnAction(event ->{
           try {
            handleLogin();
        } catch (IOException | InvalidCredentialsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }
        );




        Exit.setOnAction(event -> handleExit());
        
    }
    

      @FXML public void handleLogin() throws IOException, InvalidCredentialsException{
        try{
        String username = Usernametf.getText();
            String password = Passwordtf.getText();
           boolean UserExist = admin.hasAdministrator(username, password);
                
            
                if(UserExist){
             Administrator user = admin.getAdministrator(username, password);

             model.setLoggedInUser(user);
             if(user !=null){
                stage.close();
            Stage agenStage = new Stage();
               agenStage.getIcons().add(new Image("/image/agency_icon.png"));
              ViewLoader.showStage(model, "/view/AgencyView.fxml", "Prog 2 Travel Agency", agenStage);

             }
            }
            }
            catch(InvalidCredentialsException ex){
            ViewLoader.showErrorWindow(new ErrorModel(ex, "User not found"));
            return;            // }
            //  catch(InvalidCredentialsException e){
            //     System.out.println("INvalid");
            // }

             }
        

        }    

    
    public void handleExit(){
        stage.close();
    }
}

