/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasandolista;

import java.io.IOException;
import pasandolista.cnf.Save;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import pasandolista.cnf.Load;
import pasandolista.cnf.Config;

/**
 *
 * @author ADarkHero
 */
public class PasandoListaController implements Initializable {
    
    @FXML
    private Label counter;
    @FXML
    private Button exitButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button resetButton;
    @FXML
    private Button incrementButton;
    @FXML
    private Button decrementButton;
    @FXML
    private Button configButton;
    @FXML
    private ImageView backgroundImage;
    
    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
    @FXML
    private void save(ActionEvent event) {
            Save s = new Save();
            s.saveValue(counter.getText());
    }
    
    @FXML
    private void reset(ActionEvent event) {
            counter.setText("0");
    }
    
    @FXML
    private void increment(ActionEvent event) {
        int increment = Integer.parseInt(counter.getText());
        increment++;
        counter.setText(""+increment);
        
        Load l = new Load();
        if(l.getAutoSave()){
            saveButton.fire();
        }
    }
    
    @FXML
    private void decrement(ActionEvent event) {
        int decrement = Integer.parseInt(counter.getText());
        decrement--;
        
        if(decrement < 0){
            decrement = 0;                  //No negative counting
        }
        
        counter.setText(""+decrement);
        
        Load l = new Load();
        if(l.getAutoSave()){
            saveButton.fire();
        }
    }
    
    @FXML
    private void openConfig(ActionEvent event) throws IOException {
        Config c = new Config();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Load l = new Load();
        counter.setText(l.getValue());
        String image = "file:img/blank.png";
        if(!l.getBackgroundImage().equals("false")){
            image = l.getBackgroundImage();
            image = image.replaceAll("\\\\", "\\\\\\\\");
            image = "file:"+image;
            System.out.print(image);
        }
        counter.setTextFill(Color.web(l.getFontColor()));
        backgroundImage.setImage(new Image(image));
    }    
    
}
