/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pasandolista.cnf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.xml.sax.SAXException;


/**
 * FXML Controller class
 *
 * @author ADarkHero
 */
public class ConfigController implements Initializable {
    @FXML
    private Button newConfigButton;
    @FXML
    private Button loadConfigButton;
    @FXML
    private Button updateConfigButton;
    @FXML
    private Button closeConfigButton;
    @FXML
    private Button changeAutoSaveButton;
    @FXML
    private Button changeBackgroundImageButton;
    @FXML
    private Button deleteBackgroundImageButton;
    @FXML
    private TextField currentValue;
    @FXML
    private TextField autoSave;
    @FXML
    private TextField backgroundImage;
    @FXML
    private ColorPicker fontColor;
    
    @FXML
    private void newConfig(ActionEvent event) throws FileNotFoundException, IOException {  
        File oldConfig = new File("./cnf/config.xml"); 
        File sampleConfig = new File("./cnf/sample.xml");  
        
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("XML-File", "xml"));
        chooser.setDialogTitle("Specify a file to save"); 
        chooser.setCurrentDirectory(new File("./cnf/usr"));
        
        int userSelection = chooser.showSaveDialog(null);
 
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = chooser.getSelectedFile();
            
            
            if (!fileToSave.getAbsolutePath().endsWith(".xml")){
                String filepath = fileToSave.getCanonicalPath();
                filepath += ".xml";
                fileToSave = new File(filepath);
            }
            
            //Create new config file
            FileInputStream in = new FileInputStream(sampleConfig);
            FileOutputStream out = new FileOutputStream(fileToSave);

            FileChannel inc=in.getChannel();
            FileChannel outc=out.getChannel();

            inc.transferTo(0,inc.size(),outc);

            inc.close();
            outc.close();

            in.close();
            out.close();
            
            
            Save s = new Save();
            s.setFilepath(fileToSave.getCanonicalPath());
            s.saveXMLFileName(fileToSave.getCanonicalPath());
            s.resetFilepath();
            
            //Delete old one
            in = new FileInputStream(fileToSave);
            out = new FileOutputStream(oldConfig);

            inc=in.getChannel();
            outc=out.getChannel();

            inc.transferTo(0,inc.size(),outc);

            inc.close();
            outc.close();

            in.close();
            out.close();
            
            //Loads new config in UI
            loadStuff();
        }
    }
    
    @FXML
    private void loadConfig(ActionEvent event) throws FileNotFoundException, IOException {  
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("XML-File", "xml"));
        chooser.setDialogTitle("Specify a file to load"); 
        chooser.setCurrentDirectory(new File("./cnf/usr"));
        
        //Displays FileChooser
        int returnValue = chooser.showOpenDialog(null);
        
        //Sets saving path, if User selected a file
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            File fileToLoad = chooser.getSelectedFile();
            File oldConfig = new File("./cnf/config.xml"); 
            
            FileInputStream in = new FileInputStream(fileToLoad);
            FileOutputStream out = new FileOutputStream(oldConfig);

            FileChannel inc=in.getChannel();
            FileChannel outc=out.getChannel();

            inc.transferTo(0,inc.size(),outc);

            inc.close();
            outc.close();

            in.close();
            out.close();
            
            //Loads new config in UI
            loadStuff();
        }
    }
    
    @FXML
    private void updateConfig(ActionEvent event) throws SAXException {
        Save s = new Save();
        Load l = new Load();
        
        for(int i = 0; i < 2; i++){
            s.saveValue(currentValue.getText());
            s.autoSave(autoSave.getText());
            s.saveBackgroundImage(backgroundImage.getText());
            s.saveFontColor(fontColor.getValue().toString());
            s.setFilepath(l.getXMLFileName()); 
        }
        s.resetFilepath();
        
        closeConfigButton.fire();
    }

    @FXML
    private void closeConfig(ActionEvent event) {        
        Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void changeAutoSave(ActionEvent event) {        
        if(autoSave.getText().equals("false")){
            autoSave.setText("true");
        }
        else{
            autoSave.setText("false");
        }
    }
    
    @FXML
    private void changeBackgroundImage(ActionEvent event) throws IOException {        
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
        chooser.setDialogTitle("Specify a file to load"); 
        chooser.setCurrentDirectory(new File("./img"));
        
        //Displays FileChooser
        int returnValue = chooser.showOpenDialog(null);
        
        //Sets saving path, if User selected a file
        if(returnValue == JFileChooser.APPROVE_OPTION)
        {
            backgroundImage.setText(chooser.getSelectedFile().getCanonicalPath());
        }
    }
    
    @FXML
    private void deleteBackgroundImage(ActionEvent event) {        
        backgroundImage.setText("false");
    }
    
    @FXML
    private void loadStuff(){
        Load l = new Load();
        currentValue.setText(l.getValue());
        autoSave.setText(""+l.getAutoSave());
        backgroundImage.setText(l.getBackgroundImage());
        fontColor.setValue(Color.web(l.getFontColor()));
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadStuff();
    }  
    
}
