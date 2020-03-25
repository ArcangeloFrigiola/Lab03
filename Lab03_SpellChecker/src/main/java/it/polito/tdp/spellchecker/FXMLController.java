package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.WordCheck;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {

    private ResourceBundle resources;
    
    ObservableList<String> languagesList = FXCollections.observableArrayList("English", "Italian");

   
    
    @FXML
    private URL location;

    @FXML
    private TextArea txtOutput;

    @FXML
    private Label numberErrorsTxt;

    @FXML
    private Button btnClear;

    @FXML
    private Label checkSpeedTxt;

    @FXML
    private ChoiceBox<String> languageChoices;
    
    @FXML
    private TextArea txtInput;

    @FXML
    private Button btnCheck;

    
    
    @FXML
    void doCheck(ActionEvent event) {

    	
    	Dictionary dizionario = new Dictionary();
    	dizionario.loadDictionary(this.languageChoices.getValue());
    	
    	//Linear spell check
    	double startTime = System.nanoTime();
    	List<WordCheck> parole = new LinkedList<>(dizionario.checkTxt(this.txtInput.getText()));
    	double finishTime = System.nanoTime();
    	
    	//Dichotomic spell check
    	double startTimeD = System.nanoTime();
    	List<WordCheck> paroleD = new LinkedList<>(dizionario.checkTxtDichotomic(this.txtInput.getText()));
    	double finishTimeD = System.nanoTime();
    	
    	this.txtOutput.clear();
    	int count = 0;
    	
    	for(WordCheck w: parole) {
    		if(!w.getStatus()) {
    			this.txtOutput.appendText(w.getParola()+"\n");
    			count ++;
    		}
    	}
    	this.numberErrorsTxt.setText("The text contains "+count+" errors!");
    	this.checkSpeedTxt.setText("Linear spell check completed in "+(finishTime-startTime)*0.000000001+" seconds.");
    			//+ "Dichotomic spell check completed in "+(finishTime-startTime)*0.000000001+" seconds");
    }

    @FXML
    void doClearTxtAll(ActionEvent event) {

    	this.txtInput.clear();
    	this.txtOutput.clear();
    }


    @FXML
    void initialize() {
        assert languageChoices != null : "fx:id=\"languageChoices\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Scene.fxml'.";
        assert numberErrorsTxt != null : "fx:id=\"numberErrorsTxt\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'Scene.fxml'.";
        assert checkSpeedTxt != null : "fx:id=\"checkSpeedTxt\" was not injected: check your FXML file 'Scene.fxml'.";

        this.languageChoices.setItems(languagesList);
        this.languageChoices.setValue("English");
    }
  
}
